package com.contact.contact.service;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
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
	public void testDeleteContact() {
		
		Long contactId = 1L;

		contactService.deleteContact(contactId);

		verify(contactRepository, times(1)).deleteById(contactId);
	}

}
