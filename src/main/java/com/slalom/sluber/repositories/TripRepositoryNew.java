package com.slalom.sluber.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.slalom.sluber.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * This is the "repository" that actually sends the data to the database
 */

@Repository
public class TripRepositoryNew {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    public Trip saveTripToDB(Trip trip) {
        dynamoDBMapper.save(trip);
        return trip;
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
