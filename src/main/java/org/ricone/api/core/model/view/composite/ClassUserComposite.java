package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class ClassUserComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String userId;
    private Integer userSchoolYear;
    private String role;

    public ClassUserComposite() {
    }

    public ClassUserComposite(String userId, Integer userSchoolYear, String role) {
        this.userId = userId;
        this.userSchoolYear = userSchoolYear;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ClassUserComposite)) return false;

        ClassUserComposite that = (ClassUserComposite) o;

        if(userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if(userSchoolYear != null ? !userSchoolYear.equals(that.userSchoolYear) : that.userSchoolYear != null)
            return false;
        return role != null ? role.equals(that.role) : that.role == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userSchoolYear != null ? userSchoolYear.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
