/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook.dao;

import com.zls.addressbook.dto.Address;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zshug
 */
public class AddressBookDaoFileImplTest {
    
    AddressBookDao testDao;
    
    public AddressBookDaoFileImplTest() {
    }
     
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testAddressBook.txt";
        new FileWriter(testFile);
        testDao = new AddressBookDaoFileImpl(testFile);
    }


    @Test
    public void testAddGetAddress() throws Exception{
        String lastName = "Lovelace";
        Address address = new Address();
        address.setLastName("Lovelace");
        address.setFirstName("Ada");
        address.setStreet("123 Street");
        address.setCity("seattle");
        address.setState("WA");
        address.setZip("98102");
        
        
        testDao.addAddress(lastName, address);
        Address retrievedAddress = testDao.getAddressByName(lastName);
        
        assertEquals(address.getFirstName(), retrievedAddress.getFirstName(),"Checking first name");
        assertEquals(address.getLastName(),retrievedAddress.getLastName(),"Checking last name");
        assertEquals(address.getStreet(), retrievedAddress.getStreet(),"Checking street address");
        assertEquals(address.getCity(), retrievedAddress.getCity(),"Checking city ");
        assertEquals(address.getState(), retrievedAddress.getState(),"Checking state");
        assertEquals(address.getZip(), retrievedAddress.getZip(),"Checking zip");
        
    }
    
    @Test 
    void testAddGetAllAddress() throws Exception{
        Address firstAddress = new Address("Ada","Lovelace","123 Street","Seattle","WA","98102");
        Address secondAddress = new Address("Charles","Babbage","456 Ave","Portland","OR","97177");
        
        testDao.addAddress(firstAddress.getLastName(), firstAddress);
        testDao.addAddress(secondAddress.getLastName(), secondAddress);
        
        List<Address> allAddress = testDao.getAllAddress();
        
        assertNotNull(allAddress, "The list of addresses must not be null");
        assertEquals(2, allAddress.size(),"List of addresses should have 2");
        
        assertTrue(allAddress.contains(firstAddress),"List of addresses should include Ada");
        assertTrue(allAddress.contains(secondAddress),"List of addresses should includ Charles");
    }
    
    @Test
    void testRemoveStudent() throws Exception{
        Address firstAddress = new Address("Ada","Lovelace","123 Street","Seattle","WA","98102");
        Address secondAddress = new Address("Charles","Babbage","456 Ave","Portland","OR","97177");
        
        testDao.addAddress(firstAddress.getLastName(), firstAddress);
        testDao.addAddress(secondAddress.getLastName(), secondAddress);
        
        Address removedAddress = testDao.removeAddress(firstAddress.getLastName());
        assertEquals(removedAddress,firstAddress,"The removed address should be Ada");
        
        List<Address> allAddress = testDao.getAllAddress();
        assertNotNull(allAddress,"All address should not be null");
        assertEquals(1,allAddress.size(),"All address should have 1 address");
        
        assertTrue(allAddress.contains(secondAddress),"All Address should have Charles");
        assertFalse(allAddress.contains(firstAddress),"All address should not have Ada");
        
        removedAddress = testDao.removeAddress(secondAddress.getLastName());
        assertEquals(removedAddress,secondAddress,"The removed address should be Charles");
        
        allAddress = testDao.getAllAddress();
        assertTrue(allAddress.isEmpty(),"The retrieved list should be empty");
        
        Address retrievedAddress = testDao.getAddressByName(firstAddress.getLastName());
        assertNull(retrievedAddress,"Ada was removed, should be null");
        
        retrievedAddress = testDao.getAddressByName(secondAddress.getLastName());
        assertNull(retrievedAddress,"Charles was removed, should be null");
        
    }
    
    @Test
    void testEditAddress() throws Exception{
        Address address = new Address("Ada","Lovelace","123 Street","Seattle","WA","98102");
        
        testDao.addAddress(address.getLastName(), address);
        
        address.setFirstName("Suzy");
        Address returnedAddress = testDao.editAddress(address.getLastName(), address);
        
        List<Address> allAddress = testDao.getAllAddress();
        
        assertNotNull(allAddress,"All addreses list should not be null");
        assertEquals(1,allAddress.size(),"All addresses should only have 1 address");
        
        assertNotEquals(returnedAddress.getFirstName(),address.getFirstName(),"First name should be changed");
    }
    
    @Test
    void testGetAddressTotal() throws Exception{
        Address firstAddress = new Address("Ada","Lovelace","123 Street","Seattle","WA","98102");
        Address secondAddress = new Address("Charles","Babbage","456 Ave","Portland","OR","97177");
        
        testDao.addAddress(firstAddress.getLastName(), firstAddress);
        testDao.addAddress(secondAddress.getLastName(), secondAddress);
        List<Address> allAddress = testDao.getAllAddress();
        int totalCount = allAddress.size();
        
        int totalAddressCount = testDao.getAddressCount();
        
        assertEquals(2,totalAddressCount,"Total addresses should be 2");
        
        testDao.removeAddress(firstAddress.getLastName());
        
        totalAddressCount = testDao.getAddressCount();
        assertEquals(1, totalAddressCount,"Total addresses should be 1");
    }
    
    
}
