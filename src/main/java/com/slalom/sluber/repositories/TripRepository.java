package com.slalom.sluber.repositories;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;

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
        EmployeeDetails employee1 = new EmployeeDetails();
        TripDetails trip1 = new TripDetails();
        employee1.setName("Todd S");
        employee1.setPhoneNumber("555-555-1234");
        trip1.setTripId("tripId-1");
        trip1.setOrigin("Slalom Hq, Seattle");
        trip1.setDestination("Quarterly, Westin Bellevue");
        trip1.setArrivalTime(OffsetDateTime.parse("2021-10-21T18:32:28Z"));
        trip1.setDriver(employee1);
        trip1.setOriginator(TripDetails.OriginatorEnum.DRIVER);

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
