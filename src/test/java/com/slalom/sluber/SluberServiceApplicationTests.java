package com.slalom.sluber;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import com.slalom.sluber.api.models.TripDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SluberServiceApplicationTests {
    private static HttpHeaders headers;
    private static final String URL_PREFIX = "/";
    private static final String ORIGIN = "Seattle Slalom HQ";

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
    void verifyGetTrips() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<TripDetails>> response =
                restTemplate.exchange(URL_PREFIX + "/sluber/trips", HttpMethod.GET, entity,
                        new ParameterizedTypeReference<List<TripDetails>>() {
                        });
        List<TripDetails> trips = response.getBody();
        assertAll(
                () -> assertEquals(3, trips.size()),
                () -> assertEquals("tripId-1", trips.get(0).getTripId())
        );
    }

    @Test
    void createTrip() {
        CreateTripDetails createTripDetails = new CreateTripDetails();
        createTripDetails.setOrigin(ORIGIN);
        HttpEntity<CreateTripDetails> entity = new HttpEntity<>(createTripDetails, headers);

        ResponseEntity<TripDetails> response =
                restTemplate.postForEntity(URL_PREFIX + "/sluber/trips", entity, TripDetails.class);

        TripDetails trip = response.getBody();

        assertAll(
                () -> assertEquals(String.valueOf(0), trip.getTripId()),
                () -> assertEquals(ORIGIN, trip.getOrigin())
        );
    }

    @Test
    void addPassengerToTrip() {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setName("Passenger");
        employeeDetails.setPhoneNumber("123-123-1234");
        HttpEntity<TripDetails> entity = new HttpEntity(employeeDetails, headers);

        ResponseEntity<TripDetails> response =
                restTemplate.exchange(URL_PREFIX + "/sluber/trips/tripId-3", HttpMethod.PUT, entity, TripDetails.class);

        TripDetails trip = response.getBody();

        assertAll(
                () -> assertEquals(String.valueOf(0), trip.getTripId()),
                () -> assertEquals(ORIGIN, trip.getOrigin()),
                () -> assertEquals(1, trip.getPassengers().size())
        );
    }
}
