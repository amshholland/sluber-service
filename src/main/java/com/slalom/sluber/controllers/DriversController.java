package com.slalom.sluber.controllers;

import com.slalom.sluber.api.SluberApi;
import com.slalom.sluber.api.models.DriverDetails;
import com.slalom.sluber.api.models.DriverResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class DriversController implements SluberApi {

    @Override
    public ResponseEntity<DriverResponse> createDriver(DriverDetails body) {
        DriverResponse driverResponse = new DriverResponse();
        driverResponse.driverId("abc-123");
        return ResponseEntity.ok(driverResponse);
    }
}
