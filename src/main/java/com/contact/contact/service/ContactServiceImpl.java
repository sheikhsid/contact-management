package com.contact.contact.service;

import com.contact.contact.entity.ContactEntity;
import com.contact.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactEntity> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public ContactEntity getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public ContactEntity saveContact(ContactEntity contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
