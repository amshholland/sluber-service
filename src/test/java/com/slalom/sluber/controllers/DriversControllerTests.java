package com.slalom.sluber.controllers;

import com.slalom.sluber.api.models.DriverDetails;
import com.slalom.sluber.api.models.DriverResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriversControllerTests {
    static private DriversController driversController;

    @BeforeAll
    static void beforeAll() {
        driversController = new DriversController();
    }


    @Test
    public void verifyDriverCreates() {
        String name = "Zelda, the princess";
        String driversId = "abc-123";
        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setName(name);

        ResponseEntity<DriverResponse> response = driversController.createDriver(driverDetails);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(driversId, response.getBody().getDriverId());
    }
}
