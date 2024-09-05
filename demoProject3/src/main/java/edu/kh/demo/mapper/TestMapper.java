package edu.kh.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

/* @Mapper
 * - 마이바티스 mapper와 연결된 인터페이스임을 명시
 * - 자동으로 해당 인터페이스를 상속받은 클래스를 만들어
 *   Bean으로 등록함
 */
@Mapper
public interface TestMapper {

	/** 사용자 이름조회
	 * @param userNo
	 * @return userName
	 */
	String selectUserName(int userNo);
	// -> 해당 메서드가 호출된 경우
	// 	  연결되어져있는 mapper.xml파일에서
	//    id 속성 값이
	//    메서드명과 같은 sql 태그가 수행된다!
	
	
	
}
