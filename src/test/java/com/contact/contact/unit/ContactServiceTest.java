package com.contact.contact.unit;

import com.contact.contact.entity.ContactEntity;
import com.contact.contact.repository.ContactRepository;
import com.contact.contact.service.ContactService;
import com.contact.contact.service.ContactServiceImpl;
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
        List<ContactEntity> contacts = new ArrayList<>();
        contacts.add(new ContactEntity(1L, "Sheikh Saad", null, null, "sheikh@domain.com"));
        contacts.add(new ContactEntity(2L, "Sheikh Amin", null, null, "amin@domain.com"));

        when(contactRepository.findAll()).thenReturn(contacts);

        List<ContactEntity> result = contactService.getAllContacts();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetContactById() {
        Long contactId = 1L;
        ContactEntity contact = new ContactEntity(contactId, "Sheikh Saad", null, null, "sheikh@domain.com");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        ContactEntity result = contactService.getContactById(contactId);

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
        ContactEntity contactToSave = new ContactEntity(null, "Sheikh Saad", null, null, "sheikh@domain.com");
        ContactEntity savedContact = new ContactEntity(1L, "Sheikh Amin", null, null, "amin@domain.com");

        // Mock the repository's behavior
        when(contactRepository.save(any(ContactEntity.class))).thenReturn(savedContact);

        // Perform the service call
        ContactEntity result = contactService.saveContact(contactToSave);

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
