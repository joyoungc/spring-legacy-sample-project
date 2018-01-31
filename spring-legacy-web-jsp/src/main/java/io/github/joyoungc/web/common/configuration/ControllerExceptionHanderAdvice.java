package io.github.joyoungc.web.common.configuration;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.joyoungc.web.common.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHanderAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerBindException(BindException e) {
		log.error("## BindException error : {}", e);
		String resultMsg = e.hasFieldErrors() ? this.getErrorMessage(e.getBindingResult()) : e.getMessage();
		return Collections.singletonMap("resultMessage", resultMsg);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("## MethodArgumentNotValidException error : {}", e);
		String resultMsg = e.getBindingResult().hasFieldErrors() ? this.getErrorMessage(e.getBindingResult())
				: e.getMessage();
		return Collections.singletonMap("resultMessage", resultMsg);
	}

	private String getErrorMessage(BindingResult bind) {
		return MessageFormat.format(bind.getFieldErrors().get(0).getDefaultMessage(),
				bind.getFieldErrors().get(0).getField());
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerDataNotFoundException(DataNotFoundException e) {
		log.error("## DataNotFoundException error : {}", e);
		return Collections.singletonMap("resultMessage", e.getMessage());
	}

}
