package com.example.bookreservation.controller;

import com.example.bookreservation.model.AuthorDto;
import com.example.bookreservation.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/get/all")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/find/by/{finCode}")
    public AuthorDto findByFinCode(@PathVariable String finCode) {
        return authorService.findByFinCode(finCode);
    }

    @PostMapping("/save")
    public void saveAuthor(@RequestBody AuthorDto authorDto) {
        authorService.saveAuthor(authorDto);
    }

    @DeleteMapping("/delete/by/{finCode}")
    public void deleteByFinCode(@PathVariable String finCode) {
        authorService.deleteByFinCode(finCode);
    }
}
