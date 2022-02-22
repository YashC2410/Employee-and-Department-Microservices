package com.employee.microservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="Employee_Details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	@Id
	private long id;
	@Column(name="emp_Name")
	private String empName;
	@Column(name="work_exp")
	private String workExperience;
	@Column(name="sal")
	private long salary;
	@Column(name="joined_date")
	private String joiningDate;
	@Column(name="dep_id")
	private long departmentId;
	@Transient
	private DepartmentInfo departmentDetails;
	private String enviorment;
	
	
	public Employee() {}

	public Employee(long id, String empName, String workExperience, long salary, String joiningDate, long departmentId,
			DepartmentInfo departmentDetails) {
		super();
		this.id = id;
		this.empName = empName;
		this.workExperience = workExperience;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.departmentId = departmentId;
		this.departmentDetails = departmentDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEnviorment() {
		return enviorment;
	}

	public void setEnviorment(String enviorment) {
		this.enviorment = enviorment;
	}

	public DepartmentInfo getDepartmentDetails() {
		return departmentDetails;
	}

	public void setDepartmentDetails(DepartmentInfo departmentDetails) {
		this.departmentDetails = departmentDetails;
	}
	
	
	
}
