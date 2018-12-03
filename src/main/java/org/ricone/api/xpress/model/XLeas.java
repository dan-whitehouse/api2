package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
@JsonRootName(value = "xLeas")
public class XLeas {
	@JsonProperty("xLea")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XLea> xLeas;

	public XLeas() {
		xLeas = new ArrayList<>();
	}

	public XLeas(List<XLea> xLeas) {
		super();
		this.xLeas = xLeas;
	}

	@JsonProperty("xLea")
	public List<XLea> getXLeas() {
		return xLeas;
	}

	@JsonProperty("xLea")
	public void setXLeas(List<XLea> xLea) {
		this.xLeas = xLea;
	}

	@Override
	public String toString() {
		return "XLeas{" + "xLeas=" + xLeas + '}';
	}
}