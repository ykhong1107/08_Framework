package com.kh.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.dto.Employee;
import com.kh.test.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("employee")
@RequiredArgsConstructor
@Controller
public class EmployeeController {


	private final EmployeeService service;
	
	@ResponseBody
	@GetMapping("selectAll")
	public List<Employee> employeeListSelect(){
		
		return service.employeeListSelect();
	}
	
	
	
}
