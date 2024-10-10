package edu.kh.project.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.dto.Board;
import edu.kh.project.board.service.BoardService;
import edu.kh.project.board.service.EditBoardService;
import edu.kh.project.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("editBoard")
@Slf4j
public class EditBoardController {

	private final EditBoardService service;
	
	// 수정 시 상세조회 서비스 호출을 위한 객체 의존성 주입
	private final BoardService boardService;
	
	/* @PathVariable 사용 시 정규 표현식 적용가능!!
	 * {변수명:정규표현식}
	 */
	
	/** 게시글 작성화면 전환
	 * {boardCode:[0-9]+} : boardCode는 숫자 1글자 이상만 가능
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(
			@PathVariable("boardCode") int boardCode) {
		// @PathVariable 지정 시 foward한 html 파일에서도 사용가능!
		
		
		return "board/boardWrite";
		
	}
	
	/** 게시글 등록
	 * @param boardCode : 게시판 종류번호
	 * @param inputBoard : 제출된 key값이 일치하는 필드에 값이 저장된 객체
	 * 										(커맨드객체)
	 * @param loginMember : 로그인한 회원정보(글쓴이 회원번호 필요)
	 * @param images : 제출된 file 타입 input 태그 데이터
	 * @param ra : 리다이렉트 시 request scope로 값 전달
	 * @return
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(
			@PathVariable("boardCode") int boardCode,
			@ModelAttribute Board inputBoard,
			@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra) {
		
		/* 전달된 파라미터 확인 (debug 모드)
		 * 
		 * 제목, 내용, boardCode -> inputBoard 커맨드 객체
		 * 
		 * List<MultipartFile> images의 크기 (size())
		 *   == 제출된 file타입의 input 태그 개수 == 5개
		 *   
		 * * 선택된 파일이 없더라도 빈칸으로 제출이 된다!
		 * ex) 0, 2, 4 인덱스만 선택 
		 *  -> 0, 2, 4 는 제출된 파일이 있고
		 *     1, 3 은 빈칸으로 존재
		 * */
		
		// 1) 작성자 회원번호를 inputBoard에 세팅
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		// 2) 서비스 호출 후 결과(작성된 게시글 번호) 반환받기
		int boardNo = service.boardInsert(inputBoard, images);
		
		// 3) 서비스 결과에 따라 응답제어
		String path = null;
		String message = null;
		
		if(boardNo == 0) { // 실패
				path = "insert";
				message = "게시글 작성 실패";
		}else {
			
//			path = "/board/" + boardCode + "/" + boardNo; // 상세조회 주소
			
			path = "/board/" + boardCode; // 목록조회 주소(임시)
			message = "게시글이 작성 되었습니다!!";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		
		
		return "redirect:" + path; //임시
	}
	
	/** 게시글 삭제
	 * - DB에서 boardNo, memberNo가 일치하는
	 *   "BOARD" TABLE의 행의 BOARD_DEL_FL 컬럼 값을 'Y'로 변경
	 * @param boardNo
	 * @param loginMember
	 * @param ra
	 * @param referer : 현재 컨트롤러 메서드를 요청한 페이지 주소
	 * 								 (이전 페이지 주소 == 상세조회 페이지)
	 * @return
	 *  - 삭제성공 시 : "삭제되었습니다" 메시지 전달
	 *                     + 해당 게시판 목록으로 redirect
	 *                     
	 *  - 삭제 실패 시 : "삭제실패" 메시지 전달
	 *                     + 삭제하려던 게시글 상세조회 페이지 redirect                     
	 */
	@PostMapping("delete")
	public String boardDelete(
			@RequestParam("boardNo") int boardNo,
			@SessionAttribute("loginMember")Member loginMember,
			RedirectAttributes ra,
			@RequestHeader("referer") String referer) {
		  //
		
			// http://localhost/board/2/2018
			log.debug("referer : {} ", referer );
			
			// 행의 개수때문에 int를 적어줘야함!!!!!!
			int delete = service.boardDelete(boardNo, loginMember.getMemberNo()); 
			
			String path = null;
			String message = null;
			
			
			
			if(delete == 0) {
				path = "referer";
				message = "삭제실패!";
				
				
			}else {
				String boardCode = referer.split("/")[4];
				path = "/board/" + boardCode;
				message = "삭제되었습니다.";
				
			}
			
			ra.addFlashAttribute("message", message);
			
			
		
			return "redirect:" + path;
			
//			if(result > 0) { // 성공
//				message = "삭제 되었습니다";
//				path = "삭제된 게시글이 존재하던 게시판 목록 주소";
//				
//				String regExp = "/board/[0-9]+";
//				
//				Pattern pattern = Pattern.compile(regExp);
//				Matcher matcher = pattern.matcher(referer);
//				
//				if(matcher.find()) { // 일치하는 부분을 찾은 경우
//					path = matcher.group(); // /board/1
//				}
//				
//			} else { // 실패
//				message = "삭제 실패";
//				path = referer; // 삭제를 요청했던 상세 조회 페이지 주소
//			}
//   -- 좀더 복잡한 코드 --			
			
	}
	
	/** 게시글 수정화면 전환
	 * @param boardCode : 게시판 종류
	 * @param boardNo : 수정할 게시글 번호
	 * @param loginMember : 로그인한 회원정보(session)
	 * @param ra : redirect 시 request scope로 데이터 전달
	 * @param model : foward 시 request scope로 데이터 전달
	 */
	@PostMapping("{boardCode}/{boardNo}/updateView")
	public String updateView(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@SessionAttribute("loginMember")Member loginMember,
			RedirectAttributes ra,
			Model model) {
		
			// boardCode, boardNo가 일치하는 글 조회
			Map<String, Integer>map = 
					Map.of("boardCode", boardCode, "boardNo", boardNo);
		
			Board board = boardService.selectDetail(map);
			
			// 게시글이 존재하지 않는 경우
			if(board == null) {
				ra.addFlashAttribute("message",
														 "해당 게시글이 존재하지 않습니다.");
				
				
				return "redirect:/board/" + boardCode; // 게시글 목록
			}
		
			// 게시글 작성자가 로그인한 회원이 아닌경우
			if(board.getMemberNo() != loginMember.getMemberNo()) {
				
				ra.addFlashAttribute("message",
														 "글 작성자만 수정 가능합니다");
				
				return String.format("redirect:/board/%d%d",
														  boardCode, boardNo); // 상세조회
				
			}
		
			// 게시글이 존재하고
			// 로그인한 회원이 작성한 글이 맞을경우
			// 수정화면으로 forward
			model.addAttribute("board", board);
			return "board/boardUpdate";
			
	}
	
	/** 게시글 수정
	 * @return
	 */
	@PostMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	public String boardUpdate(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@ModelAttribute Board inputBoard,
			@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images,
			@RequestParam(value="deleteOrderList", required = false)String deleteOrderList,
			RedirectAttributes ra
			) {
		
		// 1. 커맨드객체 inputBoard에 로그인한 회원번호 추가
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		// inputBoard에 세팅된 값
		// : boardCode, boardNo, boardTitle, boardContent, memberNo
		
		// 게시글 수정 서비스 호출 후 결과반환
		int result = service.boardUpdate(inputBoard, images, deleteOrderList);
		
		String message = null;
		if(result > 0) {
			message = "게시글이 수정되었습니다";
		} else {
			message = "수정실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return String.format("redirect:/board/%d/%d",
													boardCode, boardNo); // 상세조회
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
 