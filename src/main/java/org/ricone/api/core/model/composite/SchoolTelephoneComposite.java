package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class SchoolTelephoneComposite implements Serializable {
    private static final long serialVersionUID = -7917402556223348526L;

    protected String schoolPhoneRefId;
    protected Integer schoolPhoneSchoolYear;

    public SchoolTelephoneComposite() {

    }

    public SchoolTelephoneComposite(String schoolPhoneRefId, Integer schoolPhoneSchoolYear) {
        this.schoolPhoneRefId = schoolPhoneRefId;
        this.schoolPhoneSchoolYear = schoolPhoneSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolPhoneRefId == null) ? 0 : schoolPhoneRefId.hashCode());
        result = prime * result + ((schoolPhoneSchoolYear == null) ? 0 : schoolPhoneSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolTelephoneComposite other = (SchoolTelephoneComposite) obj;
        if(schoolPhoneRefId == null) {
            if(other.schoolPhoneRefId != null) return false;
        }
        else if(!schoolPhoneRefId.equals(other.schoolPhoneRefId)) return false;
        if(schoolPhoneSchoolYear == null) {
            if(other.schoolPhoneSchoolYear != null) return false;
        }
        else if(!schoolPhoneSchoolYear.equals(other.schoolPhoneSchoolYear)) return false;
        return true;
    }

}
