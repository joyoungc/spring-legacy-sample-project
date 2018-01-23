package io.github.joyoungc.web.common.configuration;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHanderAdvice {
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerBindException(BindException e) {
		log.error("## BindException error : {}", e);
		String resultMsg = e.hasFieldErrors()
				? MessageFormat.format(e.getFieldErrors().get(0).getDefaultMessage(),
						e.getFieldErrors().get(0).getField())
				: e.getMessage();
		return Collections.singletonMap("resultMessage", resultMsg);		
	}

}
