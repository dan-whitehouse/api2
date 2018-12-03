package org.ricone.api.xpress.service;

import org.ricone.api.xpress.controller.ControllerData;
import org.ricone.api.xpress.model.XLeasResponse;

import java.util.List;

public interface XLeaService {
    XLeasResponse findAll(ControllerData metadata);
}