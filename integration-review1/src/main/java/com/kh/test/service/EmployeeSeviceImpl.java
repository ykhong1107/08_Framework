package com.kh.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.kh.test.dto.Employee;
import com.kh.test.mapper.EmployeeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService{

	@Mapper
	private final EmployeeMapper mapper;
	
	
	@Override
	public List<Employee> employeeListSelect() {
		return mapper.employeeListSelect();
	}
	
}