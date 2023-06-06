package com.contact.contact.service;

import com.contact.contact.entity.ContactEntity;

import java.util.List;

public interface ContactService {
    List<ContactEntity> getAllContacts();

    ContactEntity getContactById(Long id);

    ContactEntity saveContact(ContactEntity contact);

    void deleteContact(Long id);
}

