package edu.kh.todolist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.mapper.TodoListMapper;

@Transactional
@Service
public class TodoListServiceImpl implements TodoListService{

	@Autowired
	private TodoListMapper mapper;
	
	
	@Override
	public Map<String, Object> selectTodoList() {
		
		List<Todo> todoList = mapper.selectTodoList();
		
		int completeCount = mapper.selectCompleteCount();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		
		return map;
	}
	
}
