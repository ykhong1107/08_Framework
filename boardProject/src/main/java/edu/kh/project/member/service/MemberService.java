package edu.kh.project.member.service;

import edu.kh.project.member.dto.Member;

public interface MemberService {

	/** 로그인 서비스
	 * @param memberEmail
	 * @param memberPw
	 * @return loginMember 또는 null(email 또는 pw 불일치)
	 */
	Member login(String memberEmail, String memberPw);

	/** 회원가입
	 * @param inputMember
	 * @return
	 */
	int signUp(Member inputMember);

	/** 이메일 중복검사
	 * @param email
	 * @return count 값 반환
	 */
	int emailCheck(String email);

	/** 닉네임 중복검사
	 * @param nickname
	 * @return conut 값 반환
	 */
	int nicknameCheck(String nickname);

}
