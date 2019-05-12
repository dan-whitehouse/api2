package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLeas"})
public class XLeasResponse {

    @JsonProperty("xLeas")
    @XmlElementWrapper(nillable = true)
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