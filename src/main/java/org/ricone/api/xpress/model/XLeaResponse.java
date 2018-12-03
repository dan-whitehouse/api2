package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
@XmlRootElement
public class XLeaResponse {

    @JsonProperty("xLea")
    @XmlElement(name = "xLea")
    private XLea xLea;

    public XLeaResponse() {
    }

    public XLeaResponse(XLea xLea) {
        super();
        this.xLea = xLea;
    }

    @JsonProperty("xLea")
    public XLea getXLea() {
        return xLea;
    }

    @JsonProperty("xLea")
    public void setXLea(XLea xLea) {
        this.xLea = xLea;
    }

    @Override
    public String toString() {
        return "XLeaResponse{" + "xLea=" + xLea + '}';
    }
}