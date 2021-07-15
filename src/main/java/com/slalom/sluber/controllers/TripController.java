package com.slalom.sluber.controllers;
import com.slalom.sluber.model.Trip;
import com.slalom.sluber.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//the controller is what first handles the requests from the clients
//we specify and enable this to be a rest controller
//have some methods and expose some endpoints that clients can consume / hit
//define the address of the endpoint with Request mapping

//Only real difference between TC2 and TC is TC2 doesn't "implement Sluber API"
@CrossOrigin("http://localhost:3000")
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
    public Trip saveTrip(@RequestBody Trip trip) {
            return tripRepository.saveTripToDB(trip);
    }

    //TODO: Add a passenger to a trip
}