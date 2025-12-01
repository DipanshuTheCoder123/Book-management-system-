package com.student.book_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.student.book_management.dto.BookDto;
import com.student.book_management.entity.Book;
import com.student.book_management.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
private final BookRepository repo;


public BookService(BookRepository repo) {
this.repo = repo;
}


public BookDto create(BookDto dto) {
Book book = mapToEntity(dto);
Book saved = repo.save(book);
return mapToDto(saved);
}


public BookDto update(Long id, BookDto dto) {
Book existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
existing.setTitle(dto.getTitle());
existing.setAuthor(dto.getAuthor());
existing.setPages(dto.getPages());
existing.setPublisher(dto.getPublisher());
return mapToDto(repo.save(existing));
}


public BookDto getById(Long id) {
return repo.findById(id).map(this::mapToDto).orElseThrow(() -> new RuntimeException("Book not found"));
}


public List<BookDto> getAll() {
return repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
}


public void delete(Long id) {
repo.deleteById(id);
}


private BookDto mapToDto(Book b) {
return BookDto.builder()
.id(b.getId())
.title(b.getTitle())
.author(b.getAuthor())
.pages(b.getPages())
.publisher(b.getPublisher())
.build();
}


private Book mapToEntity(BookDto d) {
return Book.builder()
.title(d.getTitle())
.author(d.getAuthor())
.pages(d.getPages())
.publisher(d.getPublisher())
.build();
}
}