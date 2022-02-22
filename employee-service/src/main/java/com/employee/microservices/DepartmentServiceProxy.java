package com.employee.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="department-service")
public interface DepartmentServiceProxy {

	@GetMapping("/department-service/{id}")
	public DepartmentInfo retrieveDepartmentForEmployee(@PathVariable("id") Long id);
}
