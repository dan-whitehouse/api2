package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"schoolYear"})
public class Metadata implements Serializable {

    @JsonProperty("schoolYear")
    private Integer schoolYear;
    private final static long serialVersionUID = -7504765401152453898L;

    /**
     * No args constructor for use in serialization
     */
    public Metadata() {
    }

    /**
     * @param schoolYear
     */
    public Metadata(Integer schoolYear) {
        super();
        this.schoolYear = schoolYear;
    }

    @JsonProperty("schoolYear")
    public Integer getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return Stream.of(schoolYear).allMatch(Objects::isNull);
    }

    @Override
    public String toString() {
        return "Metadata{" + "schoolYear='" + schoolYear + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Metadata)) return false;

        Metadata metadata = (Metadata) o;

        return schoolYear != null ? schoolYear.equals(metadata.schoolYear) : metadata.schoolYear == null;
    }

    @Override
    public int hashCode() {
        return schoolYear != null ? schoolYear.hashCode() : 0;
    }
}