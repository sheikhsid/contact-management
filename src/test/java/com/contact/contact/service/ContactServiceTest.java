package com.contact.contact.service;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import com.contact.contact.exception.ContactNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @SuppressWarnings("deprecation")
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void testGetAllContacts() {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(new ContactDto(1L, "Sheikh Saad", null, null, "sheikh@domain.com"));
        contacts.add(new ContactDto(2L, "Sheikh Amin", null, null, "amin@domain.com"));

        when(contactRepository.findAll()).thenReturn(contacts);

        List<ContactDto> result = contactService.getAllContacts();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetContactById() {
        Long contactId = 1L;
        ContactDto contact = new ContactDto(contactId, "Sheikh Saad", null, null, "sheikh@domain.com");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        ContactDto result = contactService.getContactById(contactId);

        assertEquals(contactId, result.getId());
        assertEquals("Sheikh Saad", result.getName());
        assertEquals("sheikh@domain.com", result.getEmail());

    }

    @Test
    public void testContactNotFoundExceptionMessage() {
        String errorMessage = "Contact not found";
        ContactNotFoundException exception = new ContactNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testSaveContact() {
        // Prepare test data
        ContactDto contactToSave = new ContactDto(null, "Sheikh Saad", null, null, "sheikh@domain.com");
        ContactDto savedContact = new ContactDto(1L, "Sheikh Amin", null, null, "amin@domain.com");

        // Mock the repository's behavior
        when(contactRepository.save(any(ContactDto.class))).thenReturn(savedContact);

        // Perform the service call
        ContactDto result = contactService.saveContact(contactToSave);

        // Verify the result
        assertNotNull(result.getId());
        assertEquals("Sheikh Amin", result.getName());
        assertEquals("amin@domain.com", result.getEmail());
        // You can add more assertions as needed
    }

    @Test
    public void testDeleteContact() {
        Long contactId = 1L;

        contactService.deleteContact(contactId);

        verify(contactRepository, times(1)).deleteById(contactId);
    }
}
