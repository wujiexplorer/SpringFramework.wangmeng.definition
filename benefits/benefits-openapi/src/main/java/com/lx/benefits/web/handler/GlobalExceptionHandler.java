package com.lx.benefits.web.handler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

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

import com.apollo.common.exception.ArgumentException;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.util.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { Throwable.class })
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleCommonException(Throwable e, HttpServletRequest request) {
		log.error("handleCommonException:" + request.getRequestURI(), e);
		return Response.fail("服务器异常");
	}

	@ExceptionHandler(value = BusinessException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleBusinessException(BusinessException e, HttpServletRequest request) throws IOException {
		StackTraceElement stackTraceElement = null;
		for (StackTraceElement item : e.getStackTrace()) {
			if (item.getClassName().startsWith("com.lx.benefits")) {
				stackTraceElement = item;
				break;
			}
		}
		log.error("BusinessException, code={}, message={}, location={}", e.getCode(), e.getMessage(), stackTraceElement);
		return Response.fail(Integer.valueOf(e.getCode()), e.getMessage());
	}

	/**
	 * 如果是ajax请求没有权限就会被拦截到，不要在securityConfig中配置
	 * 
	 * @param request
	 * @param e
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) throws IOException {
		log.error("", e);
		return Response.fail(e.getMessage());
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) throws IOException {
		log.error(e.getMessage(), e);
		return Response.fail(e.getMessage());
	}

	@ExceptionHandler(value = ArgumentException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleArgumentException(ArgumentException e, HttpServletRequest request) throws IOException {
		log.error(e.getMessage(), e);
		return Response.fail(e.getMessage());
	}

	// 处理@Valid 校验没通过的情况
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) throws IOException {
		log.error(e.getMessage(), e);
		return Response.fail(e.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Object validExceptionHandler(BindException e, WebRequest request) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		log.error(e.getMessage(), e);
		return Response.fail(fieldErrors.get(0).getDefaultMessage());
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
	public Object handleMultipartException(MultipartException e, HttpServletRequest request) throws IOException {
		log.error(e.getMessage());
		return Response.fail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
		log.error("缺少请求参数", e);
		return Response.fail("缺少请求参数");
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
		log.error("参数解析失败", e);
		return Response.fail("参数解析失败");
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ConstraintViolationException.class)
	public Object handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
		log.error("参数验证失败", e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		return Response.fail(message);
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ValidationException.class)
	public Object handleValidationException(ValidationException e, HttpServletRequest request) {
		log.error("参数验证失败", e);
		return Response.fail(e.getMessage());
	}

	/**
	 * 405 Method Not Allowed
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		log.error("不支持当前请求方法", e.getMessage());
		return Response.fail("不支持当前请求方法");
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Object handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
		log.error("不支持当前媒体类型", e);
		return Response.fail("不支持当前媒体类型");
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		log.error("参数格式不匹配", e);
		return Response.fail("参数不合法");
	}

}
