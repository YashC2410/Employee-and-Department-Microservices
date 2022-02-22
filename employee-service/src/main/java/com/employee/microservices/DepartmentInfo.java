package com.employee.microservices;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentInfo {

	private String deptName;
	private String noOfEmployees;
	private String deptManager;
	private String technology;
	private String enviornment;
	public DepartmentInfo() {}

	public DepartmentInfo(String deptName, String noOfEmployees, String deptManager, String technology) {
		super();
		this.deptName = deptName;
		this.noOfEmployees = noOfEmployees;
		this.deptManager = deptManager;
		this.technology = technology;
	}

	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
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
