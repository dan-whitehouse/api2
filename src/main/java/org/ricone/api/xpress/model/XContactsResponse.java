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
@JsonPropertyOrder({"xContacts"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XContactsResponse {

    @JsonProperty("xContacts")
    @XmlElement(name = "xContacts")
    private XContacts xContacts;

    public XContactsResponse() {
    }

    public XContactsResponse(XContacts xContacts) {
        super();
        this.xContacts = xContacts;
    }

    @JsonProperty("xContacts")
    public XContacts getXContacts() {
        return xContacts;
    }

    @JsonProperty("xContacts")
    public void setXContacts(XContacts xContacts) {
        this.xContacts = xContacts;
    }

    @Override
    public String toString() {
        return "XContactsResponse{" + "xContacts=" + xContacts + '}';
    }
}