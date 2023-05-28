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
        contacts.add(new ContactDto(1L, "Sheikh Saad", null, null, "sheikh@domain.com"));
        contacts.add(new ContactDto(2L, "Sheikh Amin", null, null, "amin@domain.com"));

        // Mock the repository's behavior
        when(contactRepository.findAll()).thenReturn(contacts);

        // Perform the service call
        List<ContactDto> result = contactService.getAllContacts();

        // Verify the result
        assertEquals(2, result.size());
        // You can add more assertions as needed
    }

	@Test
	public void testDeleteContact() {
		// Prepare test data
		Long contactId = 1L;

		// Perform the service call
		contactService.deleteContact(contactId);

		// Verify the repository's delete method was called with the correct contactId
		verify(contactRepository, times(1)).deleteById(contactId);
	}

}
