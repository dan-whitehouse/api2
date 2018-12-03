package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class EntryExitCodeComposite implements Serializable {
    private static final long serialVersionUID = -188804471075557016L;

    protected String entryExitCodeRefId;
    protected Integer entryExitCodeSchoolYear;

    public EntryExitCodeComposite() {

    }

    public EntryExitCodeComposite(String entryExitCodeRefId, Integer entryExitCodeSchoolYear) {
        this.entryExitCodeRefId = entryExitCodeRefId;
        this.entryExitCodeSchoolYear = entryExitCodeSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entryExitCodeRefId == null) ? 0 : entryExitCodeRefId.hashCode());
        result = prime * result + ((entryExitCodeSchoolYear == null) ? 0 : entryExitCodeSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        EntryExitCodeComposite other = (EntryExitCodeComposite) obj;
        if(entryExitCodeRefId == null) {
            if(other.entryExitCodeRefId != null) return false;
        }
        else if(!entryExitCodeRefId.equals(other.entryExitCodeRefId)) return false;
        if(entryExitCodeSchoolYear == null) {
            if(other.entryExitCodeSchoolYear != null) return false;
        }
        else if(!entryExitCodeSchoolYear.equals(other.entryExitCodeSchoolYear)) return false;
        return true;
    }

}
