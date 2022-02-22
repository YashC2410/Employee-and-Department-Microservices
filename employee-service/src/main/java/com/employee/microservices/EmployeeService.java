package com.employee.microservices;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String EMPLOYEE_NOT_FOUND = "No Employee Was Found Having ID: ";
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		logger.debug("Obtaining Details of All Employees");
		return employeeRepository.findAll();
	}
	public Employee getEmpById(Long id) {
		logger.debug("Obtaining Details of Employee with ID: {}",id);
		return employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND+id)
				);
	}
	
	public String deleteEmployee(Long id) {
		logger.debug("Deleting Employee Having ID: {}",id);
		if(employeeRepository.findById(id).isPresent()) {
			employeeRepository.deleteById(id);
			return "Employee With ID: "+id+" is Deleted";
		}else {
			logger.error("Error While Performing Employee Delete Function");
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND+id);
		}
	}
	
	public Employee saveEmployee(Employee newEmployee) {
		logger.debug("Creating New Employee With ID: {}",newEmployee.getId());
		return employeeRepository.save(newEmployee);
	}
	
	public String updateEmployee(Employee e,Long id) {
		logger.debug("Updating Details of Employee Having ID: {}",id);
	  if(employeeRepository.findById(id).isPresent()) {
		   Employee existingEmployee = employeeRepository.findById(id).get();
			existingEmployee.setEmpName(e.getEmpName());
			existingEmployee.setJoiningDate(e.getJoiningDate());
			existingEmployee.setSalary(e.getSalary());
			existingEmployee.setWorkExperience(e.getWorkExperience());
			existingEmployee.setDepartmentId(e.getDepartmentId());
			employeeRepository.save(existingEmployee);
			return "Employee Having ID: "+id+" is Updated";
		}
		else {
			logger.error("Error Occured While Performing Employee Update Function");
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND+id);
		}
	}
}
