package com.slalom.sluber;

import com.slalom.sluber.model.Trip;
import org.junit.jupiter.api.BeforeAll;
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
				restTemplate.postForEntity(URL_PREFIX + "sluber" + URL_PREFIX + "trips", entity, Trip.class);

		Trip trip = response.getBody();

		assertAll(
				() -> assertEquals(ORIGIN, trip.getOrigin()),
				() -> assertEquals("Tijuana", trip.getDestination()),
				() -> assertEquals(3, trip.getSeatsAvailable())
		);
	}

	// TODO: add more test coverage [SSU-69]
}