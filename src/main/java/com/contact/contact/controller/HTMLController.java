package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HTMLController {

    private final ContactRepository contactRepository;

    @Autowired
    public HTMLController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute @Valid ContactDto contactDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors, e.g., return to the form with error messages
            return "contact-form";
        }

        // Perform additional sanitization or validation checks if needed

        contactRepository.save(contactDto);
        return "redirect:/";
    }
}
