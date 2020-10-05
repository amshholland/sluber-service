package com.slalom.sluber;

import com.slalom.sluber.api.models.TripDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SluberServiceApplicationTests {
	private static HttpHeaders headers;
	private static final String URL_PREFIX = "/";

	@Autowired
	private TestRestTemplate restTemplate;

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
				() -> assertEquals(1, trips.size()),
				() -> assertEquals("tripId-1", trips.get(0).getTripId())
		);
	}
}
