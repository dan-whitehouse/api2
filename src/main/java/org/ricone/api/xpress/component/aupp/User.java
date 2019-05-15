package org.ricone.api.xpress.component.aupp;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-10-31
 */

public class User {
    private String refId;
    private String username;
    private String password;

    public User(String refId, String username, String password) {
        this.refId = refId;
        this.username = username;
        this.password = password;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}