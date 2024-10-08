package edu.kh.project.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Date Transfer Object) : 계층간 데이터 전달용 객체
// - 계층? Controller, Service, DB 등을 구분

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Board {
	
	// 행 번호
	private int rnum;

	// BOARD 테이블 컬럼과 매핑되는 필드
	private int 	 boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int 	 readCount;
	private String boardDelFl;
	private int 	 memberNo;
	private int 	 boardCode;

	// MEMBER 테이블 JOIN 컬럼
	
	private String memberNickname;
	
	// 목록 조회시 댓글/좋아요 수 상관쿼리 결과
	private int 	 commentCount;
	private int 	 likeCount;
	
	// -----------------------------------------------
	// (추가작성 예정)
	
	private String thumbnail; // 썸네일 이미지
	private String profileImg; // 작성자 프로필 이미지
	
	// 특정 게시글의 이미지목록을 저장할 필드
	private List<BoardImg> imageList;
	
	// 특정 게시글의 댓글목록을 저장할 필드
	private List<Comment> commentList;
	
	// 좋아요 체크여부를 저장할 필드(1 == 누른적있음)
	private int likeCheck;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
