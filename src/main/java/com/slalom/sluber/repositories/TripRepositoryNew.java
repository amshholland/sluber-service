package com.slalom.sluber.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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
    //@Autowired
    //private AmazonDynamoDB client;

    public Trip saveTripToDB(Trip trip) {
        trip.setId();
        dynamoDBMapper.save(trip);
        return trip;
    }


    /**
     * Attempting to return all trips from the db with a scan request.
     * Need to convert ScanResult datatype into List<Trip> to maintain
     * compatability with already established API.
     * @return
     */
    public List<Trip> getAllTrips() {
        List<Trip> myList = dynamoDBMapper.scan(Trip.class, new DynamoDBScanExpression());
        return myList;
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
