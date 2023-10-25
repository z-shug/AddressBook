/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook.controller;

import com.zls.addressbook.dao.AddressBookDao;
import com.zls.addressbook.dao.AddressBookDaoException;
import com.zls.addressbook.dto.Address;
import com.zls.addressbook.ui.AddressBookView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zshug
 */
public class AddressBookController {
    private final AddressBookView view; 
    private final AddressBookDao dao;
    
    public AddressBookController( AddressBookView view, AddressBookDao dao){
        this.view = view;
        this.dao = dao;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        view.welcomeBanner();
        
        try{
            while(keepGoing){
                menuSelection = view.printMenuAndGetSelection();
                
                switch(menuSelection){
                    case 1:
                        addAddress();
                        break;
                    case 2:
                        editAddress();
                        break;
                    case 3:
                        removeAddress();
                        break;
                    case 4:
                        retrieveAddressCount();
                        break;
                    case 5:
                        listAllAddresses();
                        break;
                    case 6:
                        findAddress();
                        break;
                    case 7:
                      keepGoing = false;
                      break;
                    default: 
                        view.unknownCommandBanner();
                        
                }
            }
        } catch(AddressBookDaoException e){
            view.errorMessage(e.getMessage());
        }
    }
    
    public void addAddress() throws AddressBookDaoException {
        view.addAddressBanner();
        Address newAddress = view.collectNewAddressInfo();
        Address checkExists = dao.getAddressByName(newAddress.getLastName());
        if( checkExists != null){
            view.alredyExistsBanner();
            return;
        }
        String lastName = newAddress.getLastName();
        dao.addAddress(lastName, newAddress);
        view.addAddressSuccessBanner();
        view.returnToMenuBanner();  
    }
    
    public void editAddress() throws AddressBookDaoException {
        view.editAddressBanner();
        String lastName = view.editAddressMenu();
        Address foundAddress = dao.getAddressByName(lastName);
        if(foundAddress != null){
            boolean keepGoing = true;
            while(keepGoing){
               view.displayAddress(foundAddress);
               int userChoice = view.editAddressMenuAndSelection();
               switch (userChoice){
                   case 1:
                       String newFirstName = view.editFieldInput("first name");
                       foundAddress.setFirstName(newFirstName);
                       break;
                   case 2:
                       String newLastName = view.editFieldInput("last name");
                       foundAddress.setLastName(newLastName);
                       break;
                   case 3:
                       String newStreet = view.editFieldInput("street address");
                       foundAddress.setStreet(newStreet);
                       break;
                   case 4:
                       String newCity = view.editFieldInput("city");
                       foundAddress.setCity(newCity);
                       break;
                   case 5:
                       String newState = view.editFieldInput("state");
                       foundAddress.setState(newState);
                       break;
                   case 6:
                       String newZip = view.editFieldInput("Zip");
                       foundAddress.setZip(newZip);
                       break;
                   case 7:
                       keepGoing = false;
                       break;
                   default:
                       view.unknownCommandBanner();
                } 
            }
            view.displayAddress(foundAddress);
            int confirmEdit = view.confirmEdit();
            switch (confirmEdit){
                case 1:
                    dao.removeAddress(lastName);
                    dao.editAddress(foundAddress.getLastName(), foundAddress);
                    view.editSuccessBanner();
                    break;
                case 2:
                   view.editCancelBanner();
                   break;
            }
        }else{
            view.doesNotExistBanner();
        }
        view.returnToMenuBanner();
    }
    
    public void removeAddress() throws AddressBookDaoException{
        view.removeAddressBanner();
        String lastName = view.removeAddressMenu();
        Address foundAddress = dao.getAddressByName(lastName);
        if(foundAddress != null){
            view.displayAddress(foundAddress);
            int userChoice = view.confirmRemove();
            switch(userChoice){
                case 1:
                    dao.removeAddress(lastName);
                    view.removeSuccessBanner();
                    break;
                case 2:
                    view.removeCancelBanner();
                    break;
            }
        }else{
            view.doesNotExistBanner();
        }
        view.returnToMenuBanner();
    }
    
    public void retrieveAddressCount() throws AddressBookDaoException{
        view.totalCountBanner();
        int count = dao.getAddressCount();
        view.displayTotalCount(count);
        view.returnToMenuBanner();
    }
    
    public void listAllAddresses () throws AddressBookDaoException {
        view.listAllBanner();
        List<Address> addressList = dao.getAllAddress();
        view.listAllAddresses(addressList);
        view.returnToMenuBanner();
    }
    
    public void findAddress() throws AddressBookDaoException{
        view.findAddressBanner();
        String userInput = view.findAddressMenu();
        Address foundAddress = dao.getAddressByName(userInput);
        if(foundAddress != null){
            view.displayAddress(foundAddress);
        }else{
            view.doesNotExistBanner();
        }
        view.returnToMenuBanner();
    }
}
