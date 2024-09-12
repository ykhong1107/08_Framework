package edu.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
}