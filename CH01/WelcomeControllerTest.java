package com.springmvctutorial.springboot_springmvc_first_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WelcomeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testWelcomeEndpoint() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Welcome to Spring MVC Course"));
    }

    @Test
    public void testTodayEndpointWithDate() throws Exception {
        mockMvc.perform(get("/today")
                        .param("date", "2024/12/09")) // Pass a custom date
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Today's date is: 09-12-2024"));
    }

    @Test
    public void testTodayEndpointWithoutDate() throws Exception {
        String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        mockMvc.perform(get("/today")) // No date parameter
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Today's date is: " + today));
    }

    @Test
    public void testInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/today")
                        .param("date", "09/12/2024")) // Invalid date format
                .andExpect(status().isBadRequest())
                .andExpect((ResultMatcher) content().string("Invalid date format. Please use 'yyyy/MM/dd'."));
    }

    @Test
    public void testRedirectEndpoint() throws Exception {
        mockMvc.perform(get("/redirect"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/greeting?name=Spring"));
    }
}
