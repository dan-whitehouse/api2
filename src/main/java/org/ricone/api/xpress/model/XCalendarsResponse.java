package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCalendars"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XCalendarsResponse {

    @JsonProperty("xCalendars")
    @XmlElement(name = "xCalendars")
    private XCalendars xCalendars;

    public XCalendarsResponse() {
    }

    public XCalendarsResponse(XCalendars xCalendars) {
        super();
        this.xCalendars = xCalendars;
    }

    @JsonProperty("xCalendars")
    public XCalendars getXCalendars() {
        return xCalendars;
    }

    @JsonProperty("xCalendars")
    public void setXCalendars(XCalendars xCalendars) {
        this.xCalendars = xCalendars;
    }

    @Override
    public String toString() {
        return "XCalendarsResponse{" + "xCalendars=" + xCalendars + '}';
    }
}