package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class ClassGradeComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String gradeId;
    private Integer gradeSchoolYear;

    public ClassGradeComposite() {
    }

    public ClassGradeComposite(String gradeId, Integer gradeSchoolYear) {
        this.gradeId = gradeId;
        this.gradeSchoolYear = gradeSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ClassGradeComposite)) return false;

        ClassGradeComposite that = (ClassGradeComposite) o;

        if(gradeId != null ? !gradeId.equals(that.gradeId) : that.gradeId != null) return false;
        return gradeSchoolYear != null ? gradeSchoolYear.equals(that.gradeSchoolYear) : that.gradeSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = gradeId != null ? gradeId.hashCode() : 0;
        result = 31 * result + (gradeSchoolYear != null ? gradeSchoolYear.hashCode() : 0);
        return result;
    }
}
