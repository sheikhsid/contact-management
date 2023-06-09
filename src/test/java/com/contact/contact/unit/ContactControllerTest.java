package com.contact.contact.unit;

import com.contact.contact.controller.ContactController;
import com.contact.contact.entity.ContactEntity;
import com.contact.contact.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testGetAllContacts() throws Exception {
        List<ContactEntity> contacts = new ArrayList<>();
        contacts.add(new ContactEntity(1L, "Sheikh Saad", null, null, "sheikh@domain.com"));
        contacts.add(new ContactEntity(2L, "Sheikh Amin", null, null, "amin@domain.com"));

        Mockito.when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Sheikh Saad"))
                .andExpect(jsonPath("$[0].email").value("sheikh@domain.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Sheikh Amin"))
                .andExpect(jsonPath("$[1].email").value("amin@domain.com"));

        Mockito.verify(contactService, Mockito.times(1)).getAllContacts();
    }

    @Test
    public void testGetContactById() throws Exception {
        Long contactId = 1L;
        ContactEntity contact = new ContactEntity(contactId, "Sheikh Saad", null, null, "sheikh@domain.com");

        Mockito.when(contactService.getContactById(contactId)).thenReturn(contact);

        mockMvc.perform(get("/contacts/{id}", contactId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(contactId))
                .andExpect(jsonPath("$.name").value("Sheikh Saad"))
                .andExpect(jsonPath("$.email").value("sheikh@domain.com"));

        Mockito.verify(contactService, Mockito.times(1)).getContactById(contactId);
    }

    @Test
    public void testGetContactById_ContactNotFound() throws Exception {
        Long contactId = 1L;

        Mockito.when(contactService.getContactById(contactId)).thenReturn(null);

        mockMvc.perform(get("/contacts/{id}", contactId))
                .andExpect(status().isNotFound());

        Mockito.verify(contactService, Mockito.times(1)).getContactById(contactId);
    }

    @Test
    public void testSaveContact() throws Exception {
        ContactEntity contact = new ContactEntity(1L, "Sheikh Saad", null, null, "sheikh@domain.com");

        Mockito.when(contactService.saveContact(any(ContactEntity.class))).thenReturn(contact);

        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sheikh Saad\",\"email\":\"sheikh@domain.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sheikh Saad"))
                .andExpect(jsonPath("$.email").value("sheikh@domain.com"));

        Mockito.verify(contactService, Mockito.times(1)).saveContact(any(ContactEntity.class));
    }

    @Test
    public void testDeleteContact() throws Exception {
        Long contactId = 1L;

        mockMvc.perform(delete("/contacts/{id}", contactId))
                .andExpect(status().isNoContent());

        Mockito.verify(contactService, Mockito.times(1)).deleteContact(contactId);
    }
}
