package com.slalom.sluber.repositories;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class TripRepository {
    private Map<String, TripDetails> trips;

    public TripRepository() {
        trips = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        TripDetails trip1 = new TripDetails();
        EmployeeDetails passenger1 = new EmployeeDetails();
        EmployeeDetails passenger2 = new EmployeeDetails();
        List<EmployeeDetails> passengers = new ArrayList<EmployeeDetails>();
        trip1.setTripId("tripId-1");
        trip1.setOrigin("Slalom Hq, Seattle");
        trip1.setDepartureTime("2021-010-21T17:32:28Z");
        trip1.setComments("Willing to pick people up anywhere in downtown Seattle.");
        trip1.setSeatsAvailable(3);
        passenger1.setName("Pragathi S");
        passenger1.setPhoneNumber("666-555-4444");
        passenger2.setName("Anthony S");
        passenger2.setPhoneNumber("111-222-3333");
        passengers.add(passenger1);
        passengers.add(passenger2);
        trip1.setPassengers(passengers);

        trips.put("tripId-1", trip1);
    }

    /**
     * Stores a new trip.  Generates a tripId
     *
     * @param createTripDetails a new trip
     * @return the new trip, with an id
     */
    public TripDetails createTrip(CreateTripDetails createTripDetails) {
        String tripId = generateTripId();
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTripId(tripId);
        tripDetails.setOrigin(createTripDetails.getOrigin());
        tripDetails.setDepartureTime(createTripDetails.getDepartureTime());
        tripDetails.setComments(createTripDetails.getComments());
        tripDetails.setSeatsAvailable(createTripDetails.getSeatsAvailable());
        tripDetails.setPassengers(createTripDetails.getPassengers());

        this.trips.put(tripId, tripDetails);

        return tripDetails;
    }



    /**
     * Method to generate a unique trip id.  It's public so we can mock it in tests
     *
     * @return unique number based on current time
     */
    public String generateTripId() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * Gets all trips
     *
     * @return all trips
     */
    public List<TripDetails> getTrips() {
        return new ArrayList(trips.values());
    }
}
