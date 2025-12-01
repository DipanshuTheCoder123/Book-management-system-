package com.student.book_management.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.book_management.dto.BookDto;
import com.student.book_management.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
private final BookService service;


public BookController(BookService service) {
this.service = service;
}


@PostMapping
public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto dto) {
BookDto created = service.create(dto);
return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
}


@GetMapping
public List<BookDto> list() {
return service.getAll();
}


@GetMapping("/{id}")
public BookDto get(@PathVariable Long id) {
return service.getById(id);
}


@PutMapping("/{id}")
public BookDto update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
return service.update(id, dto);
}


@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
service.delete(id);
return ResponseEntity.noContent().build();
}
}