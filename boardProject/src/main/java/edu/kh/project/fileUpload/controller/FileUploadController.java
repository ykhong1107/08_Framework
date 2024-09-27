package edu.kh.project.fileUpload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.fileUpload.dto.FileDto;
import edu.kh.project.fileUpload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("fileUpload")
@RequiredArgsConstructor
public class FileUploadController {

	private final FileUploadService service;
	
	/** 파일 업로드 연습 메인 페이지 전환
	 * @return
	 */
	@GetMapping("main")
	public String fileUploadMain(Model model) {
		
		List<FileDto> fileList = service.selectFileList();
		
		model.addAttribute("fileList", fileList);
		
		return "fileUpload/main";
	}
	
	
	/* [Spring에서 제출된 파일을 처리하는 방법]
	 * 
	 * 1) enctype = "multipart/form-data" 로
	 *    클라이언트가 요청
	 *    
	 * 2) Spring에 내장되어있는
	 *    MultipartResolver가 제출된 파라미터들을 분류함
	 *    
	 *    문자열, 숫자 -> String
	 *    파일 -> MultipartFile 인터페이스 구현한 객체
	 *    
	 * 3) 컨트롤러 메서드 매개 변수로 전달
	 *    (@RequestParam, @ModelAttribute)   
	 */
	
	/** 단일 파일 업로드
	 * @return
	 */
	@PostMapping("test1")
	public String test1(
		@RequestParam("uploadFile") MultipartFile uploadFile) 
				throws IllegalStateException, IOException{
		
		String filePath = service.test1(uploadFile);
		
		
		log.debug("업로드된 파일 경로 : {}", filePath);
		
		return "redirect:main";
	}
	
	
	/** 단일 파일 업로드 + 일반 데이터
	 * @param uploadFile : 업로드되어 임시저장된 파일을 참조하는 객체
	 * @param fileName : 원본 이름으로 지정된 파일명
	 * @return
	 */
	@PostMapping("test2")
	public String test2(
		@RequestParam("uploadFile") MultipartFile uploadFile,
		@RequestParam("fileName") String fileName)
				throws IllegalStateException, IOException{
		
		String filePath = service.test2(uploadFile, fileName);
		
		log.debug("업로드된 파일 경로 : {}", filePath);
		
		return "redirect:main";
	}
	
	/** 단일파일 업로드 + 사용자 정의 예외를 이용한 예외처리
	 * @param uploadFile
	 * @return
	 */
	@PostMapping("test3")
	public String test3(
			@RequestParam("uploadFile") MultipartFile uploadFile) {
		
		String filePath = service.test3(uploadFile);
		
		log.debug("업로드된 파일 경로 : {}", filePath);
		
		return "redirect:main";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}




