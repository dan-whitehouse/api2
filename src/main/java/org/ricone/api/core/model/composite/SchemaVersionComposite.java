package org.ricone.api.core.model.composite;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.0.0.2
 * @since 2016-08-03
 */

public class SchemaVersionComposite implements java.io.Serializable {
    private static final long serialVersionUID = -5220447978182945197L;
    protected Integer major;
    protected Integer minor;
    protected Integer bugFix;

    public SchemaVersionComposite() {
    }

    public SchemaVersionComposite(Integer major, Integer minor, Integer bugFix) {
        this.major = major;
        this.minor = minor;
        this.bugFix = bugFix;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bugFix == null) ? 0 : bugFix.hashCode());
        result = prime * result + ((major == null) ? 0 : major.hashCode());
        result = prime * result + ((minor == null) ? 0 : minor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchemaVersionComposite other = (SchemaVersionComposite) obj;
        if(bugFix == null) {
            if(other.bugFix != null) return false;
        }
        else if(!bugFix.equals(other.bugFix)) return false;
        if(major == null) {
            if(other.major != null) return false;
        }
        else if(!major.equals(other.major)) return false;
        if(minor == null) {
            if(other.minor != null) return false;
        }
        else if(!minor.equals(other.minor)) return false;
        return true;
    }


}
