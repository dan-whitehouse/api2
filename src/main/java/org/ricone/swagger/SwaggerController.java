package org.ricone.swagger;

import io.swagger.annotations.Api;
import org.ricone.api.oneroster.component.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("Swagger:SwaggerController")
public class SwaggerController extends BaseController {

	@RequestMapping(value = "/docs", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
}
