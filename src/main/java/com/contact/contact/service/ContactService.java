package com.contact.contact.service;

import com.contact.contact.model.ContactModel;

import java.util.List;

public interface ContactService {
    List<ContactModel> getAllContacts();

    ContactModel getContactById(Long id);

    ContactModel saveContact(ContactModel contact);

    void deleteContact(Long id);
}
