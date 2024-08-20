package com.example.capstone2.Advice;

import com.example.capstone2.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    // it handles the ApiException that I have thrown
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    //All endpoints
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    // it occurs if you try to creat multiple instance with the same uniq value
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    // it occurs when you don't match give an endpoint a 2 arg when it expects a 3 arg
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    // it occurs when you call an endpoint wrong. Like when you call a get endpoint with a post.
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    //it occurs when you give it the wrong data type like passing a letter in the path var when it is expecting an integer
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
