package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class InfrastructureServices {
    @JacksonXmlElementWrapper(localName = "infrastructureServices", useWrapping = false)
    private List<InfrastructureService> infrastructureService;

    public List<InfrastructureService> getInfrastructureService ()
    {
        return infrastructureService;
    }
    public void setInfrastructureService (List<InfrastructureService> infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [infrastructureService = "+infrastructureService+"]";
    }
}
