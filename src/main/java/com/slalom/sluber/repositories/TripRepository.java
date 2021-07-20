package com.slalom.sluber.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
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

    /**
     * Saving trip to the database
     * @param trip
     * @return the created trip
     */
    public Trip saveTripToDB(Trip trip) {
        trip.setTripId();
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


    public Trip updateTrip(Trip trip, String tripId) {
        dynamoDBMapper.save(trip,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("tripId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(tripId)
                                )));
        return dynamoDBMapper.load(Trip.class, tripId);
    }
    // TODO: create a delete trip method [SSU-70]
//    public void deleteTripFromDB(String id) {
//        Trip trip = dynamoDBMapper.load(Trip.class, id);
//        dynamoDBMapper.delete(trip);
//    }
}
