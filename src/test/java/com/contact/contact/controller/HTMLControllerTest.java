package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HTMLControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactRepository contactRepository;

    private HTMLController htmlController;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        htmlController = new HTMLController(contactRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(htmlController).build();
    }

    @Test
    public void testSaveContact() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh");
        contactDto.setEmail("sheikh@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/saveContact")
                .flashAttr("contactDto", contactDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        // Verify that the contactRepository.save() method is called
        Mockito.verify(contactRepository).save(contactDto);
    }
}
