package br.com.digitalpages.marvel.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MarvelAppExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		exception.printStackTrace();
		return new ModelAndView("marvel/error").addObject("exception", exception);
	}

}
