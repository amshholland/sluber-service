package com.slalom.sluber.repositories;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripRepositoryTests {
    private static TripRepository tripRepository;
    private static Clock mockClock;

    private final static String ORIGIN = "Slalom HQ, Seattle";

    @BeforeAll
    static void beforeAll() {
        mockClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        tripRepository = new TripRepository(mockClock);
    }

    @Test
    void createTripTest() {
        CreateTripDetails createTripDetails = new CreateTripDetails();
        createTripDetails.setOrigin(ORIGIN);


        TripDetails trip = tripRepository.createTrip(createTripDetails);

        assertAll(
                () -> assertEquals(String.valueOf(mockClock.millis()), trip.getTripId()),
                () -> assertEquals(ORIGIN, trip.getOrigin())
        );
    }
}
