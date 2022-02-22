package com.dept.microservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dept_details")
public class Department {

	@Id
	private long id;
	@Column(name="dept_name")
	private String deptName;
	@Column(name="dept_manager")
	private String deptManager;
	@Column(name="no_of_emp")
	private String noOfEmployees;
	@Column(name="dept_tech")
	private String technology;
	private String enviornment;
	public Department() {}

	public Department(String deptName, String deptManager, String noOfEmployees, String technology) {
		super();
		this.deptName = deptName;
		this.deptManager = deptManager;
		this.noOfEmployees = noOfEmployees;
		this.technology = technology;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}

	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getEnviornment() {
		return enviornment;
	}

	public void setEnviornment(String enviornment) {
		this.enviornment = enviornment;
	}	
	
}
