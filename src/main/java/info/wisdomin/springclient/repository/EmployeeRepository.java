package info.wisdomin.springclient.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import info.wisdomin.springclient.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	


}