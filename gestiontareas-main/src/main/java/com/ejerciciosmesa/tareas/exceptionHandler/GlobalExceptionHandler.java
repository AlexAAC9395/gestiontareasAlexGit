package com.ejerciciosmesa.tareas.exceptionHandler;

import com.ejerciciosmesa.tareas.exception.ErrorResponse;
import com.ejerciciosmesa.tareas.exception.TaskNotFoundException;
import com.ejerciciosmesa.tareas.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //GLOBAL
//@RestControllerAdvice("com."".controller") //Por paquetes
//@RestControllerAdvice(assignableTypes = "".class) //Por clase
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException ex){
        return new ErrorResponse("Tarea no encontrada", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ValidationErrorResponse("Validación fallida", errorMessages);
    }
}
