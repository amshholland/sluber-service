package com.slalom.sluber.controllers;
//import com.slalom.sluber.Dao.PersonRepository;
import com.slalom.sluber.model.Trip;
import com.slalom.sluber.repositories.TripRepositoryNew;
import com.slalom.sluber.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//the controller is what first handles the requests from the clients
//we specify and enable this to be a rest controller
//have some methods and expose some endpoints that clients can consume / hit
//define the address of the endpoint with Request mapping
//@RequestMapping("/api/person")
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class TripController2 {

    private final TripService tripService;
    //Dependency injection with the person repository
    private final TripRepositoryNew tripRepository;
    //with at autowired srpingboot injects the actual service into the constructor
    @Autowired
    public TripController2(TripService tripService, TripRepositoryNew tripRepository) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
    }
    //want this to be served as post request
    //include @request body to tell spring that this person will be created from content
    //in the request body?
    //@PostMapping
    //public void addTrip(@RequestBody Trip trip) {
    //    tripService.addTrip(trip);
    //}

    //when we get a post request we interperate the request body as a Peson object.
    //in the person Repository that we injected as a property, we do save that person - which triggers the
    //reposityr code to save the person. can split this up further into a service to reduce coupling.
    @PostMapping("/trip")
    public Trip saveTrip(@RequestBody Trip trip) {
        return tripRepository.saveTripToDB(trip);
    }

    //Tell Spring that this will be a get request
    @GetMapping("/trip")
    public List<Trip> getAllTrips() {
        return tripRepository.getAllTrips();
    }

    /*@GetMapping
    public Trip getTripById(String tripId) {
        return TripService.getTripById(tripId);
    }*/
}