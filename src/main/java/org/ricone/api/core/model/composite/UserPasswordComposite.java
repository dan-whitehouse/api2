package org.ricone.api.core.model.composite;

import java.io.Serializable;

public class UserPasswordComposite implements Serializable {
    private static final long serialVersionUID = 4607875896783565676L;

    protected String entityRefId;
    protected Integer entitySchoolYear;
    protected String entityType;
    protected String appId;

    public UserPasswordComposite() {
    }

    public UserPasswordComposite(String entityRefId, Integer entitySchoolYear, String entityType, String appId) {
        this.entityRefId = entityRefId;
        this.entitySchoolYear = entitySchoolYear;
        this.entityType = entityType;
        this.appId = appId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((entityRefId == null) ? 0 : entityRefId.hashCode());
        result = prime * result + ((entitySchoolYear == null) ? 0 : entitySchoolYear.hashCode());
        result = prime * result + ((entityType == null) ? 0 : entityType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        UserPasswordComposite other = (UserPasswordComposite) obj;
        if(appId == null) {
            if(other.appId != null) return false;
        }
        else if(!appId.equals(other.appId)) return false;
        if(entityRefId == null) {
            if(other.entityRefId != null) return false;
        }
        else if(!entityRefId.equals(other.entityRefId)) return false;
        if(entitySchoolYear == null) {
            if(other.entitySchoolYear != null) return false;
        }
        else if(!entitySchoolYear.equals(other.entitySchoolYear)) return false;
        if(entityType == null) {
            if(other.entityType != null) return false;
        }
        else if(!entityType.equals(other.entityType)) return false;
        return true;
    }
}