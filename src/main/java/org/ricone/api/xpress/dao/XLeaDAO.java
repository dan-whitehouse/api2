package org.ricone.api.xpress.dao;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.controller.ControllerData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface XLeaDAO {
    List<LeaWrapper> findAll(ControllerData metadata);
}