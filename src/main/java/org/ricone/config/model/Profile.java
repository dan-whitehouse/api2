package org.ricone.config.model;

public class Profile {
    private String name;
    private String title;
    private String id;
    private String description;
    private String filterType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    @Override
    public String toString() {
        return "Profile{" + "name='" + name + '\'' + ", title='" + title + '\'' + ", id='" + id + '\'' + ", description='" + description + '\'' + ", filterType='" + filterType + '\'' + '}';
    }
}
