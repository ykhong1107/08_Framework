package edu.kh.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.common.util.RedisUtil;

@Controller
@RequestMapping("email")
public class EmailController {

	@Autowired
	public RedisUtil redisUtil;
	
	// 레디스 확인하기
	@ResponseBody
	@GetMapping("redisTest")
	public int redisTest(	
			@RequestParam("key") String key,
			@RequestParam("value") String value
			) {
	
		// 전달받은 key, value 를 redis에 set 하기
		redisUtil.setValue(key, value, 60); // 60초 후에 만료
		
		return 1;
	}
	
}
