package com.lx.benefits.web.handler;


import com.alibaba.fastjson.JSON;
import com.apollo.common.exception.ArgumentException;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.response.enums.CommonMsgEnum;
import com.apollo.common.utils.base.LogUtil;
import com.apollo.starter.web.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String ILLEGALA_RGUMENT_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());


    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleCommonException(Throwable e, HttpServletRequest request) {
        logger.error("handleCommonException:" + request.getRequestURI(), e);
        return Result.wrapResult(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), CommonMsgEnum.SYSTEM_ERROR.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleBusinessException(BusinessException e, HttpServletRequest request)
            throws IOException {
    	  //logger.error("handleBusinessException:", e);
    	StackTraceElement stackTraceElement = null;
		for (StackTraceElement item : e.getStackTrace()) {
			if (item.getClassName().startsWith("com.lx.benefits")) {
				stackTraceElement = item;
				break;
			}
		}
		logger.error("BusinessException, code={}, message={}, location={}", e.getCode(), e.getMessage(), stackTraceElement);
		return Result.wrapResult(e.getCode(), e.getMessage());
    }

    /**
     * 如果是ajax请求没有权限就会被拦截到，不要在securityConfig中配置
     * @param request
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request)
            throws IOException {
        logger.error("", e);
        return Result.wrapResult(HttpStatus.FORBIDDEN.toString(), e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.OK)
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request)
            throws IOException {
        logger.error(e.getMessage());
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, e.getMessage());
    }

    @ExceptionHandler(value = ArgumentException.class)
    @ResponseStatus(value = HttpStatus.OK)
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleArgumentException(ArgumentException e, HttpServletRequest request)
            throws IOException {
        logger.error(e.getMessage());
        return Result.wrapResult(e.getCode(), e.getMessage());
    }

    //处理@Valid 校验没通过的情况
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request)
            throws IOException {
        logger.error(e.getMessage());
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result validExceptionHandler(BindException e, WebRequest request) {
        List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
        LogUtil.error(e);
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, JSON.toJSONString(
                fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())
        ));
    }

    /**
     * 参数解析异常
     *
     * @param request
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = MultipartException.class)
    @ResponseStatus(value = HttpStatus.OK)
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleMultipartException(MultipartException e, HttpServletRequest request)
            throws IOException {
        logger.error(e.getMessage());
        loggerRequest(request);
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        logger.error("缺少请求参数", e);
        loggerRequest(request);
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, "缺少请求参数");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        logger.error("参数解析失败", e);
        loggerRequest(request);
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, "参数解析失败");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        logger.error("参数验证失败", e);
        loggerRequest(request);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, message);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException e, HttpServletRequest request) {
        logger.error("参数验证失败", e);
        loggerRequest(request);
        return Result.wrapResult(ILLEGALA_RGUMENT_CODE, e.getMessage());
    }

    /**
     * 405 Method Not Allowed
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        logger.error("不支持当前请求方法", e);
        return Result.wrapResult(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED), "不支持当前请求方法");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        logger.error("不支持当前媒体类型", e);
        return Result.wrapResult(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE), "不支持当前媒体类型");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        logger.error("参数格式不匹配", e);
        return Result.wrapResult(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE), "参数不合法");
    }


    private void loggerRequest(HttpServletRequest request){
        logger.error("exception请求头数据,uri:{}, contentType:{}, method:{}, params:{}",
                request.getRequestURI(), request.getContentType(), request.getMethod(), JSON.toJSONString(request.getParameterMap())
        );
    }
}
