package com.example.bookreservation.controller;

import com.example.bookreservation.model.DeletedDto;
import com.example.bookreservation.service.DeletedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deleted")
public class DeletedController {
    private final DeletedService deletedService;

    public DeletedController(DeletedService deletedService){
        this.deletedService = deletedService;
    }

    @GetMapping("/get/all")
    public List<DeletedDto> getAllDeleted(){
        return deletedService.getAllDeleted();
    }

    @GetMapping("/find/by/{bookCode}")
    public DeletedDto findByBookCode(@PathVariable String bookCode){
        return deletedService.findByBookCode(bookCode);
    }

    @GetMapping("/find/by/bookName")
    public List<DeletedDto> findByBookName(@RequestParam String bookName){
        return deletedService.findByBookName(bookName);
    }
}
