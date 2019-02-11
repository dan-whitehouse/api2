package org.ricone.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class GlobalController {
	@ResponseBody
	@ExceptionHandler(NoContentException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT) //204
	public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		//Do nothing
	}
}