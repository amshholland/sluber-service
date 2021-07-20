package com.slalom.sluber.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerated;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@DynamoDBTable(tableName = "Employee")
public class Employee {

    @DynamoDBHashKey(attributeName = "employeeId")
    @NotBlank
    private String employeeId;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "phoneNumber")
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = "emailAddress")
    private String emailAddress;

    /*
    Constructors - Empty constructor is necessary for DB scan
     */

    public Employee(String employeeId, String name, String phoneNumber, String emailAddress) {
        this.employeeId = employeeId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Employee() {}

    /*
    Getters
     */

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    /*
    Setters
     */

    public void setEmployeeId() {
        String uniqueID = UUID.randomUUID().toString();
        this.employeeId = uniqueID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /*
    other functions
     */

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

}