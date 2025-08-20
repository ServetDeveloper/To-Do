package com.example.Todo.service;

import com.example.Todo.model.Todo;
import com.example.Todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

  @Autowired
  private final TodoRepository todoRepository;

  public List<Todo> getAllTodos () {
    return todoRepository.findAll();
  }

  public Todo addTodo (Todo todo) {
    return todoRepository.save(todo);
  }

  public Optional<Todo> findById (Long id) {
    return  todoRepository.findById(id);
  }

  public void deleteTodoById (Long id) {
    todoRepository.deleteById(id);
  }

  public void save (Todo todo) {
    todoRepository.save(todo);
  }
}
