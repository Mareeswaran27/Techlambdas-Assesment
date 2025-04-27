package com.example.demo.repository;

import com.example.demo.model.employees;  
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee_Repository extends MongoRepository<employees, String> {
}
