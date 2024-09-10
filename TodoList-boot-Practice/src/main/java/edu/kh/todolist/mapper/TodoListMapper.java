package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.dto.Todo;

@Mapper
public interface TodoListMapper {

	List<Todo> selectTodoList();

	int selectCompleteCount();



}
