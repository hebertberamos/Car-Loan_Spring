package com.personalproject.carloan.controllers.exceptions;

import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourcesNotFoundException exception, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
