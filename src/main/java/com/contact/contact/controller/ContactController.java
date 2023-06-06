package com.contact.contact.controller;

import com.contact.contact.entity.ContactEntity;
import com.contact.contact.model.ContactDto;
import com.contact.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactEntity> contacts = contactService.getAllContacts();
        List<ContactDto> contactDtos = contacts.stream()
                .map(contact -> new ContactDto(
                        contact.getId(),
                        contact.getName(),
                        contact.getCompany(),
                        contact.getNumber(),
                        contact.getEmail()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(contactDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable("id") Long id) {
        ContactEntity contact = contactService.getContactById(id);
        if (contact != null) {
            ContactDto contactDto = new ContactDto(
                    contact.getId(),
                    contact.getName(),
                    contact.getCompany(),
                    contact.getNumber(),
                    contact.getEmail()
            );
            return new ResponseEntity<>(contactDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@RequestBody ContactDto contactDto) {
        ContactEntity contactEntity = new ContactEntity(
                contactDto.getId(),
                contactDto.getName(),
                contactDto.getCompany(),
                contactDto.getNumber(),
                contactDto.getEmail()
        );
        ContactEntity savedContact = contactService.saveContact(contactEntity);
        ContactDto savedContactDto = new ContactDto(
                savedContact.getId(),
                savedContact.getName(),
                savedContact.getCompany(),
                savedContact.getNumber(),
                savedContact.getEmail()
        );
        return new ResponseEntity<>(savedContactDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
