package com.exam.java;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.exam.java.controller.StudentController;
import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Student;
import com.exam.java.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class OAuthIntegTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    StudentRepository studentRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;


    @Value("${security.jwt.client_id}")
    private String CLIENT_ID;

    @Value("${security.jwt.client_secret}")
    private String CLIENT_SECRET;


    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String EMAIL = "test";
    private static final String NAME = "test";
    private static int ID = 1;

    private static String accessToken = "";

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        accessToken = obtainAccessToken("milen.yakov", "jwtpass");
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        System.out.println(accessToken);
        mockMvc.perform(get("/students")).andExpect(status().isUnauthorized());
    }


    @Test
    public void testStudent() {
        // StudentController mock = org.mockito.Mockito.mock(StudentController.class);


        Student s = new Student();
        Student s2 = new Student();
        s.setId(2);
        s2.setId(3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(s);
        studentList.add(s2);


        when(studentController.getAllstudents()).thenReturn(studentList);


        List<Student> studentList2 = studentController.getAllstudents();

        verify(studentRepository).findAll();

        assertEquals(studentList, studentList2);


    }

    private String obtainAccessToken(String username, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
