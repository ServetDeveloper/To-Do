package com.example.Todo.service;

import com.example.Todo.model.Todo;
import com.example.Todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  public List<Todo> getAllTodos () {
    return todoRepository.findAll();
  }

  public void addTodo (String title) {
    Todo todo = new Todo();
    todo.setTitle(title);
    todoRepository.save(todo);
  }

  public void deleteTodoById (Long id) {
    todoRepository.deleteById(id);
  }
}
