package edu.kh.todolist.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	/** 할일 목록 조회 + 완료된 할 일 개수
	 * @return
	 */
	Map<String, Object> selectTodoList();

	/** 할일추가
	 * @param todo
	 * @return result
	 */
	int todoAdd(Todo todo);

	/** 할일 상세조회
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);

	/** 완료 여부 변경
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
	 * @param ra
	 * @return 
	 */
	int todoDelete(int todoNo);

	String searchTitle(int todoNo);

	/** 전체 할 일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();

	/** 완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int getCompleteCount();

	/** 할 일 전체목록
	 * @return
	 */
	List<Todo> getTodoList();



}
