package com.slalom.sluber.services;

import com.slalom.sluber.api.models.EmployeeDetails;
import com.slalom.sluber.exceptions.NotFoundException;
import com.slalom.sluber.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTests {
    private TripRepository mockTripRepository;
    private TripService tripService;

    private static final String TRIP_ID = "my trip id";
    private static final String NAME = "Todd S";
    private static final String PHONE = "555-867-5309";

    @BeforeEach
    void beforeEach() {
        this.mockTripRepository = mock(TripRepository.class);
        this.tripService = new TripService(mockTripRepository);
    }

    @Test
    // Verify that exception is thrown when trip is not found in the repo
    void exceptionThrown_tripIsNotFound() {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setName(NAME);
        employeeDetails.setPhoneNumber(PHONE);
        when(mockTripRepository.getTrip(TRIP_ID)).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> tripService.addPassenger(TRIP_ID, employeeDetails)
        );
    }
}
