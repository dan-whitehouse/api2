package org.ricone.api.oneroster.model;

public enum StatusType {
	active, //An active record.
	tobedeleted, //Denotes that it is safe to delete the record.
	@Deprecated inactive //DEPRECATED. To be mapped to 'tobedeleted.
}
