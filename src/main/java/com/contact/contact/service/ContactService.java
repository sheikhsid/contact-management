package com.contact.contact.service;

import com.contact.contact.model.Contact;

import java.util.List;

public interface ContactService {
    void saveContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    // Add other methods as per your requirements
}
