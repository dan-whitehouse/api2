package org.ricone.security.acl;

public class ProvisionedZones
{
    private ProvisionedZone provisionedZone;

    public ProvisionedZone getProvisionedZone ()
    {
        return provisionedZone;
    }

    public void setProvisionedZone (ProvisionedZone provisionedZone)
    {
        this.provisionedZone = provisionedZone;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [provisionedZone = "+provisionedZone+"]";
    }
}