package com.slalom.sluber.controllers;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import com.slalom.sluber.services.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripControllerTests {
    private static TripController tripController;

    private static TripService mockTripService;

    private static final String TRIP_ID = "tripId-1";
    private static final String ORIGIN = "Slalom HQ, Seattle";
    private static final String DEPARTURE_TIME = "2021-10-21T17:32:28Z";
    private static final String COMMENTS = "Willing to pick people up anywhere in downtown Seattle.";
    private static final int SEATS_AVAILABLE = 3;
    private static final String NAME = "Todd S";
    private static final String PHONE_NUMBER = "555-555-1234";
    private static final String DESTINATION = "Quarterly, Westin Bellevue";
    private static final String ARRIVAL_TIME = "2021-10-21T18:32:28Z";
    private static final String PASSENGER1_NAME = "Pragathi S";
    private static final String PASSENGER1_PHONE_NUMBER = "666-555-4444";
    private static final String PASSENGER2_NAME = "Anthony S";
    private static final String PASSENGER2_PHONE_NUMBER = "111-222-3333";


    @BeforeAll
    static void beforeAll() {
        mockTripService = mock(TripService.class);

        tripController = new TripController(mockTripService);
    }


    @Test
    public void verifyCreateTrip() {
        // Arrange
        CreateTripDetails createTripDetails = new CreateTripDetails();
        createTripDetails.setOrigin(ORIGIN);
        createTripDetails.setDepartureTime(OffsetDateTime.parse(DEPARTURE_TIME));
        createTripDetails.setComments(COMMENTS);
        createTripDetails.setSeatsAvailable(SEATS_AVAILABLE);
        EmployeeDetails driver = new EmployeeDetails();
        driver.setName(NAME);
        driver.setPhoneNumber(PHONE_NUMBER);
        createTripDetails.setDriver(driver);
        createTripDetails.setDestination(DESTINATION);
        createTripDetails.setArrivalTime(OffsetDateTime.parse(ARRIVAL_TIME));
        EmployeeDetails passenger1 = new EmployeeDetails();
        passenger1.setName(PASSENGER1_NAME);
        passenger1.setPhoneNumber(PASSENGER1_PHONE_NUMBER);
        EmployeeDetails passenger2 = new EmployeeDetails();
        passenger2.setName(PASSENGER2_NAME);
        passenger2.setPhoneNumber(PASSENGER2_PHONE_NUMBER);
        List<EmployeeDetails> passengers = new ArrayList<EmployeeDetails>();
        passengers.add(passenger1);
        passengers.add(passenger2);
        createTripDetails.setPassengers(passengers);
        createTripDetails.setOriginator(CreateTripDetails.OriginatorEnum.DRIVER);


        TripDetails tripDetails = new TripDetails();
        tripDetails.setTripId(TRIP_ID);
        tripDetails.setOrigin(ORIGIN);
        tripDetails.setDepartureTime(OffsetDateTime.parse(DEPARTURE_TIME));
        tripDetails.setComments(COMMENTS);
        tripDetails.setSeatsAvailable(SEATS_AVAILABLE);
        tripDetails.setDriver(driver);
        tripDetails.setDestination(DESTINATION);
        tripDetails.setArrivalTime(OffsetDateTime.parse(ARRIVAL_TIME));
        tripDetails.setPassengers(passengers);
        tripDetails.setOriginator(TripDetails.OriginatorEnum.DRIVER);

        when(mockTripService.createTrip(createTripDetails)).thenReturn(tripDetails);

        // Act
        ResponseEntity<TripDetails> response = tripController.createTrip(createTripDetails);

        // Assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(TRIP_ID, response.getBody().getTripId());
        assertEquals(ORIGIN, response.getBody().getOrigin());
        assertEquals(OffsetDateTime.parse(DEPARTURE_TIME), response.getBody().getDepartureTime());
        assertEquals(OffsetDateTime.parse(ARRIVAL_TIME), response.getBody().getArrivalTime());
        assertEquals(DESTINATION, response.getBody().getDestination());
        assertEquals(TripDetails.OriginatorEnum.DRIVER, response.getBody().getOriginator());
        assertEquals(passengers, response.getBody().getPassengers());
        assertEquals(driver, response.getBody().getDriver());
        assertEquals(COMMENTS, response.getBody().getComments());
    }
}
