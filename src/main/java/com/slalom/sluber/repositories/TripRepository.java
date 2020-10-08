package com.slalom.sluber.repositories;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.OffsetDateTime;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class TripRepository {
    private Map<String, TripDetails> trips;
    private Clock clock;

    public TripRepository(Clock clock) {
        trips = new HashMap<>();
        this.clock = clock;
    }

    @PostConstruct
    public void init() {
        EmployeeDetails employee1 = new EmployeeDetails();
        TripDetails trip1 = new TripDetails();
        EmployeeDetails passenger1 = new EmployeeDetails();
        EmployeeDetails passenger2 = new EmployeeDetails();
        List<EmployeeDetails> passengers = new ArrayList<EmployeeDetails>();
        trip1.setTripId("tripId-1");
        trip1.setOrigin("Slalom Hq, Seattle");
        trip1.setDepartureTime(OffsetDateTime.parse("2021-10-21T17:32:28Z"));
        trip1.setComments("Willing to pick people up anywhere in downtown Seattle.");
        trip1.setSeatsAvailable(3);
        passenger1.setName("Pragathi S");
        passenger1.setPhoneNumber("666-555-4444");
        passenger2.setName("Anthony S");
        passenger2.setPhoneNumber("111-222-3333");
        passengers.add(passenger1);
        passengers.add(passenger2);
        trip1.setPassengers(passengers);
        employee1.setName("Todd S");
        employee1.setPhoneNumber("555-555-1234");
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
        tripDetails.setDestination(createTripDetails.getDestination());
        tripDetails.setDepartureTime(createTripDetails.getDepartureTime());
        tripDetails.setComments(createTripDetails.getComments());
        if (createTripDetails.getOriginator() == CreateTripDetails.OriginatorEnum.DRIVER) {
            tripDetails.setOriginator(TripDetails.OriginatorEnum.DRIVER);
        } else {
            tripDetails.setOriginator(TripDetails.OriginatorEnum.PASSENGER);
        }
        tripDetails.setDriver(createTripDetails.getDriver());
        if (createTripDetails.getOriginator() == CreateTripDetails.OriginatorEnum.DRIVER) {
            tripDetails.setSeatsAvailable(createTripDetails.getSeatsAvailable());
            tripDetails.setArrivalTime(createTripDetails.getArrivalTime());
        }

        this.trips.put(tripId, tripDetails);

        return tripDetails;
    }



    /**
     * Method to generate a unique trip id.
     *
     * @return unique number based on current time
     */
    private String generateTripId() {
        return String.valueOf(this.clock.millis());
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
