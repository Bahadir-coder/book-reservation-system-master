package com.example.bookreservation.dao.handling;

import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.exception.StarException;
import com.example.bookreservation.model.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionDto notFoundHandler(RuntimeException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(FoundException.class)
    public ExceptionDto foundHandler(RuntimeException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StarException.class)
    public ExceptionDto starHandler(RuntimeException exception){
        return new ExceptionDto(exception.getMessage());
    }
}
