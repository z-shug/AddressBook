/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook.dao;

import com.zls.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author zshug
 */
public interface AddressBookDao {
    public Address addAddress(String lastName, Address address)
            throws AddressBookDaoException;
    
    public Address removeAddress(String lastName)
            throws AddressBookDaoException;
    
    public Address editAddress(String lastName, Address address)
            throws AddressBookDaoException;
    
    public List<Address> getAllAddress()
            throws AddressBookDaoException;
    
    public Address getAddressByName(String lastName)
            throws AddressBookDaoException;
    
    public int getAddressCount()
            throws AddressBookDaoException;
    
}
