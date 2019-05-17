package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "percent" })
public class LocalId {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("percent")
	private String percent;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public LocalId() {
	}

	/**
	 * 
	 * @param total
	 * @param percent
	 */
	public LocalId(Integer total, String percent) {
		super();
		this.total = total;
		this.percent = percent;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("percent")
	public String getPercent() {
		return percent;
	}

	@JsonProperty("percent")
	public void setPercent(String percent) {
		this.percent = percent;
	}

}