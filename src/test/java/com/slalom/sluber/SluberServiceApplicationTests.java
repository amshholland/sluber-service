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

import java.time.OffsetDateTime;
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
				() -> assertEquals("tripId-1", trips.get(0).getTripId()),
				() -> assertEquals("Slalom Hq, Seattle", trips.get(0).getOrigin()),
				() -> assertEquals(OffsetDateTime.parse("2021-10-21T17:32:28Z"), trips.get(0).getDepartureTime()),
				() -> assertEquals("Willing to pick people up anywhere in downtown Seattle.", trips.get(0).getComments()),
				() -> assertEquals(3, trips.get(0).getSeatsAvailable()),
				() -> assertEquals("Quarterly, Westin Bellevue", trips.get(0).getDestination()),
				() -> assertEquals(OffsetDateTime.parse("2021-10-21T18:32:28Z"), trips.get(0).getArrivalTime()),
				() -> assertEquals("Pragathi S", trips.get(0).getPassengers().get(0).getName()),
				() -> assertEquals("666-555-4444", trips.get(0).getPassengers().get(0).getPhoneNumber()),
				() -> assertEquals("Anthony S", trips.get(0).getPassengers().get(1).getName()),
				() -> assertEquals("111-222-3333", trips.get(0).getPassengers().get(1).getPhoneNumber()),
				() -> assertEquals("Todd S", trips.get(0).getDriver().getName()),
				() -> assertEquals("555-555-1234", trips.get(0).getDriver().getPhoneNumber()),
				() -> assertEquals(TripDetails.OriginatorEnum.DRIVER, trips.get(0).getOriginator())
		);
	}

    @Test
    void createTrip() {
        CreateTripDetails createTripDetails = new CreateTripDetails();
        createTripDetails.setOrigin(ORIGIN);
        createTripDetails.setOriginator(CreateTripDetails.OriginatorEnum.PASSENGER);
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setName("Charmander");
		employeeDetails.setPhoneNumber("5558675309");
		createTripDetails.addPassengersItem(employeeDetails);

		HttpEntity<CreateTripDetails> entity = new HttpEntity<>(createTripDetails, headers);

        ResponseEntity<TripDetails> response =
                restTemplate.postForEntity(URL_PREFIX + "/sluber/trips", entity, TripDetails.class);

        TripDetails trip = response.getBody();

        assertAll(
                () -> assertEquals(String.valueOf(0), trip.getTripId()),
                () -> assertEquals(ORIGIN, trip.getOrigin()),
				() -> assertEquals(CreateTripDetails.OriginatorEnum.PASSENGER.toString(), trip.getOriginator().toString()),
				() -> assertEquals("Charmander", trip.getPassengers().get(0).getName()),
				() -> assertEquals("5558675309", trip.getPassengers().get(0).getPhoneNumber())
		);
    }

    @Test
    void addPassengerToTrip() {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setName("Passenger");
        employeeDetails.setPhoneNumber("123-123-1234");
        HttpEntity<TripDetails> entity = new HttpEntity(employeeDetails, headers);

        ResponseEntity<TripDetails> response =
                restTemplate.exchange(URL_PREFIX + "/sluber/trips/tripId-3/add-passenger", HttpMethod.PUT, entity, TripDetails.class);

        TripDetails trip = response.getBody();

        assertAll(
                () -> assertEquals("tripId-3", trip.getTripId()),
                () -> assertEquals("Slalom Hq, Seattle", trip.getOrigin()),
				() -> assertEquals(1, trip.getPassengers().size()),
				() -> assertEquals("Passenger", trip.getPassengers().get(0).getName()),
				() -> assertEquals("123-123-1234", trip.getPassengers().get(0).getPhoneNumber())
        );
    }
}
