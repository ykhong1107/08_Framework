package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.dto.Todo;

@Mapper // 상속받은 클래스 생성 후 Bean 등록
public interface TodoListMapper {
	
	/** 할일 목록조회
	 * @return
	 */
	List<Todo> selectTodoList();

	
	/** 완료된 할 일 개수조회
	 * @return
	 */
	int selectCompleteCount();

	/** 할일 추가
	 * @param todo
	 * @return result
	 */
	int todoAdd(Todo todo);

	/** 할일 상세조회
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);

	/** 할일 완료 여부
	 * @param todoNo
	 * @return
	 */
	int todoComplete(int todoNo);


	/** 할일 수정
	 * @param todo
	 * @return
	 */
	int todoUpdate(Todo todo);

	/** 할 일 삭제
	 * @param todoNo
	 * @return 
	 */
	int todoDelete(int todoNo);


	String searchTitle(int todoNo);

	/** 전체 할 일 개수 조회
	 * @return
	 */
	int getTotalCount();



}
