package com.example.Todo.controller;

import com.example.Todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @GetMapping("/todos")
  public String getTodos (Model model) {
    model.addAttribute("todos", todoService.getAllTodos());
    return "todos";
  }

  @PostMapping("/todos")
  public String addTodo (@RequestParam String title) {
    todoService.addTodo(title);
    return "redirect:/todos";
  }

  @GetMapping("/todos/delete/{id}")
  public String deleteTodo (@PathVariable Long id) {
    todoService.deleteTodoById(id);
    return "redirect:/todos";
  }

}
