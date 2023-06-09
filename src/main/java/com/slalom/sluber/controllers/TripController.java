package com.slalom.sluber.controllers;
import com.slalom.sluber.model.Employee;
import com.slalom.sluber.model.Trip;
import com.slalom.sluber.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//the controller is what first handles the requests from the clients
//we specify and enable this to be a rest controller
//have some methods and expose some endpoints that clients can consume / hit
//define the address of the endpoint with Request mapping

//Only real difference between TC2 and TC is TC2 doesn't "implement Sluber API"
@CrossOrigin("*")
@RestController
@RequestMapping("/sluber")
public class TripController {
    //private final TripService tripService;
    private final TripRepository tripRepository;

    //with at autowired srpingboot injects the actual service into the constructor
    @Autowired
    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    //Tell Spring that this will be a get request
    //get all trips from database
    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripRepository.getAllTrips();
    }

    //Save a trip to the database
    @PostMapping("/trips")
    public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
            return new ResponseEntity(tripRepository.saveTripToDB(trip), HttpStatus.OK);
    }

    @PutMapping("/trips/{tripId}")
    public Trip updateTrip(@RequestBody Trip trip, @PathVariable String tripId) {
        trip.setTripId(tripId);
        return tripRepository.updateTrip(trip, tripId);
    }
    //TODO: Add a passenger to a trip
}