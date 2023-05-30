package com.contact.contact.controller;

import com.contact.contact.model.ContactDto;
import com.contact.contact.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
    public void testSaveContact_RedirectsToRoot() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh");
        contactDto.setEmail("sheikh@domain.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/saveContact")
                .flashAttr("contactDto", contactDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        verify(contactRepository).save(contactDto);
    }

    @Test
    public void testSaveContact_MissingName() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setEmail("sheikh@domain.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/saveContact")
                .flashAttr("contactDto", contactDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(contactRepository, times(1)).save(eq(contactDto));
    }

    @Test
    public void testSaveContact_MissingEmail() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Sheikh");
        contactDto.setEmail(null); // Set the email to null

        mockMvc.perform(MockMvcRequestBuilders.post("/saveContact")
                .flashAttr("contactDto", contactDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(contactRepository, times(1)).save(eq(contactDto));
    }

}
