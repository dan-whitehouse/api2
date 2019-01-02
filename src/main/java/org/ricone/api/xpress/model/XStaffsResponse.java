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
@JsonPropertyOrder({"xStaffs"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XStaffsResponse {

    @JsonProperty("xStaffs")
    @XmlElement(name = "xStaffs")
    private XStaffs xStaffs;

    public XStaffsResponse() {
    }

    public XStaffsResponse(XStaffs xStaffs) {
        super();
        this.xStaffs = xStaffs;
    }

    @JsonProperty("xStaffs")
    public XStaffs getXStaffs() {
        return xStaffs;
    }

    @JsonProperty("xStaffs")
    public void setXStaffs(XStaffs xStaffs) {
        this.xStaffs = xStaffs;
    }

    @Override
    public String toString() {
        return "XStaffsResponse{" + "xStaffs=" + xStaffs + '}';
    }
}