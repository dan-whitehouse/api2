package org.ricone.api.xpress.controller;

import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.service.XLeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class XLeaController extends BaseController {
    @Autowired
    private XLeaService service;

    @ResponseBody
    @GetMapping(value = "/requests/xLeas")
    @PreAuthorize("hasAuthority('get:/requests/xLeas')")
    public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response, Pageable pageable) throws Exception {
        return service.findAll(getMetaData(request, response, pageable));
    }

    @ResponseBody
    @GetMapping(value = "/requests/xSchools/{refId}/xLeas")
    @PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xLeas')")
    public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
        return service.findAll(getMetaData(request, response, pageable));
    }
}
