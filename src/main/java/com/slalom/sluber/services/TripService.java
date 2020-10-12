package com.slalom.sluber.services;

import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.EmployeeDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.exceptions.NotFoundException;
import com.slalom.sluber.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /**
     * When we have business logic on trip creation, it would live here
     *
     * @param createTripDetails a new trip
     * @return the new trip
     */
    public TripDetails createTrip(CreateTripDetails createTripDetails) {
        return this.tripRepository.createTrip(createTripDetails);
    }

    /**
     * When we have business logic on trip creation, it would live here
     *
     * @return all trips
     */
    public List<TripDetails> getTrips() {
        return this.tripRepository.getTrips();
    }

    /**
     * When we have business logic on trip creation, it would live here
     *
     * @return all trip by its id
     */
    public TripDetails getTrip(String tripId) {
        return this.tripRepository.getTrip(tripId);
    }

    /**
     * Adds a passenger to a trip
     *
     * @param tripId    id of the trip to add a passenger to
     * @param passenger passenger details
     * @return updated trip, with passenger
     */
    public TripDetails addPassenger(String tripId, EmployeeDetails passenger) {
        TripDetails trip = getTrip(tripId);
        if (trip == null) {
            throw new NotFoundException();
        }
        trip.getPassengers().add(passenger);
        trip.setSeatsAvailable(trip.getSeatsAvailable() - 1);
        return this.tripRepository.updateTrip(trip);
    }
}