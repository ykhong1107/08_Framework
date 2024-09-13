package edu.kh.project.myPage.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

@Mapper // 상속받은 클래스 구현 + Bean 등록
public interface MyPageMapper {

	/** 회원정보 수정
	 * @param inputMember
	 * @return
	 */
	int updateInfo(Member inputMember);

	/** 닉네임 중복 검사
	 * @param input
	 * @return
	 */
	int checkNickname(String input);

	
	
}
