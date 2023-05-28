package com.contact.contact.service;

import com.contact.contact.model.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAllContacts();

    ContactDto getContactById(Long id);

    ContactDto saveContact(ContactDto contact);

    void deleteContact(Long id);
}
