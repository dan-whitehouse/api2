package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.Lea;

public class LeaWrapper {
	private String districtId;
	private Lea lea;

	public LeaWrapper() {
	}

	public LeaWrapper(String districtId, Lea lea) {
		this.districtId = districtId;
		this.lea = lea;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Lea getLea() {
		return lea;
	}

	public void setLea(Lea lea) {
		this.lea = lea;
	}
}