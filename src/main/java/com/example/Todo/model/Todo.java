package com.example.Todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title cannot be empty")
  @Size(min = 5, max = 30, message = "Title should be between 5 and 30 characters")
  private String title;

  @Size(min = 20, max = 50, message = "Description should be between 20 and 50 characters")
  private String description;

  private boolean completed;

}
