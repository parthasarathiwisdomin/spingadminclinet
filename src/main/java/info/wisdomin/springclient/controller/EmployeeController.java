package info.wisdomin.springclient.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import info.wisdomin.springclient.model.Employee;
import info.wisdomin.springclient.service.EmployeeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = { "/employee" })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Employee> getAllEmployee() {
		List<Employee> tasks = employeeService.getEmployee();
		return tasks;
	}

	@GetMapping(value = "/{id}", headers = "Accept=application/json")
	public Optional<Employee> getEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> employee = employeeService.findById(id);
		return employee;
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody Employee currentEmployee) {

		Optional<Employee> employee = employeeService.findById(currentEmployee.getId());
		if (employee == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		employeeService.update(currentEmployee, currentEmployee.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Employee " + employee.getName());
		employeeService.createEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Employee> deleteUser(@PathVariable("id") long id) {
		Optional<Employee> employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}