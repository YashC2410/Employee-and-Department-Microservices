package com.dept.microservices;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String DEPARTMENT_LOGGER_ID = "/department-service/{} is called: {}";
	private static final String DEPARTMENT_LOGGER = "/department-service called: {}";
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private Environment environment;
	
	@GetMapping("/department-service/{id}")
	public Department getDepartmentById(@PathVariable(value="id") Long id) {
		String port = environment.getProperty("local.server.port");
		logger.debug(DEPARTMENT_LOGGER_ID,id,new Date());
		Department deptById = departmentService.getDepartmentById(id);
		deptById.setEnviornment(port);
		return deptById;
	}
	
	@GetMapping("/department-service")
	public List<Department> getDepartmentList(){
		logger.debug(DEPARTMENT_LOGGER,new Date());
		return  departmentService.getAllDepartments();
	}
	
	@PostMapping("/department-service")
	public Department saveDepartment(@RequestBody Department dept) {
		logger.debug(DEPARTMENT_LOGGER,new Date());
		return  departmentService.saveDepartment(dept);
	}
	
	@PutMapping("/department-service/{id}")
	public String updateDepartment(@RequestBody Department dept, @PathVariable(value="id") Long id) {
		logger.debug(DEPARTMENT_LOGGER_ID,id,new Date());
		return departmentService.updateDepartment(dept, id);
	}
	
	@DeleteMapping("/department-service/{id}")
	public String deleteDepartment(@PathVariable(value="id") Long id) {
		logger.debug(DEPARTMENT_LOGGER_ID,id,new Date());
		return departmentService.deleteDepartment(id);
	}
}
