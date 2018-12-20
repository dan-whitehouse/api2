package org.ricone.error;

import org.ricone.api.xpress.error.exception.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public void notFound() throws NotFoundException {
        throw new NotFoundException("You shouldn't be here...");
    }
}