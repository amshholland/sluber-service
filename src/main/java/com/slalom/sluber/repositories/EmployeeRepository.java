package com.slalom.sluber.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.slalom.sluber.model.Employee;
import com.slalom.sluber.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

//TODO: refactor EmployeeRepository class to merge with TripRepository.
//TODO: Should only have 1 repository for all tables within a database.
@Repository
public class EmployeeRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Employee saveEmployeeToDB(Employee employee) {
        employee.setEmployeeId();
        dynamoDBMapper.save(employee);
        return employee;
    }
}
