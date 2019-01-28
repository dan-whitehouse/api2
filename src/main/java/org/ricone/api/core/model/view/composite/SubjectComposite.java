package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class SubjectComposite implements Serializable {
    private static final long serialVersionUID = -6775456278122710145L;

    private String subjectId;
    private Integer subjectSchoolYear;

    public SubjectComposite() {
    }

    public SubjectComposite(String subjectId, Integer subjectSchoolYear) {
        this.subjectId = subjectId;
        this.subjectSchoolYear = subjectSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof SubjectComposite)) return false;

        SubjectComposite that = (SubjectComposite) o;

        if(subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;
        return subjectSchoolYear != null ? subjectSchoolYear.equals(that.subjectSchoolYear) : that.subjectSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = subjectId != null ? subjectId.hashCode() : 0;
        result = 31 * result + (subjectSchoolYear != null ? subjectSchoolYear.hashCode() : 0);
        return result;
    }
}
