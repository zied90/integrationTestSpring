package com.example.demospringsecurityintegrtiontest;

import com.example.demospringsecurityintegrtiontest.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemospringsecurityintegrtiontestApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
    @WithMockUser("/zied-1")
    @Test
    public void testSavePerson() throws Exception {

        Person person = new Person(0, "user8", "CIVIL");
        String jsonRequest = mapper.writeValueAsString(person);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/savePerson").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());

    }
    @WithMockUser("/zied-1")
    @Test
    public void testgetPersons() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getAllPersons").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());

    }
    @Test
    public void testgetPersonsWithTestRestTemplate() {
        ResponseEntity<?> response = template.withBasicAuth("zied", "pwd").getForEntity("/getAllPersons",
                ArrayList.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
