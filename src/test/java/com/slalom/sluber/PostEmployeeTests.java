package com.slalom.sluber;

import com.slalom.sluber.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PostEmployeeTests {
    private static HttpHeaders headers;
    private static final String URL_PREFIX = "/";
    //private static final String ORIGIN = "Seattle Slalom HQ";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private Clock clock;

    @BeforeAll
    static void beforeAll() {
        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void createEmployee() {
        Employee employee = new Employee();
        employee.setName("Billy Joel");
        employee.setEmployeeId();
        employee.setPhoneNumber("111-222-3333");
        employee.setEmailAddress("TestEmployee11@gmail.com");

        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);

        ResponseEntity<Employee> response =
                restTemplate.postForEntity(URL_PREFIX + "sluber/employees", entity, Employee.class);

        Employee responseEmployee = response.getBody();

        System.out.println(responseEmployee);

        assertAll(
                () -> assertEquals("Billy Joel", responseEmployee.getName()),
                () -> assertEquals("111-222-3333", responseEmployee.getPhoneNumber()),
                () -> assertEquals("TestEmployee11@gmail.com", responseEmployee.getEmailAddress())
        );

    }

    /*TODO
		use Mockito to create a mock DynamoDB database so that starting state of testing database can be
		the same each time.
	*/


}
