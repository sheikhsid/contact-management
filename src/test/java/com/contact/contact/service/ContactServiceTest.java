package com.contact.contact.service;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void testGetAllContacts() {
        // Prepare test data
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(new ContactDto(1L, "John Doe", null, null, "john@example.com"));
        contacts.add(new ContactDto(2L, "Jane Smith", null, null, "jane@example.com"));

        // Mock the repository's behavior
        when(contactRepository.findAll()).thenReturn(contacts);

        // Perform the service call
        List<ContactDto> result = contactService.getAllContacts();

        // Verify the result
        assertEquals(2, result.size());
        // You can add more assertions as needed
    }
}
