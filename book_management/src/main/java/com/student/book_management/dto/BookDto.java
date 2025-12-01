package com.student.book_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookDto {
private Long id;


@NotBlank(message = "Title is required")
private String title;


@NotBlank(message = "Author is required")
private String author;


@Min(value = 1, message = "Pages must be at least 1")
private int pages;


private String publisher;
} 

