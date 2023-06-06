package com.contact.contact.integration;

import com.contact.contact.entity.ContactEntity;
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
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName("Sheikh Saad");
        contactEntity.setEmail("sheikh@domain.com");
        contactEntity.setNumber("1234567890");

        String url = "http://localhost:" + port + "/contacts";

        // Act
        ResponseEntity<ContactEntity> response = restTemplate.postForEntity(url, contactEntity, ContactEntity.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ContactEntity createdContact = response.getBody();
        assertNotNull(createdContact.getId());
    }

    @Test
    public void testGetContactById() {
        // Arrange
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName("Sheikh Saad");
        contactEntity.setEmail("sheikh@domain.com");
        contactEntity.setNumber("9876543210");
        ContactEntity savedContact = contactRepository.save(contactEntity);

        String url = "http://localhost:" + port + "/contacts/" + savedContact.getId();

        // Act
        ResponseEntity<ContactEntity> response = restTemplate.getForEntity(url, ContactEntity.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContactEntity fetchedContact = response.getBody();
        assertEquals(savedContact.getId(), fetchedContact.getId());
    }

    @Test
    public void testGetAllContacts() {
        // Arrange
        ContactEntity contact1 = new ContactEntity();
        contact1.setName("Sheikh Saad");
        contact1.setEmail("sheikh@domain.com");
        contact1.setNumber("1234567890");

        ContactEntity contact2 = new ContactEntity();
        contact2.setName("Sheikh Saad");
        contact2.setEmail("sheikh@domain.com");
        contact2.setNumber("9876543210");

        contactRepository.save(contact1);
        contactRepository.save(contact2);

        String url = "http://localhost:" + port + "/contacts";

        // Act
        ResponseEntity<ContactEntity[]> response = restTemplate.getForEntity(url, ContactEntity[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContactEntity[] contacts = response.getBody();
        assertNotNull(contacts);
        assertEquals(2, contacts.length);
    }

    @Test
    public void testDeleteContact() {
        // Arrange
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName("Sheikh Saad");
        contactEntity.setEmail("sheikh@domain.com");
        contactEntity.setNumber("1234567890");

        ContactEntity savedContact = contactRepository.save(contactEntity);

        String url = "http://localhost:" + port + "/contacts/" + savedContact.getId();

        // Act
        restTemplate.delete(url);

        // Assert
        assertFalse(contactRepository.findById(savedContact.getId()).isPresent());
    }
}
