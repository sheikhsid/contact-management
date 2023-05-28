package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HTMLController {
    private final ContactRepository contactRepository;

    @Autowired
    public HTMLController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute ContactDto contactDto) {
        Contact contact = mapDtoToModel(contactDto);
        contactRepository.save(contact);
        return "redirect:/";
    }

    private Contact mapDtoToModel(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        // Map other fields from the DTO to the Contact entity as needed
        return contact;
    }
}

