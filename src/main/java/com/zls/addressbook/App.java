/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.addressbook;

import com.zls.addressbook.controller.AddressBookController;
import com.zls.addressbook.dao.AddressBookDao;
import com.zls.addressbook.dao.AddressBookDaoFileImpl;
import com.zls.addressbook.ui.AddressBookView;
import com.zls.addressbook.ui.UserIO;
import com.zls.addressbook.ui.UserIOConsoleImpl;

/**
 *
 * @author zshug
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookDao dao = new AddressBookDaoFileImpl();
        AddressBookController controller = new AddressBookController(view,dao);
        controller.run();
    }
}
