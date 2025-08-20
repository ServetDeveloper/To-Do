package com.example.Todo.controller;

import com.example.Todo.model.Todo;
import com.example.Todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @GetMapping("/todos")
  public String getTodos (Model model) {
    model.addAttribute("todos", todoService.getAllTodos());
    model.addAttribute("todo", new Todo());
    return "todos";
  }

  @PostMapping("/todos")
  public String addTodo (@Valid @ModelAttribute("todo") Todo todo,
                         BindingResult result, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("todos", todoService.getAllTodos());
      return "todos";
    }
    todoService.addTodo(todo);
    return "redirect:/todos";
  }

  @PostMapping("/todos/delete/{id}")
  public String deleteTodo (@PathVariable Long id) {
    todoService.deleteTodoById(id);
    return "redirect:/todos";
  }

  @PostMapping("/todos/update-list")
  public String updateTodoList(@ModelAttribute("todos") List<Todo> todos) {

    for (Todo updatedTodo : todos) {

      Todo existingTodo = todoService.findById(updatedTodo.getId())
        .orElse(null);

      if (existingTodo != null) {
        existingTodo.setCompleted(updatedTodo.isCompleted());
        todoService.save(existingTodo);
      }
    }

    return "redirect:/todos";
  }

}
