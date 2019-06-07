package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employees;

@RestController

public class EmployeeController {
	@Autowired
	EmployeeMapper repository;

	@GetMapping("/users")
	public ResponseEntity<List<Employees>> listAllEmployeess() {
		List<Employees> employees = (List<Employees>) repository.findAll();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employees>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employees>>(employees, HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable long id) {
		Employees employee = repository.findById(id);
		if (employee != null)
			return new ResponseEntity<Employees>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<Employees>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody Employees employees) {
		repository.insert(employees);
		return new ResponseEntity<Employees>(HttpStatus.CREATED);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody Employees employee) {
		Employees findEmployee = repository.findById(id);
		if (findEmployee != null)
			findEmployee.setFirstName(employee.getFirstName());
		findEmployee.setLastName(employee.getLastName());
		repository.update(findEmployee);
		return new ResponseEntity<Employees>(HttpStatus.OK);
	}
}
