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
@JsonPropertyOrder({"xStudents"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XStudentsResponse {

    @JsonProperty("xStudents")
    @XmlElement(name = "xStudents")
    private XStudents xStudents;

    public XStudentsResponse() {
    }

    public XStudentsResponse(XStudents xStudents) {
        super();
        this.xStudents = xStudents;
    }

    @JsonProperty("xStudents")
    public XStudents getXStudents() {
        return xStudents;
    }

    @JsonProperty("xStudents")
    public void setXStudents(XStudents xStudents) {
        this.xStudents = xStudents;
    }

    @Override
    public String toString() {
        return "XStudentsResponse{" + "xStudents=" + xStudents + '}';
    }
}