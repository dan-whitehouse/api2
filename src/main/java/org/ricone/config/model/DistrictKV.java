package org.ricone.config.model;

public class DistrictKV {
    private String id;
    private String district_id;
    private String field;
    private String value;

    public DistrictKV() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DistrictKV{" + "id='" + id + '\'' + ", district_id='" + district_id + '\'' + ", field='" + field + '\'' + ", value='" + value + '\'' + '}';
    }
}
