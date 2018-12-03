package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class SchoolIdentifierComposite implements Serializable {
    private static final long serialVersionUID = -8755392503549314139L;

    protected String schoolIdentifierRefId;
    protected Integer schoolIdentifierSchoolYear;

    public SchoolIdentifierComposite() {

    }

    public SchoolIdentifierComposite(String schoolIdentifierRefId, Integer schoolIdentifierSchoolYear) {
        this.schoolIdentifierRefId = schoolIdentifierRefId;
        this.schoolIdentifierSchoolYear = schoolIdentifierSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolIdentifierRefId == null) ? 0 : schoolIdentifierRefId.hashCode());
        result = prime * result + ((schoolIdentifierSchoolYear == null) ? 0 : schoolIdentifierSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolIdentifierComposite other = (SchoolIdentifierComposite) obj;
        if(schoolIdentifierRefId == null) {
            if(other.schoolIdentifierRefId != null) return false;
        }
        else if(!schoolIdentifierRefId.equals(other.schoolIdentifierRefId)) return false;
        if(schoolIdentifierSchoolYear == null) {
            if(other.schoolIdentifierSchoolYear != null) return false;
        }
        else if(!schoolIdentifierSchoolYear.equals(other.schoolIdentifierSchoolYear)) return false;
        return true;
    }


}
