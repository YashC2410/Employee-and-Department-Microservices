package com.employee.microservices;

import java.util.Date;

public class EmployeeExceptionResponse {

	private int statusCode;
	private String message;
	private Date timestamp;
	private String errorDetails;
	public EmployeeExceptionResponse(int statusCode, String message, Date timestamp, String errorDetails) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = timestamp;
		this.errorDetails = errorDetails;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
