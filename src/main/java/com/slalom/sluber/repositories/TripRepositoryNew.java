package com.slalom.sluber.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.slalom.sluber.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the "repository" that actually sends the data to the database
 */

@Repository
public class TripRepositoryNew {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    @Autowired
    private AmazonDynamoDB client;

    public Trip saveTripToDB(Trip trip) {
        dynamoDBMapper.save(trip);
        return trip;
    }


    /**
     *Attempting to return all trips from the db with a scan request.
     * Need to convert ScanResult datatype into List<Trip> to maintain
     * compatability with already established API.
     * @return
     */
    public ScanResult getAllTrips() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("Trips");
        ScanResult result = client.scan(scanRequest);
        return result;
    }

    public Trip getTripById(String id) {
        return dynamoDBMapper.load(Trip.class, id);
    }


    public String deleteTripFromDB(String id) {
        Trip trip = dynamoDBMapper.load(Trip.class, id);
        dynamoDBMapper.delete(trip);
        return "Trip Deleted";
    }

}
