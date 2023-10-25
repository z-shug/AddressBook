/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook.ui;

import com.zls.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author zshug
 */
public class AddressBookView {
    private final UserIO io;
    
    public AddressBookView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu:");
        io.print("\t1. Add Address");
        io.print("\t2. Edit Address");
        io.print("\t3. Remove Address");
        io.print("\t4. Retrieve Address Count");
        io.print("\t5. List All Addresses");
        io.print("\t6. Find Address");
        io.print("\t7. Exit");
        
        int userChoice = io.readInt("Please select from the above choices:", 1,7);
        return userChoice;
    }   
    
    public Address collectNewAddressInfo(){
        io.print("Add Address Menu:");
        String firstName = io.readString("\tPlease Enter First Name:");
        String lastName = io.readString("\tPlease Enter Last Name:");
        String  street = io.readString("\tPlease Enter Street Address:");
        String  city = io.readString("\tPlease Enter City:");
        String  state = io.readString("\tPlease Enter State:");
        String  zip = io.readString("\tPlease Enter Zip:");
        
        Address newAddress = new Address();
        newAddress.setFirstName(firstName);
        newAddress.setLastName(lastName);
        newAddress.setStreet(street);
        newAddress.setCity(city);
        newAddress.setState(state);
        newAddress.setZip(zip);
        return newAddress;
    }
    
    public String editAddressMenu(){
        io.print("Edit Address Menu:");
        return io.readString("\tPlease enter the last name of the address you wish to edit:");
    }
    
    public int editAddressMenuAndSelection(){
        io.print("\tWhat would you like to edit?");
        io.print("\t1. First Name");
        io.print("\t2. Last Name");
        io.print("\t3. Street Address");
        io.print("\t4. City");
        io.print("\t5. State");
        io.print("\t6. Zip");
        io.print("\t7. Finished Editing");
        return io.readInt("\nPlease make a selection:",1,7);
    }
    
    public String editFieldInput(String forWhat){
        return io.readString("\nPlease enter the new " +forWhat + ":");
    }
    
    public int confirmEdit(){
        io.print("\tPlease confirm this edit:");
        io.print("\t1. Confirm");
        io.print("\t2. Cancel");
        return io.readInt("\tPlease make a selection:",1,2);
    }
    
    public String removeAddressMenu(){
        io.print("Remove Address Menu:");
        return io.readString("\tPlease enter the last name of address to remove:");
        
    }
    
    public String findAddressMenu(){
        io.print("Find Address Menu:");
        return io.readString("\t Please enter the last name of the address you wish to find:");
    }
    
    public void displayAddress(Address address){
        io.print("\n\t" + address.getFirstName()+" "+address.getLastName());
        io.print("\t" + address.getStreet());
        io.print("\t" + address.getCity() +
                    ", " + address.getState() + 
                    ", " + address.getZip() + "\n");
    }
    
    public int confirmRemove(){
        io.print("\tAre you sure you want to remove this address?");
        io.print("\t1. Yes");
        io.print("\t2. No");
        return io.readInt("\tPlease confirm:",1,2);
        
    }
    
    public void listAllAddresses(List<Address> addresses){
        for ( Address address: addresses){
            io.print("\t" + address.getFirstName()+" "+address.getLastName());
            io.print("\t" + address.getStreet());
            io.print("\t" + address.getCity() +
                    ", " + address.getState() + 
                    ", " + address.getZip() + "\n");
        }
    }
    
    public void displayTotalCount(int count){
        io.print("There are " +count+ " addresses in your address book."); 
    }
    
    
    public void addAddressSuccessBanner(){
        io.print("*****ADDRESS SUCCESFULLY ADDED*****");
    }
    
    public void editAddressBanner(){
        io.print("*****EDIT AN ADDRESS*****");
    }
    
    public void editSuccessBanner(){
        io.print("*****ADDRESS SUCCESSFULLY UPDATED*****");
    }
    
    public void editCancelBanner(){
        io.print("*****EDIT CANCELED*****");
    }
    
    public void findAddressBanner(){
        io.print("*****FIND AN ADDRESS*****");
    }
    
    public void removeAddressBanner(){
        io.print("*****REMOVE AN ADDRESS*****");
    }
    
    public void removeSuccessBanner(){
        io.print("*****ADRESS SUCCESFULLY REMOVED*****");
    }
    
    public void removeCancelBanner(){
        io.print("*****REMOVAL CANCELED*****");
    }
    
    public void alredyExistsBanner(){
        io.print("*****ADDRESS ALREADY EXISTS*****");
    }
    
    public void doesNotExistBanner(){
        io.print("*****ADDRESS DOES NOT EXIST******");
    }
    
    public void addAddressBanner(){
        io.print("*****ADD AN ADDRESS*****");
    }
    
    public void returnToMenuBanner(){
        io.print("*****RETUNRING TO MAIN MENU*****");
    }
    
    public void listAllBanner(){
        io.print("*****LISTING ALL ADDRESSES*****");
    }
    
    public void unknownCommandBanner(){
        io.print("*****UNKNOWN COMMAND*****");
    }
    
    public void totalCountBanner(){
        io.print("*****RETRIEVING ADDRESS COUNT*****");
    }
    
    public void welcomeBanner(){
        io.print("*****WELCOME TO YOUR ADDRESS BOOK*****");
    }
    
    public void errorMessage(String errorMessage){
        io.print("*****ERROR*****");
        io.print(errorMessage);
    }
}
