package io.github.joyoungc.admin.common.configuration;

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

import io.github.joyoungc.admin.common.exception.DataNotFoundException;
import io.github.joyoungc.admin.common.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandlerAdvice {

	/***
	 * BindException Handler
	 * @param BindException
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerBindException(BindException e) {
		log.error("## BindException error : {}", e);
		String resultMsg = e.hasFieldErrors() ? this.getErrorMessage(e.getBindingResult()) : e.getMessage();
		return Collections.singletonMap(Constants.RESULT_MESSAGE, resultMsg);
	}

	/***
	 * MethodArgumentNotValidException Handler
	 * @param MethodArgumentNotValidException
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("## MethodArgumentNotValidException error : {}", e);
		String resultMsg = e.getBindingResult().hasFieldErrors() ? this.getErrorMessage(e.getBindingResult())
				: e.getMessage();
		return Collections.singletonMap(Constants.RESULT_MESSAGE, resultMsg);
	}

	/***
	 * DataNotFoundException Handler
	 * @param DataNotFoundException
	 * @return
	 */
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> handlerDataNotFoundException(DataNotFoundException e) {
		log.error("## DataNotFoundException error : {}", e);
		return Collections.singletonMap(Constants.RESULT_MESSAGE, e.getMessage());
	}

	/***
	 * BindingResult에서 응답 메세지 생성
	 * @param bind
	 * @return
	 */
	private String getErrorMessage(BindingResult bind) {
		return MessageFormat.format(bind.getFieldErrors().get(0).getDefaultMessage(),
				bind.getFieldErrors().get(0).getField());
	}
}
