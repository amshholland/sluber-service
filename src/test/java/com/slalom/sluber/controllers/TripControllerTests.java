package com.slalom.sluber.controllers;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.services.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripControllerTests {
    private static TripController tripController;

    private static TripService mockTripService;

    private static final String TRIP_ID = "tripId-1";
    private static final String ORIGIN = "Slalom HQ, Seattle";


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

        TripDetails tripDetails = new TripDetails();
        tripDetails.setTripId(TRIP_ID);
        tripDetails.setOrigin(ORIGIN);

        when(mockTripService.createTrip(createTripDetails)).thenReturn(tripDetails);

        // Act
        ResponseEntity<TripDetails> response = tripController.createTrip(createTripDetails);

        // Assert
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(TRIP_ID, response.getBody().getTripId());
        assertEquals(ORIGIN, response.getBody().getOrigin());
    }
}
