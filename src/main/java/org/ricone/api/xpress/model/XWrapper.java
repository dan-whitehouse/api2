package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties({ "districtId" })
@XmlTransient
public abstract class XWrapper {

	@JsonIgnore
	@XmlTransient
	private String districtId;

	@JsonIgnore
	@XmlTransient
	public String getDistrictId() {
		return districtId;
	}

	@JsonIgnore
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	/* CUSTOM METHODS */
	@JsonIgnore
	@XmlTransient
	public boolean isEmptyObject() {
		return Stream.of(districtId).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XWrapper [districtId=" + districtId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((districtId == null) ? 0 : districtId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		XWrapper other = (XWrapper) obj;
		if (districtId == null) {
			if (other.districtId != null) return false;
		}
		else if (!districtId.equals(other.districtId)) return false;
		return true;
	}
}