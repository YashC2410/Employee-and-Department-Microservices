package com.dept.microservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String DEPT_NOT_FOUND = "No Department Was Found Having ID: ";
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	public Department getDepartmentById(Long id) {
		logger.debug("Obtaining Department Details With ID: {}",id);
		return departmentRepository.findById(id).orElseThrow(
				() -> new DepartmentNotFoundException(DEPT_NOT_FOUND+id));
	}
	
	public String deleteDepartment(Long id) {
		logger.debug("Deleting Department Having ID: {}",id);
		if(departmentRepository.findById(id).isPresent()) {
			departmentRepository.deleteById(id);
			return "Department With ID: "+id+" is Deleted";
		}else {
			logger.error("Something Went Wrong While Performing Delete Function");
			throw new DepartmentNotFoundException(DEPT_NOT_FOUND+id);
		}
	}
	
	public Department saveDepartment(Department newDept) {
		logger.debug("Creating New Department With ID: {}",newDept.getId());
		return departmentRepository.save(newDept);
	}
	
	public String updateDepartment(Department dep,Long id) {
		logger.debug("Updating Details of Department Having ID: {}",id);
		if(departmentRepository.findById(id).isPresent()) {
		Department existingDepartment = departmentRepository.findById(id).get();
		 existingDepartment.setDeptManager(dep.getDeptManager());
		 existingDepartment.setDeptName(dep.getDeptName());
		 existingDepartment.setNoOfEmployees(dep.getNoOfEmployees());
		 existingDepartment.setTechnology(dep.getTechnology());
		 departmentRepository.save(existingDepartment);
		return "Department Having ID: "+id+" is Updated Successfully";
		}
		else {
			logger.error("Something Went Wrong While Performing Department Update Function");
			throw new DepartmentNotFoundException(DEPT_NOT_FOUND+id);
		}
	}
}
