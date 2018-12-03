package org.ricone.api.xpress.service;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.controller.ControllerData;
import org.ricone.api.xpress.dao.XLeaDAO;
import org.ricone.api.xpress.mapper.XLeaMapper;
import org.ricone.api.xpress.model.XLeasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XLeaServiceImp implements XLeaService {
    @Autowired private XLeaDAO dao;
    @Autowired private XLeaMapper mapper;

    @Override
    public XLeasResponse findAll(ControllerData metadata){
        List<LeaWrapper> instance = dao.findAll(metadata);
        return mapper.convert(instance);
    }
}