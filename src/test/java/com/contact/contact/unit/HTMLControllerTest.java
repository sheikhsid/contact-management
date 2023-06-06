package com.contact.contact.unit;

import com.contact.contact.controller.HTMLController;
import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HTMLControllerTest {

    @Mock
    private ContactRepository contactRepository;

    private HTMLController htmlController;

    @SuppressWarnings("deprecation")
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        htmlController = new HTMLController(contactRepository);
    }

    @Test
    public void testSaveContact() {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(1L);
        contactDto.setName("Sheikh Saad");
        contactDto.setCompany("SDTechnologist");
        contactDto.setNumber("1234567890");
        contactDto.setEmail("sheikh@domain.com");

        htmlController.saveContact(contactDto);

        Mockito.verify(contactRepository).save(Mockito.any()); // Update this line to accept any ContactEntity object
    }
}
