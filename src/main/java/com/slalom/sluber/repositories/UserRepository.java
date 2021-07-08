package com.slalom.sluber.repositories;

import java.util.List;

import com.slalom.sluber.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByLastName(String lastName);
}