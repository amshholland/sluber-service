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
        // Trip 1 has passsangers and a driver
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

        // Trip 2 has a passenger but no driver
        EmployeeDetails employee2 = new EmployeeDetails();
        TripDetails trip2 = new TripDetails();
        trip2.setTripId("tripId-2");
        trip2.setOrigin("Microsoft Campus");
        trip2.setDepartureTime(OffsetDateTime.parse("2021-11-21T17:32:28Z"));
        trip2.setComments("Willing to pick people up anywhere in Redmond.");
        trip2.setSeatsAvailable(2);
        employee2.setName("David K");
        employee2.setPhoneNumber("555-555-4321");
        trip2.setPassengers(Arrays.asList(employee2));
        trip2.setDestination("Slalom HQ, Seattle");
        trip2.setArrivalTime(OffsetDateTime.parse("2021-11-21T18:32:28Z"));
        trip2.setOriginator(TripDetails.OriginatorEnum.PASSENGER);
        trips.put("tripId-2", trip2);

        // Trip 3 has a driver but no passengers
        TripDetails trip3 = new TripDetails();
        trip3.setTripId("tripId-3");
        trip3.setOrigin("Slalom Hq, Seattle");
        trip3.setDepartureTime(OffsetDateTime.parse("2021-10-31T17:32:28Z"));
        trip3.setComments("Willing to pick people up anywhere in downtown Seattle.");
        trip3.setSeatsAvailable(3);
        EmployeeDetails employee3 = new EmployeeDetails();
        employee3.setName("Elizabeth S");
        employee3.setPhoneNumber("555-123-1234");
        trip3.setDestination("Quarterly, Westin Bellevue");
        trip3.setArrivalTime(OffsetDateTime.parse("2021-10-31T18:32:28Z"));
        trip3.setDriver(employee3);
        trip3.setOriginator(TripDetails.OriginatorEnum.DRIVER);
        trip3.setPassengers(new ArrayList<>());
        trips.put("tripId-3", trip3);
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
        List<EmployeeDetails> passengers = new ArrayList<EmployeeDetails>();
        if (createTripDetails.getOriginator() == CreateTripDetails.OriginatorEnum.DRIVER) {
            tripDetails.setOriginator(TripDetails.OriginatorEnum.DRIVER);
            tripDetails.setDriver(createTripDetails.getDriver());
        } else {
            tripDetails.setOriginator(TripDetails.OriginatorEnum.PASSENGER);
            passengers.add(createTripDetails.getDriver());
        }
        tripDetails.setPassengers(passengers);
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

    /**
     * Gets a trip by its id
     *
     * @return trip
     */
    public TripDetails getTrip(String tripId) {
        return trips.get(tripId);
    }

    /**
     * Updates a trip
     *
     * @return trip
     */
    public TripDetails updateTrip(TripDetails updatedTrip) {
        return trips.put(updatedTrip.getTripId(), updatedTrip);
    }
}
