package com.contact.contact.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;

public class HTMLControllerTest {

    private MockMvc mockMvc;
    private HTMLController htmlController;
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        contactRepository = mock(ContactRepository.class);
        htmlController = new HTMLController(contactRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(htmlController).build();
    }

    @Test
    public void testSaveContact() throws Exception {
        ContactDto contact = new ContactDto(1L, "Sheikh Saad", null, null, "sheikh@domain.com");

        mockMvc.perform(post("/saveContact")
                .flashAttr("contactDto", contact))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(contactRepository).save(contact);
    }
}
