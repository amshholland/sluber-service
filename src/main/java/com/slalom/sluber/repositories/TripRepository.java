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
public class TripRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Trip saveTripToDB(Trip trip) {
        trip.setId();
        dynamoDBMapper.save(trip);
        return trip;
    }

    /**
     * Return all trips via a scan request
     */
    public List<Trip> getAllTrips() {
        List<Trip> myList = dynamoDBMapper.scan(Trip.class, new DynamoDBScanExpression());
        return myList;
    }

    /**
     * Method to delete a document from DynamoDB
    */
    public String deleteTripFromDB(String id) {
        Trip trip = dynamoDBMapper.load(Trip.class, id);
        dynamoDBMapper.delete(trip);
        return "Trip Deleted";
    }

}
