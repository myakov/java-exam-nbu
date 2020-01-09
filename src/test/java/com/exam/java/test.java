package com.exam.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exam.java.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class test {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllStudents() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/students",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Student student = restTemplate.getForObject(getRootUrl() + "/api/v1/students/1", Student.class);
        System.out.println(student.getFirstName());
        assertNotNull(student);
        System.out.println(student);
    }

    @Test
    public void testCreateEmployee() {
        Student student = new Student();
        student.setEmailId("admin@gmail.com");
        student.setFirstName("admin");
        student.setLastName("admin");

        ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/student/", student, Student.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Student student = restTemplate.getForObject(getRootUrl() + "/api/v1/students/" + id, Student.class);
        student.setFirstName("admin1");
        student.setLastName("admin2");

        restTemplate.put(getRootUrl() + "/api/v1/students/" + id, student);

        Student updatedStudent = restTemplate.getForObject(getRootUrl() + "/api/v1/students/" + id, Student.class);
        assertNotNull(updatedStudent);
        System.out.println(student);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Student student = restTemplate.getForObject(getRootUrl() + "/api/v1/students/" + id, Student.class);
        assertNotNull(student);

        restTemplate.delete(getRootUrl() + "/api/v1/students/" + id);

        try {
            student = restTemplate.getForObject(getRootUrl() + "/api/v1/students/" + id, Student.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
