package org.ricone.api.xpress.request.app;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement(name = "schools")
@JsonRootName(value = "schools")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"id", "name", "kv"})
public class School {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("kv")
    private HashMap<String, String> kv;

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

    @Override
    public String toString() { return "School{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", kv=" + kv + '}'; }
}