package edu.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.test.dto.Student;
import lombok.extern.slf4j.Slf4j;




@RequestMapping("student")
@Controller // Controller 역할 명시 및 + bean 등록
public class StudentController {

	@PostMapping("select")
	public String selectStudent(Model model, 
			@ModelAttribute Student student) {
		model.addAttribute("stdName", student.getStdName());
		model.addAttribute("stdAge", student.getStdAge());
		model.addAttribute("stdAddress", student.getStdAddress());
		

		

		return "student/select";
	}
	
	@GetMapping("signUp")
	public String project() {
		
		
		
		return "student/signUp";
	}
	
	@GetMapping("signUpAccount")
	public String signUpAccount() {
		
		
		
		return "student/signUpAccount";
	}
	
	@GetMapping("signUpAccountConfirm")
	public String signUpAccountConfirm() {
	
		return "student/signUpAccountConfirm";
	}
	
	@GetMapping("signUpCreate")
	public String signUpCreate() {
		
		return "student/signUpCreate";
	}
	
	@GetMapping("myPageLogin")
	public String myPageLogin() {
		
		return "student/myPageLogin";
	}
	
	@GetMapping("myPageMain")
	public String myPageMain() {
			
		
		
		
		return "student/myPageMain";
	}
	
	
	@GetMapping("myPageUpdate")
	public String myPageUpdate() {
		
		return "student/myPageupdate";
	}
	
	@GetMapping("myPageOrderHistory")
	public String myPageOrderHistory() {
		
		return "student/myPageOrderHistory";
	}
	
	@GetMapping("myPageSalesHistory")
	public String myPageSalesHistory() {
		
		return "student/myPageSalesHistory";
	}
	
	@GetMapping("myPagePointHistory")
	public String myPagePointHistory() {
		
		return "student/myPagePointHistory";
	}
	
	
}