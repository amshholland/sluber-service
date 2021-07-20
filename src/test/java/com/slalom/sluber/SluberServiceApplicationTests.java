package com.slalom.sluber;

import com.slalom.sluber.model.Trip;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class SluberServiceApplicationTests {
	private static HttpHeaders headers;
	private static final String URL_PREFIX = "/";
	private static final String ORIGIN = "Seattle Slalom HQ";
	private String trip1Id;
	private String trip2Id;
	private String trip3Id;

	@Autowired
	private TestRestTemplate restTemplate;


	@BeforeAll
	static void beforeAll() {
		headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
	}

	@Disabled
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
		newTrip.setTripId();
		trip1Id = newTrip.getTripId();
		newTrip.setSeatsAvailable(3);
		newTrip.setPassengers(p);

		HttpEntity<Trip> entity = new HttpEntity<>(newTrip, headers);

		ResponseEntity<Trip> response =
				restTemplate.postForEntity(URL_PREFIX + "sluber" + URL_PREFIX + "trips", entity, Trip.class);

		Trip trip = response.getBody();

		assertAll(
				() -> assertEquals(ORIGIN, trip.getOrigin()),
				() -> assertEquals("Tijuana", trip.getDestination()),
				() -> assertEquals(3, trip.getSeatsAvailable())
		);
	}

	@Disabled
	@Test
	void getAllTrips() {
		ArrayList<String> p = new ArrayList<String>();
		p.add("Nadia");
		Trip newTrip = new Trip();
		newTrip.setOrigin(ORIGIN);
		newTrip.setDepartureTime("10 October 10:00:00 AM");
		newTrip.setDestination("Vancouver");
		newTrip.setDriver("Hana");
		newTrip.setArrivalTime("12 October 12:00:00 PM");
		newTrip.setTripId();
		trip2Id = newTrip.getTripId();
		newTrip.setSeatsAvailable(4);
		newTrip.setPassengers(p);

		HttpEntity<Trip> entity = new HttpEntity<>(newTrip, headers);
		ResponseEntity<Trip> response = restTemplate.postForEntity(URL_PREFIX + "sluber" + URL_PREFIX + "trips", entity, Trip.class);

		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("Caroll");
		p2.add("Karen");
		Trip newTrip2 = new Trip();
		newTrip2.setOrigin(ORIGIN);
		newTrip2.setDepartureTime("10 October 10:00:00 AM");
		newTrip2.setDestination("Manager");
		newTrip2.setDriver("Erik");
		newTrip2.setArrivalTime("12 October 12:00:00 PM");
		newTrip2.setTripId();
		trip3Id = newTrip2.getTripId();
		newTrip2.setSeatsAvailable(2);
		newTrip2.setPassengers(p2);

		HttpEntity<Trip> entity2 = new HttpEntity<>(newTrip2, headers);
		ResponseEntity<Trip> response2 = restTemplate.postForEntity(URL_PREFIX + "sluber" + URL_PREFIX + "trips", entity2, Trip.class);

		ResponseEntity<Trip[]> responseAllTrips = restTemplate.getForEntity(URL_PREFIX + "sluber" + URL_PREFIX + "trips", Trip[].class);
		Trip[] trips = responseAllTrips.getBody();
		assertAll(
				() -> assertNotNull(trips),
				() -> assertNotNull(trips[0]),
				() -> assertNotNull(trips[1])

		);


	}

	// TODO: add more test coverage [SSU-69]
}