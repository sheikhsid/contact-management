package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class ContactControllerTestIT {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7").withDatabaseName("mydb")
            .withUsername("root").withPassword("password").withReuse(true);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        contactRepository.deleteAll();
    }

    @BeforeClass
    public static void beforeAll() {
        mysql.start();
    }

    @AfterClass
    public static void afterAll() {
        mysql.stop();
    }

    @Test
    public void testCreateContact() {
        // Arrange
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh Saad");
        contactDto.setEmail("sheikh@domain.com");
        contactDto.setNumber("1234567890");

        String url = "http://localhost:" + port + "/contacts";

        // Act
        ResponseEntity<ContactDto> response = restTemplate.postForEntity(url, contactDto, ContactDto.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ContactDto createdContact = response.getBody();
        assertNotNull(createdContact.getId());
    }

    @Test
    public void testGetContactById() {
        // Arrange
        ContactDto contact = new ContactDto();
        contact.setName("Sheikh Saad");
        contact.setEmail("sheikh@domain.com");
        contact.setNumber("9876543210");
        ContactDto savedContact = contactRepository.save(contact);

        String url = "http://localhost:" + port + "/contacts/" + savedContact.getId();

        // Act
        ResponseEntity<ContactDto> response = restTemplate.getForEntity(url, ContactDto.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContactDto fetchedContact = response.getBody();
        assertEquals(savedContact.getId(), fetchedContact.getId());
    }

    @Test
    public void testGetAllContacts() {
        // Arrange
        ContactDto contact1 = new ContactDto();
        contact1.setName("Sheikh Saad");
        contact1.setEmail("sheikh@domain.com");
        contact1.setNumber("1234567890");

        ContactDto contact2 = new ContactDto();
        contact2.setName("Sheikh Saad");
        contact2.setEmail("sheikh@domain.com");
        contact2.setNumber("9876543210");

        contactRepository.save(contact1);
        contactRepository.save(contact2);

        String url = "http://localhost:" + port + "/contacts";

        // Act
        ResponseEntity<ContactDto[]> response = restTemplate.getForEntity(url, ContactDto[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContactDto[] contacts = response.getBody();
        assertNotNull(contacts);
        assertEquals(2, contacts.length);
    }

    @Test
    public void testDeleteContact() {
        // Arrange
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh Saad");
        contactDto.setEmail("sheikh@domain.com");
        contactDto.setNumber("1234567890");

        ContactDto savedContact = contactRepository.save(contactDto);

        String url = "http://localhost:" + port + "/contacts/" + savedContact.getId();

        // Act
        restTemplate.delete(url);

        // Assert
        assertFalse(contactRepository.findById(savedContact.getId()).isPresent());
    }
}
