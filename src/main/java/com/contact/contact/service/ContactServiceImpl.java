package com.contact.contact.service;

import com.contact.contact.model.Contact;
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
    public void saveContact(Contact contact) {
        // Implement the saveContact logic here
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        // Implement the getAllContacts logic here
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        // Implement the getContactById logic here
        return contactRepository.findById(id).orElse(null);
    }
    // Implement other methods from the ContactService interface
}
