//package com.slalom.sluber.repositories;
//
//import com.slalom.sluber.api.models.CreateTripDetails;
//import com.slalom.sluber.api.models.TripDetails;
//import com.slalom.sluber.api.models.EmployeeDetails;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.time.Clock;
//import java.time.Instant;
//import java.time.ZoneId;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TripRepositoryTests {
//    private static TripRepository tripRepository;
//    private static Clock mockClock;
//
//    private final static String ORIGIN = "Slalom HQ, Seattle";
//
//    @BeforeAll
//    static void beforeAll() {
//        mockClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
//        tripRepository = new TripRepository(mockClock);
//    }
//
//    @Test
//    void createTripTest() {
//        CreateTripDetails createTripDetails = new CreateTripDetails();
//        createTripDetails.setOrigin(ORIGIN);
//        createTripDetails.setOriginator(CreateTripDetails.OriginatorEnum.PASSENGER);
//        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setName("Charmander");
//		employeeDetails.setPhoneNumber("5558675309");
//		createTripDetails.addPassengersItem(employeeDetails);
//
//
//        TripDetails trip = tripRepository.createTrip(createTripDetails);
//
//        assertAll(
//                () -> assertEquals(String.valueOf(mockClock.millis()), trip.getTripId()),
//                () -> assertEquals(ORIGIN, trip.getOrigin()),
//                () -> assertEquals(CreateTripDetails.OriginatorEnum.PASSENGER.toString(), trip.getOriginator().toString()),
//				() -> assertEquals("Charmander", trip.getPassengers().get(0).getName()),
//				() -> assertEquals("5558675309", trip.getPassengers().get(0).getPhoneNumber())
//        );
//    }
//}
