package org.ricone.error;

public class Error {
    private String url;
    private String message;
    private int status;
    private String description;

    public Error() {
    }

    public Error(String url, int status, String message, String description) {
        this.url = url;
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}