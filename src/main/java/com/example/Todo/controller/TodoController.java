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

  @PostMapping("/todos/{id}")
  public String deleteTodo (@PathVariable Long id) {
    todoService.deleteTodoById(id);
    return "redirect:/todos";
  }

  @PostMapping("update/{id}")
  public String updateTodo (@PathVariable Long id, @ModelAttribute("todo") Todo todo) {

    todoService.updateTodo(id, todo);

    return "redirect:/todos";
  }

  @GetMapping("edit/{id}")
  public String showUpdateForm (@PathVariable Long id, Model model) {
    Todo todo = todoService.findById(id)
        .orElseThrow( () -> new IllegalArgumentException("Invalid todo id: " + id));

    model.addAttribute("todo", todo);
    return "todos";
  }

}
