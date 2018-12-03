package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class SchoolComposite implements Serializable {
    private static final long serialVersionUID = -8352062885923364027L;

    protected String schoolRefId;
    protected Integer schoolSchoolYear;

    public SchoolComposite() {
    }

    public SchoolComposite(String schoolRefId, Integer schoolSchoolYear) {
        super();
        this.schoolRefId = schoolRefId;
        this.schoolSchoolYear = schoolSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolRefId == null) ? 0 : schoolRefId.hashCode());
        result = prime * result + ((schoolSchoolYear == null) ? 0 : schoolSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolComposite other = (SchoolComposite) obj;
        if(schoolRefId == null) {
            if(other.schoolRefId != null) return false;
        }
        else if(!schoolRefId.equals(other.schoolRefId)) return false;
        if(schoolSchoolYear == null) {
            if(other.schoolSchoolYear != null) return false;
        }
        else if(!schoolSchoolYear.equals(other.schoolSchoolYear)) return false;
        return true;
    }
}
