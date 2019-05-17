package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"primary", "other"})
public class StaffAssignments implements Serializable {

    @JsonProperty("primary")
    private Integer primary;
    @JsonProperty("other")
    private Integer other;
    private final static long serialVersionUID = -6902863392721427610L;

    /**
     * No args constructor for use in serialization
     */
    public StaffAssignments() {
    }

    /**
     * @param other
     * @param primary
     */
    public StaffAssignments(Integer primary, Integer other) {
        super();
        this.primary = primary;
        this.other = other;
    }

    @JsonProperty("primary")
    public Integer getPrimary() {
        return primary;
    }

    @JsonProperty("primary")
    public void setPrimary(Integer primary) {
        this.primary = primary;
    }

    @JsonProperty("other")
    public Integer getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Integer other) {
        this.other = other;
    }

}