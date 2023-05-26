package com.contact.contact.service;

import com.contact.contact.model.ContactModel;
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
    public List<ContactModel> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public ContactModel getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public ContactModel saveContact(ContactModel contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
