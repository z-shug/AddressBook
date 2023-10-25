/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook.dao;

import com.zls.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author zshug
 */
public class AddressBookDaoFileImpl implements AddressBookDao{
    private Map<String,Address> addressMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public final String FILE;
    public static final String DELIMITER = "::";
    
    public AddressBookDaoFileImpl(){
        FILE = "AddressBookFile.txt";
    }
    
    public AddressBookDaoFileImpl(String addressBookFile){
        this.FILE = addressBookFile;
    }
    
    
    private Address unmarshallAddress(String addressAsText){
        String[] addressTokens = addressAsText.split(DELIMITER);
        Address newAddress = new Address();
        
        newAddress.setLastName(addressTokens[0]);
        newAddress.setFirstName(addressTokens[1]);
        newAddress.setStreet(addressTokens[2]);
        newAddress.setCity(addressTokens[3]);
        newAddress.setState(addressTokens[4]);
        newAddress.setZip(addressTokens[5]);
        return newAddress;
    }
    
    private void loadFile() throws AddressBookDaoException{
        Scanner reader;
        try{
            reader = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException ex) {
            throw new AddressBookDaoException("Could not load Addres Book into memory.",ex);
        }
        String currentLine;
        Address currentAddress;
        while (reader.hasNextLine()){
            currentLine = reader.nextLine();
            currentAddress = unmarshallAddress(currentLine);
            addressMap.put(currentAddress.getLastName(), currentAddress);
        }
        reader.close();
    }
    
    private String marshallAddress(Address address){
        String addressTextLine = "";
        
        addressTextLine += address.getLastName() + DELIMITER;
        addressTextLine += address.getFirstName() + DELIMITER;
        addressTextLine += address.getStreet() + DELIMITER;
        addressTextLine += address.getCity() + DELIMITER;
        addressTextLine += address.getState() + DELIMITER;
        addressTextLine += address.getZip();
        return addressTextLine;           
    }
    
    private void writeFile() throws AddressBookDaoException {
        PrintWriter writer;
        try{
            writer = new PrintWriter(new FileWriter(FILE));
        }catch(IOException ex){
            throw new AddressBookDaoException("Could not save address to text file.",ex);
        }
        String addressAsText;
        List<Address> addressList = this.getAllAddress();
        for(Address address: addressList){
            addressAsText = marshallAddress(address);
            writer.println(addressAsText);
            writer.flush();
        }
        writer.close();
    }
    
    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookDaoException{
        loadFile();
        Address newAddress = addressMap.put(lastName,address);
        writeFile();
        return newAddress;
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookDaoException{
        loadFile();
        Address removedAddress = addressMap.remove(lastName);
        writeFile();
        return removedAddress;
    }

    @Override
    public Address editAddress(String lastName, Address address)throws AddressBookDaoException {
        loadFile();
        Address editedAddress = addressMap.put(lastName, address);
        writeFile();
        return editedAddress;
    }

    @Override
    public List<Address> getAllAddress() throws AddressBookDaoException{
        loadFile();
        return new ArrayList<Address>(addressMap.values());
    }

    @Override
    public Address getAddressByName(String lastName)throws AddressBookDaoException {
        loadFile();
        return addressMap.get(lastName);
        
    }

    @Override
    public int getAddressCount()throws AddressBookDaoException {
        loadFile();
        return addressMap.size();
    }
    
    
    
}
