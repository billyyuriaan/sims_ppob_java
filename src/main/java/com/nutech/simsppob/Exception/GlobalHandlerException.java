/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.nutech.simsppob.dto.ResponseJsonFormat;

/**
 *
 * @author billyyuriaan
 */
@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseJsonFormat handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getDefaultMessage())
                .orElse("Validation error");

        ResponseJsonFormat response = new ResponseJsonFormat();
        response.put("status", 102);
        response.put("message", message);
        response.put("data", null);

        return response;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseJsonFormat handleNoRouteFound(
        NoHandlerFoundException ex
    )
    {
        ResponseJsonFormat response = new ResponseJsonFormat();
        response.put("status", 102);
        response.put("message", "Route API Not Found");
        response.put("data", null);

        return response;
    }
}
