package org.ricone.api.xpress.request.app;

import com.fasterxml.jackson.annotation.*;
import org.ricone.config.model.IFilter;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "districts")
@JsonRootName(value = "districts")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"id", "name", "kv", "filters", "schools"})
public class District {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("kv")
    private HashMap<String, String> kv;

    @JsonProperty("filters")
    private List<IFilter> filters;

    @JsonProperty("schools")
    private List<School> schools;

    @XmlElement
    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    @JsonProperty("kv")
    public HashMap<String, String> getKv() { return kv; }
    @JsonProperty("kv")
    public void setKv(HashMap<String, String> kv) { this.kv = kv; }

    /*
        In order to remove xsi:type="x____Filter" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" from the xml output on each of the filters
        I needed to change the annotation from @XmlElement to @XmlAnyElement(lax=true). By doing this I also had to go to all of the XFilter objects
        and add the @XmlRootElement(name = "filters") annotation. The name value being important.
        This is due to the fact that XmlAnyElement doesn't care what object it takes, but it must be a root object.
        When the root object doesn't have a name, it takes the name of the class, which would break the json/xml symmetry.
     */
    @XmlAnyElement(lax=true)
    @JsonProperty("filters")
    public List<IFilter> getFilters() {
        if(filters == null) {
            filters = new ArrayList<>();
        }
        return filters;
    }
    @JsonProperty("filters")
    public void setFilters(List<IFilter> filters) { this.filters = filters; }


    @XmlAnyElement(lax=true)
    @JsonProperty("schools")
    public List<School> getSchools() {
        if(schools == null) {
            schools = new ArrayList<>();
        }
        return schools;
    }
    @JsonProperty("schools")
    public void setSchools(List<School> schools) { this.schools = schools; }

    @Override
    public String toString() { return "District{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", kv=" + kv + ", filters=" + filters + '}'; }
}