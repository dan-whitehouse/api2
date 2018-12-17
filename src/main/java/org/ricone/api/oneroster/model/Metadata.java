package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ims.classification", "ims.boarding", "http://www.nbrs.org"})
public class Metadata implements Serializable {
	@JsonProperty("ims.classification")
	private String imsClassification;
	@JsonProperty("ims.boarding")
	private String imsBoarding;
	@JsonProperty("http://www.nbrs.org")
	private String httpWwwNbrsOrg;
	private final static long serialVersionUID = -210985162537038327L;

	/**
	 * No args constructor for use in serialization
	 */
	public Metadata() {
	}

	/**
	 * @param imsBoarding
	 * @param httpWwwNbrsOrg
	 * @param imsClassification
	 */
	public Metadata(String imsClassification, String imsBoarding, String httpWwwNbrsOrg) {
		super();
		this.imsClassification = imsClassification;
		this.imsBoarding = imsBoarding;
		this.httpWwwNbrsOrg = httpWwwNbrsOrg;
	}

	@JsonProperty("ims.classification")
	public String getImsClassification() {
		return imsClassification;
	}

	@JsonProperty("ims.classification")
	public void setImsClassification(String imsClassification) {
		this.imsClassification = imsClassification;
	}

	@JsonProperty("ims.boarding")
	public String getImsBoarding() {
		return imsBoarding;
	}

	@JsonProperty("ims.boarding")
	public void setImsBoarding(String imsBoarding) {
		this.imsBoarding = imsBoarding;
	}

	@JsonProperty("http://www.nbrs.org")
	public String getHttpWwwNbrsOrg() {
		return httpWwwNbrsOrg;
	}

	@JsonProperty("http://www.nbrs.org")
	public void setHttpWwwNbrsOrg(String httpWwwNbrsOrg) {
		this.httpWwwNbrsOrg = httpWwwNbrsOrg;
	}

}