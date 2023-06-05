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
    public String saveContact(@ModelAttribute("contactDto") ContactDto contactDto) {
        ContactDto sanitizedContactDto = new ContactDto();
        sanitizedContactDto.setName(contactDto.getName());
        sanitizedContactDto.setCompany(contactDto.getCompany());
        sanitizedContactDto.setNumber(contactDto.getNumber());
        sanitizedContactDto.setEmail(contactDto.getEmail());

        contactRepository.save(sanitizedContactDto);
        return "redirect:/";
    }
}
