package edu.kh.project.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.kh.project.member.dto.Member;

@Mapper // 인터페이스 상속 클래스 생성 + 클래스 Bean등록
public interface MainMapper {

	/** 전체 회원조회
	 * @return
	 */
	List<Member> selectMemberList();

	/** 빠른 로그인
	 * @param memberNo
	 * @return loginMember
	 */
	Member directLogin(int memberNo);

	/** 비밀번호 초기화
	 * @param memberNo
	 * @param encPw 
	 * @return
	 */
	int resetPw(@Param("no") int memberNo, @Param("pw") String encPw);

	
	/** 탈퇴 상태변경
	 * @param memberNo
	 * @return
	 */
	int changeStatus(int memberNo);

}
