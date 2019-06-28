package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLeas"})
public class XLeasResponse {

    @JsonProperty("xLeas")
    private XLeas xLeas;

    public XLeasResponse() {
    }

    public XLeasResponse(XLeas xLeas) {
        super();
        this.xLeas = xLeas;
    }

    @JsonProperty("xLeas")
    @XmlElementWrapper(nillable = true)
    public XLeas getXLeas() {
        return xLeas;
    }

    @JsonProperty("xLeas")
    @XmlElementWrapper(nillable = true)
    public void setXLeas(XLeas xLeas) {
        this.xLeas = xLeas;
    }

    @Override
    public String toString() {
        return "XLeasResponse{" + "xLeas=" + xLeas + '}';
    }
}