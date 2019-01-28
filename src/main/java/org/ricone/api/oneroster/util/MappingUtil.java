package org.ricone.api.oneroster.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.model.GUIDRef;
import org.ricone.api.oneroster.model.GUIDType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class MappingUtil {
	private static Logger logger = LogManager.getLogger(MappingUtil.class);

	public static GUIDRef buildGUIDRef(String pathSegment, String refId, GUIDType type) {
		if(StringUtils.isEmpty(refId)) {
			return null;
		}

		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
		builder.pathSegment("ims/oneroster/v1p1");
		builder.pathSegment(pathSegment);
		builder.pathSegment(refId);

		GUIDRef guidRef = new GUIDRef();
		guidRef.setHref(builder.build().toString());
		guidRef.setSourcedId(refId);
		guidRef.setType(type);
		return guidRef;
	}
}
