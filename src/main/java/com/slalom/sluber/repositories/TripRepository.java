package com.slalom.sluber.repositories;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
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
        trip1.setTripId("tripId-1");
        trip1.setOrigin("Slalom Hq, Seattle");

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