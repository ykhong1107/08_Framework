package com.kh.test.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.test.customer.dto.Customer;
import com.kh.test.customer.service.CustomerService;

@RequestMapping("customer") // 해당 Controller에 요청 매핑
@Controller // 응답 요청 처리, 제어  Bean 등록
public class CustomerController {

  @Autowired // DI 주입
  private CustomerService service;

  /**
   * 
   * @param customer
   * @param model
   * @param ra : redirect 시 request scope로 값 전달하는 객체
   * @return
   */
  @PostMapping("insertCustomer") 
  public String insertCustomer(Customer customer, Model model) {

  	int result = service.insertCustomer(customer);

  	if (result > 0)

  	model.addAttribute("message", "추가 성공!!!");

  	else

  	model.addAttribute("message", "추가 실패...");

  	return "result";
  }
}
