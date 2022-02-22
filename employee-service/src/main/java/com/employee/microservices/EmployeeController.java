package com.employee.microservices;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String EMPLOYEE_MESSAGE = "/employee-service/{} is called :{}";
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private Environment environment;
	@Autowired 
	private DepartmentServiceProxy departmentProxy;

	@GetMapping("/employee-service/{id}")
	public Employee getById(@PathVariable(value="id") Long id) {
		logger.debug(EMPLOYEE_MESSAGE,id,new Date());
		Employee employeeById = employeeService.getEmpById(id);
		employeeById.setEnviorment(environment.getProperty("local.server.port"));
		return employeeById;
	}
	
	@GetMapping("/employee-service/full/info/{id}")
	public Employee getFullInfoById(@PathVariable(value="id") Long id) {
		logger.debug("/employee-service/full/info/{} is called :{}",id,new Date());
		Employee empDetails = employeeService.getEmpById(id);
		HashMap<String ,Long> idValue = new HashMap<>();
		idValue.put("id", empDetails.getDepartmentId());
		ResponseEntity<DepartmentInfo> departmentDetails= new RestTemplate().getForEntity("http://localhost:8001//department-service/{id}",
				DepartmentInfo.class,idValue);
		DepartmentInfo departmentInfo = departmentDetails.getBody();
		empDetails.setDepartmentDetails(new DepartmentInfo(departmentInfo.getDeptName(),
				departmentInfo.getNoOfEmployees(),departmentInfo.getDeptManager(),
				departmentInfo.getTechnology()));
		empDetails.setEnviorment(environment.getProperty("local.server.port"));
		return empDetails;	
	}
	
	@GetMapping("/employee-service-feign/full/info/{id}")
	public Employee getFullInfoByIdFeignClient(@PathVariable(value="id") Long id) {
		logger.debug("/employee-service-feign/full/info/{} is called :{}",id,new Date());
		Employee empDetails = employeeService.getEmpById(id);
		DepartmentInfo departmentInfo = departmentProxy.retrieveDepartmentForEmployee(empDetails.getDepartmentId());
		empDetails.setDepartmentDetails(new DepartmentInfo(departmentInfo.getDeptName(),
				departmentInfo.getNoOfEmployees(),departmentInfo.getDeptManager(),
				departmentInfo.getTechnology()));
		String responsePort = departmentInfo.getEnviornment();
		logger.debug("Obtained Department Details With ID: {} From Port: {}",empDetails.getDepartmentId(),
				responsePort);
		empDetails.setEnviorment(responsePort+" feign client");
		return empDetails;	
	}
	@GetMapping("/employee-service")
	public List<Employee> getEmployeesList(){
		logger.debug("/employee-service/ is called :{}",new Date());
		return employeeService.getAllEmployees();
	}
	@PostMapping("/employee-service")
	public Employee saveEmployee(@RequestBody Employee employee) {
		logger.debug("/employee-service/ is called :{}",new Date());
		return employeeService.saveEmployee(employee);
	}
	
	@PutMapping("/employee-service/{id}")
	public String updateEmployee(@RequestBody Employee newEmployee ,@PathVariable(value="id") Long id) {
		logger.debug(EMPLOYEE_MESSAGE,id,new Date());
		return employeeService.updateEmployee(newEmployee, id);
	}
	
	@DeleteMapping("/employee-service/{id}")
	public String deleteEmployee(@PathVariable(value="id") Long id) {
		logger.debug(EMPLOYEE_MESSAGE,id,new Date());
		return employeeService.deleteEmployee(id);
	}
}
