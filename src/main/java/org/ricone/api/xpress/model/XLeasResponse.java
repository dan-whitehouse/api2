package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLeas"})
@JacksonXmlRootElement()
public class XLeasResponse {

    @JsonProperty("xLeas")
    @JacksonXmlProperty(localName = "xLeas")
    private XLeas xLeas;

    public XLeasResponse() {
    }

    public XLeasResponse(XLeas xLeas) {
        super();
        this.xLeas = xLeas;
    }

    @JsonProperty("xLeas")
    public XLeas getXLeas() {
        return xLeas;
    }

    @JsonProperty("xLeas")
    public void setXLeas(XLeas xLeas) {
        this.xLeas = xLeas;
    }

    @Override
    public String toString() {
        return "XLeasResponse{" + "xLeas=" + xLeas + '}';
    }
}