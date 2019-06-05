package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlValue;


public class InfrastructureService
{
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
	@XmlValue
    private String value;

    public String getName () {
        return name;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", value = "+ value +"]";
    }*/
}