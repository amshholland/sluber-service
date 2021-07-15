package com.slalom.sluber;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.model.Trip;
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

import java.time.OffsetDateTime;
import java.time.Clock;
import java.util.ArrayList;
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

//	@Test
//	void verifyGetTrips() {
//		HttpEntity<String> entity = new HttpEntity<>(headers);
//		ResponseEntity<List<Trip>> response =
//				restTemplate.exchange(URL_PREFIX + "/trip", HttpMethod.GET, entity,
//						new ParameterizedTypeReference<List<Trip>>() {
//						});
//		List<Trip> trips = response.getBody();
//		assertAll(
//				() -> assertEquals(2, trips.size()),
//				() -> assertEquals("30e11873-e7f4-4a79-b4dc-f9f848a475e9", trips.get(0).getId()),
//				() -> assertEquals("Slalom Hq, Seattle", trips.get(0).getOrigin()),
//				() -> assertEquals(OffsetDateTime.parse("2021-10-21T17:32:28Z"), trips.get(0).getDepartureTime()),
//				() -> assertEquals("Willing to pick people up anywhere in downtown Seattle.", trips.get(0).getComments()),
//				() -> assertEquals(3, trips.get(0).getSeatsAvailable()),
//				() -> assertEquals("Quarterly, Westin Bellevue", trips.get(0).getDestination()),
//				() -> assertEquals(OffsetDateTime.parse("2021-10-21T18:32:28Z"), trips.get(0).getArrivalTime())
//		);
//	}

    @Test
    void createTrip() {
		ArrayList<String> p = new ArrayList<String>();
		p.add("Ali");
		p.add("Henry");
        Trip newTrip = new Trip();
        newTrip.setOrigin(ORIGIN);
        newTrip.setDepartureTime("10 October 10:00:00 AM");
        newTrip.setDestination("Tijuana");
        newTrip.setDriver("Jax");
        newTrip.setArrivalTime("12 October 12:00:00 PM");
        newTrip.setId();
        newTrip.setSeatsAvailable(3);
        newTrip.setPassengers(p);

		HttpEntity<Trip> entity = new HttpEntity<>(newTrip, headers);

        ResponseEntity<Trip> response =
                restTemplate.postForEntity(URL_PREFIX + "/trip", entity, Trip.class);

        Trip trip = response.getBody();

        assertAll(
                () -> assertEquals(ORIGIN, trip.getOrigin()),
				() -> assertEquals("Tijuana", trip.getDestination()),
				() -> assertEquals(3, trip.getSeatsAvailable())
		);
    }

//    @Test
//    void addPassengerToTrip() {
//        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setName("Passenger");
//        employeeDetails.setPhoneNumber("123-123-1234");
//        HttpEntity<TripDetails> entity = new HttpEntity(employeeDetails, headers);
//
//        ResponseEntity<TripDetails> response =
//                restTemplate.exchange(URL_PREFIX + "/sluber/trips/tripId-3/add-passenger", HttpMethod.PUT, entity, TripDetails.class);
//
//        TripDetails trip = response.getBody();
//
//        assertAll(
//                () -> assertEquals("tripId-3", trip.getTripId()),
//                () -> assertEquals("Slalom Hq, Seattle", trip.getOrigin()),
//				() -> assertEquals(1, trip.getPassengers().size()),
//				() -> assertEquals("Passenger", trip.getPassengers().get(0).getName()),
//				() -> assertEquals("123-123-1234", trip.getPassengers().get(0).getPhoneNumber())
//        );
//    }
}
