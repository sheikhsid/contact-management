package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        String modelAndView = htmlController.saveContact(contact);

        verify(contactRepository).save(contact);
    }
}
