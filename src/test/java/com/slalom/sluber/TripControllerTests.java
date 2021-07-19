package com.slalom.sluber;

import com.slalom.sluber.model.Trip;
import com.slalom.sluber.repositories.TripRepository;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripControllerTests {

    @Test
    public void testGetAllTrips() {
        ArrayList<Trip> mockTripList = new ArrayList<Trip>();

        ArrayList<String> p = new ArrayList<String>();
        p.add("Ali");
        p.add("Henry");
        Trip mockTestTrip = new Trip();
        mockTestTrip.setOrigin("Slalom HQ");
        mockTestTrip.setDepartureTime("10 October 10:00:00 AM");
        mockTestTrip.setDestination("Tijuana");
        mockTestTrip.setDriver("Jax");
        mockTestTrip.setArrivalTime("12 October 12:00:00 PM");
        mockTestTrip.setId();
        mockTestTrip.setSeatsAvailable(3);
        mockTestTrip.setPassengers(p);

        ArrayList<String> p2 = new ArrayList<String>();
        p2.add("Nadia");
        p2.add("Jax");
        Trip mockTestTrip2 = new Trip();
        mockTestTrip2.setOrigin("Slalom Houston HQ");
        mockTestTrip2.setDepartureTime("10 October 10:00:00 AM");
        mockTestTrip2.setDestination("Vancouver");
        mockTestTrip2.setDriver("Hana");
        mockTestTrip2.setArrivalTime("12 October 12:00:00 PM");
        mockTestTrip2.setId();
        mockTestTrip2.setSeatsAvailable(4);
        mockTestTrip2.setPassengers(p2);

        mockTripList.add(mockTestTrip);
        mockTripList.add(mockTestTrip2);

        TripRepository mockTripRepo = mock(TripRepository.class);
        when(mockTripRepo.getAllTrips()).thenReturn(mockTripList);
    }

}
