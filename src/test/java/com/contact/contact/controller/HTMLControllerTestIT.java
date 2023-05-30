package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.*;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class HTMLControllerTestIT {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7")
            .withDatabaseName("mydb")
            .withUsername("root")
            .withPassword("password")
            .withReuse(true);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeClass
    public static void beforeAll() {
        mysql.start();
    }

    @AfterClass
    public static void afterAll() {
        mysql.stop();
    }

    @Before
    public void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void testSaveContact() {
        // Arrange
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh Saad");
        contactDto.setEmail("sheikh@domain.com");
        contactDto.setNumber("1234567890");

        String url = "http://localhost:" + port + "/saveContact";

        // Act
        ResponseEntity<Void> response = restTemplate.postForEntity(url, contactDto, Void.class);

        // Assert
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/"));
        assertNotNull(contactRepository.findById(1L).orElse(null));
    }
}
