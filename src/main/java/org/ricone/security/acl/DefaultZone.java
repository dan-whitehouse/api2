package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DefaultZone {
    @JacksonXmlProperty
    private String description;
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private String id;

    @JacksonXmlProperty
    public String getDescription ()
    {
        return description;
    }
    @JacksonXmlProperty
    public void setDescription (String description)
    {
        this.description = description;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    public String getId ()
    {
        return id;
    }
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [description = "+description+", id = "+id+"]";
    }
}