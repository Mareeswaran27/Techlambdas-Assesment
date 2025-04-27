package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.Employee_Repository; 
import com.example.demo.model.employees;

@RestController
@RequestMapping("/api/emp_details")
public class Emp_Controller {

    @Autowired
    private Employee_Repositary empRepo;

    
    @GetMapping
    public List<employees> getAllEmployees() {
        return empRepo.findAll();
    }

    
    @GetMapping("/{empId}")
    public ResponseEntity<employees> getEmployeeById(@PathVariable String empId) {
        return empRepo.findById(empId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new employee
    @PostMapping
    public employees createEmployee(@RequestBody employees employee) {
        return empRepo.save(employee);
    }

    // Update employee
    @PutMapping("/{empId}")
    public ResponseEntity<employees> updateEmployee(@PathVariable String empId, @RequestBody employees empDetails) {
        Optional<employees> optionalEmp = empRepo.findById(empId);

        if (optionalEmp.isPresent()) {
            employees existingEmp = optionalEmp.get();
            existingEmp.setEmpCode(empDetails.getEmpCode());
            existingEmp.setEmpName(empDetails.getEmpName());
            existingEmp.setGender(empDetails.getGender());
            existingEmp.setDesignation(empDetails.getDesignation());
            existingEmp.setEmail(empDetails.getEmail());
            existingEmp.setPhone(empDetails.getPhone());
            existingEmp.setDob(empDetails.getDob());
            existingEmp.setCity(empDetails.getCity());
            existingEmp.setAddress(empDetails.getAddress());

            employees updatedEmp = empRepo.save(existingEmp);
            return ResponseEntity.ok(updatedEmp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete employee
    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String empId) {
        return empRepo.findById(empId)
                .map(emp -> {
                    empRepo.delete(emp);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
