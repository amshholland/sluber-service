package com.slalom.sluber.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Trip class represents the Java objects that are storing information translated from the JSON format
 */
@DynamoDBTable(tableName = "trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "id")
    @NotBlank
    private String id; // unique id which corresponds to the primary key

    @DynamoDBAttribute(attributeName = "name")
    private String name; // trip name
    //if we are assuming that we will be creating people by receiving POST requests from the client,
    //we need to tell JAVA to convert the incoming JSON into a java class.
    //we can define each of these properties as JSON properties


    public Trip() {
        this.id = "";
        this.name = "";
    }

    /**
     * Constructor for a trip objects from JSON
     * @param id unique id/primary key
     * @param name trip name
     */
    public Trip(@JsonProperty("id") String id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Getter for trip id
     * @return trip unique id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for trip name
     * @return trip name
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}

