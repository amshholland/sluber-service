package com.slalom.sluber.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Trip class represents the Java objects that are storing information translated from the JSON format
 */
@DynamoDBTable(tableName = "Trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "tripId")
    @NotBlank
    private String tripId; // unique id which corresponds to the primary key

    @DynamoDBAttribute(attributeName = "origin")
    private String origin;

    @DynamoDBAttribute(attributeName = "departureTime")
    private String departureTime;

    @DynamoDBAttribute(attributeName = "comments")
    private String comments;

    @DynamoDBAttribute(attributeName = "seatsAvailable")
    private int seatsAvailable;

    @DynamoDBAttribute(attributeName = "passengers")
    private ArrayList<String> passengers;

    @DynamoDBAttribute(attributeName = "destination")
    private String destination;

    @DynamoDBAttribute(attributeName = "arrivalTime")
    private String arrivalTime;

    @DynamoDBAttribute(attributeName = "driver")
    private String driver;

    @DynamoDBAttribute(attributeName = "originator")
    private String originator = null;

    /**
     * Constructor for a trip objects from JSON
     */

    public Trip(String tripId, String origin, String departureTime, String comments, int seatsAvailable, ArrayList<String> passengers, String destination, String arrivalTime, String driver, String originator) {
        this.tripId = tripId;
        this.origin = origin;
        this.departureTime = departureTime;
        this.comments = comments;
        this.seatsAvailable = seatsAvailable;
        this.passengers = passengers;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.driver = driver;
        this.originator = originator;
    }

    public Trip() {}

    /**
     * Getter for trip id
     */
    public String getTripId() {
        return tripId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getComments() {
        return comments;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public String getDestination() {
        return destination;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDriver() {
        return driver;
    }

    public String getOriginator() {
        return originator;
    }

    /*
    Setters
     */
    public void setTripId(String id) {
        this.tripId = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setPassengers(ArrayList<String> passengers) {
        this.passengers = passengers;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setTripId(){
        String uniqueID = UUID.randomUUID().toString();
        this.tripId = uniqueID;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    /*
    Other Methods
     */
    @Override
    public int hashCode() {
        return Objects.hash(tripId);
    }


}

