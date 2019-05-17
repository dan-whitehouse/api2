package org.ricone.api.xpress.request.app;

import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("RICOne:App:AppController")
public class AppController extends BaseController {
    @Autowired
    private AppService service;

    @GetMapping("/requests/app")
    public Application getApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return service.find(getMetaData(request, response));
    }

    @GetMapping("/requests/app/{appId}")
    public ResponseEntity getAppById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "appId") String appId) throws Exception {
        return  new ResponseEntity<>("Get Specific app with id of: " + appId, HttpStatus.OK);
    }
}