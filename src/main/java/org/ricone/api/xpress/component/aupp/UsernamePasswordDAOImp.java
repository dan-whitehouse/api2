package org.ricone.api.xpress.component.aupp;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.request.xLea.XLeaDAO;
import org.ricone.config.model.District;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("XPress:AUPP:UsernamePasswordDAO")
public class UsernamePasswordDAOImp extends BaseDAO implements UsernamePasswordDAO {
	@Autowired private XLeaDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void initAppLeas(ControllerData metadata, String refId) {
		List<LeaWrapper> list = dao.findAll(metadata);

		list.forEach(wrapper -> {
			Optional<District> d = metadata.getApplication().getApp().getDistricts()
					.stream()
					.filter(district -> district.getId().equalsIgnoreCase(wrapper.getLea().getLeaId()))
					.findFirst();
			d.ifPresent(district -> district.setLea(wrapper.getLea()));
		});
	}
}