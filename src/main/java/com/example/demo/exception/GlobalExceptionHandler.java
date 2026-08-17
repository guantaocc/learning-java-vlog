package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.web.response.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 请求题格式错误
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        logger.warn(
                "请求体格式错误，path={}",
                request.getRequestURI());

        logger.debug(
                "请求体解析异常详情",
                exception);

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST_BODY",
                "请求体格式错误，请检查 JSON 语法和字段类型",
                request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        logger.warn(
                "请求参数错误，path={}，message={}",
                request.getRequestURI(),
                exception.getMessage());

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_ARGUMENT",
                exception.getMessage(),
                request.getRequestURI());
    }

    // 参数校验错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        FieldError fieldError = exception
                .getBindingResult()
                .getFieldErrors()
                .get(0);

        String message = fieldError.getField()
                + ": "
                + fieldError.getDefaultMessage();

        logger.warn(
                "请求参数校验失败，path={}，message={}",
                request.getRequestURI(),
                message);

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                message,
                request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(
            Exception exception,
            HttpServletRequest request) {

        logger.error(
                "系统异常，path={}",
                request.getRequestURI(),
                exception);

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "服务器内部错误",
                request.getRequestURI());
    }

    private ResponseEntity<ApiError> buildResponse(
            HttpStatus status,
            String code,
            String message,
            String path) {

        long timestamp = System.currentTimeMillis();
        String requestId = MDC.get("requestId");
        ApiError body = new ApiError(timestamp, status.value(), code, message, path, requestId);

        return ResponseEntity
                .status(status)
                .body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException exception,
            HttpServletRequest request) {

        logger.warn(
                "查询用户失败，path={}，message={}",
                request.getRequestURI(),
                exception.getMessage());

        return buildResponse(
                HttpStatus.NOT_FOUND,
                "USER_NOT_FOUND",
                exception.getMessage(),
                request.getRequestURI());
    }

}
