package org.ricone.api.xpress.request.app;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.config.model.App;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "app")
@JsonRootName(value = "app")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "debugLevel", "includeExitedStudents", "districts"})
public class Application {
    @JsonProperty("id")
    private String id;

    @JsonProperty("debugLevel")
    private String debugLevel;

    @JsonProperty("includeExitedStudents")
    private Boolean includeExitedStudents;

    @JsonProperty("districts")
    private List<District> districts = new ArrayList<>();

    public Application() {
    }

    public Application(App app) {
        this.id = app.getId();
        this.debugLevel = app.getSeverityLevelDataAPI();
        this.includeExitedStudents = BooleanUtils.toBoolean(app.getIncludeExitedStudents());
    }

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
    @JsonProperty("debugLevel")
    public String getDebugLevel() {
        return debugLevel;
    }
    @JsonProperty("includeExitedStudents")
    public void setDebugLevel(String debugLevel) {
        this.debugLevel = debugLevel;
    }

    @XmlElement
    @JsonProperty("includeExitedStudents")
    public Boolean getIncludeExitedStudents() {
        return includeExitedStudents;
    }
    @JsonProperty("includeExitedStudents")
    public void setIncludeExitedStudents(Boolean includeExitedStudents) { this.includeExitedStudents = includeExitedStudents; }

    @XmlElement
    @JsonProperty("districts")
    public List<District> getDistricts() {
        return districts;
    }
    @JsonProperty("districts")
    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}