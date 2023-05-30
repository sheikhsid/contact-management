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
        // Perform necessary operations with the ContactDto object
        // Here you can directly use the ContactDto object to save the data to the repository or perform any other required actions
        contactRepository.save(contactDto);
        return "redirect:/";
    }
}
