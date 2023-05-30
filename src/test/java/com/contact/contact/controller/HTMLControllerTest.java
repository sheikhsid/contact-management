package com.contact.contact.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;

public class HTMLControllerTest {

    private HTMLController htmlController;
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        contactRepository = mock(ContactRepository.class);
        htmlController = new HTMLController(contactRepository);
    }

    @Test
    public void testSaveContact() {
        ContactDto contact = new ContactDto(1L, "Sheikh Saad", null, null, "sheikh@domain.com");
        @SuppressWarnings("unused")
		String modelAndView = htmlController.saveContact(contact);

        verify(contactRepository).save(contact);
    }
}
