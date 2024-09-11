package edu.kh.project.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

// @Mapper
// - Mybatis 제공 어노테이션(컴파일러에게 지시를 내림)
// - 해당 인터페이스를 상속받은 클래스 자동구현 + Bean 등록

@Mapper
public interface MemberMapper {

	/** memberEmail이 일치하는 회원정보 조회
	 * @param memberEmail
	 * @return loginMember 또는 null
	 */
	Member login(String memberEmail);
	
	

}
