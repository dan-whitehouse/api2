package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Environment {
	private String xmlns;
	@JacksonXmlProperty(isAttribute = true, localName = "type")
	private String type;
	@JacksonXmlProperty(localName = "sessionToken")
	private String sessionToken;
	@JacksonXmlProperty(localName = "solutionId")
	private String solutionId;
	@JacksonXmlProperty(localName = "defaultZone")
	private DefaultZone defaultZone;
	@JacksonXmlProperty(localName = "authenticationMethod")
	private String authenticationMethod;
	@JacksonXmlProperty(localName = "consumerName")
	private String consumerName;
	@JacksonXmlProperty(localName = "provisionedZones")
	private ProvisionedZones provisionedZones;
	@JacksonXmlProperty(localName = "infrastructureServices")
	private InfrastructureServices infrastructureServices;

	public String getXmlns() {
		return xmlns;
	}
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public InfrastructureServices getInfrastructureServices() {
		return infrastructureServices;
	}
	public void setInfrastructureServices(InfrastructureServices infrastructureServices) {
		this.infrastructureServices = infrastructureServices;
	}

	public String getAuthenticationMethod() {
		return authenticationMethod;
	}
	public void setAuthenticationMethod(String authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}

	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public DefaultZone getDefaultZone() {
		return defaultZone;
	}
	public void setDefaultZone(DefaultZone defaultZone) {
		this.defaultZone = defaultZone;
	}

	public String getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public ProvisionedZones getProvisionedZones() {
		return provisionedZones;
	}
	public void setProvisionedZones(ProvisionedZones provisionedZones) {
		this.provisionedZones = provisionedZones;
	}

	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	@Override
	public String toString() {
		return "ClassPojo [xmlns = " + xmlns + ", infrastructureServices = " + infrastructureServices + ", authenticationMethod = " + authenticationMethod + ", sessionToken = " + sessionToken + ", defaultZone = " + defaultZone + ", solutionId = " + solutionId + ", type = " + type + ", provisionedZones = " + provisionedZones + ", consumerName = " + consumerName + "]";
	}
}