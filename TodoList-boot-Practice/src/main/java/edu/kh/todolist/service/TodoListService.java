package edu.kh.todolist.service;

import java.util.Map;

public interface TodoListService {

	/** 할일 목록 조회 + 완료된 할 일 개수
	 * @return
	 */
	Map<String, Object> selectTodoList();
	
	

}
