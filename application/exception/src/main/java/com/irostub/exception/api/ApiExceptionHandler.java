package com.irostub.exception.api;

import com.irostub.exception.exception.UserException;
import com.irostub.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(assignableTypes = ApiExceptionV3Controller.class)
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> userExceptionHandler(UserException e) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorCode", "USER_ERROR");
        errorMap.put("errorMsg", e.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @ExceptionHandler({NullPointerException.class, OutOfMemoryError.class})
    public ErrorResult multiExceptionHandle(NullPointerException e1, OutOfMemoryError e2) {
        log.error("[NullPointerException exceptionHandle] ex", e1);
        log.error("[OutOfMemoryError exceptionHandle] ex", e2);
        return null;
    }
}
