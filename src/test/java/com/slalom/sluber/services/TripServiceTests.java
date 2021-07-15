//package com.slalom.sluber.services;
//
//import com.slalom.sluber.api.models.EmployeeDetails;
//import com.slalom.sluber.api.models.TripDetails;
//import com.slalom.sluber.exceptions.NotFoundException;
//import com.slalom.sluber.repositories.TripRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class TripServiceTests {
//    private TripRepository mockTripRepository;
//    private TripService tripService;
//
//    private static final String TRIP_ID = "my trip id";
//    private static final String NAME = "Todd S";
//    private static final String PHONE = "555-867-5309";
//
//    @BeforeEach
//    void beforeEach() {
//        this.mockTripRepository = mock(TripRepository.class);
//        this.tripService = new TripService(mockTripRepository);
//    }
//
//    @Test
//    // Verify that exception is thrown when trip is not found in the repo
//    void exceptionThrown_tripIsNotFound() {
//        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setName(NAME);
//        employeeDetails.setPhoneNumber(PHONE);
//        when(mockTripRepository.getTrip(TRIP_ID)).thenReturn(null);
//
//        assertThrows(NotFoundException.class,
//                () -> tripService.addPassenger(TRIP_ID, employeeDetails)
//        );
//    }
//
//    @Test
//    void seatCountDecrementedAndPassengerAdded_passengerIsAdded() {
//        TripDetails trip = new TripDetails();
//        trip.setSeatsAvailable(5);
//        trip.setPassengers(new ArrayList<>());
//        when(mockTripRepository.getTrip("trip1")).thenReturn(trip);
//        when(mockTripRepository.updateTrip(trip)).thenReturn(trip);
//
//        EmployeeDetails passenger = new EmployeeDetails();
//        passenger.setName("Pass A");
//        passenger.setPhoneNumber("123");
//
//        TripDetails tripDetails = tripService.addPassenger("trip1", passenger);
//
//        assertAll(
//                () -> assertEquals(4, tripDetails.getSeatsAvailable()),
//                () -> assertEquals(1, tripDetails.getPassengers().size()),
//                () -> assertEquals("Pass A", tripDetails.getPassengers().get(0).getName()),
//                () -> assertEquals("123", tripDetails.getPassengers().get(0).getPhoneNumber())
//        );
//    }
//}
