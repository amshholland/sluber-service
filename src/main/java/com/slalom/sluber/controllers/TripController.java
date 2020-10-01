package com.slalom.sluber.controllers;

import com.slalom.sluber.api.TripsApi;
import com.slalom.sluber.api.models.CreateTripDetails;
import com.slalom.sluber.api.models.TripDetails;
import com.slalom.sluber.services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripController implements TripsApi {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public ResponseEntity<TripDetails> createTrip(CreateTripDetails createTripDetails) {
        TripDetails tripDetails = this.tripService.createTrip(createTripDetails);
        return ResponseEntity.ok(tripDetails);
    }

    @Override
    public ResponseEntity<List<TripDetails>> getTrips() {
        return ResponseEntity.ok(this.tripService.getTrips());
    }
}
