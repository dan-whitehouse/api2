package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "name", "type", "identifier", "parent", "children"})
public class Org extends Base implements Serializable {
	private final static long serialVersionUID = -1025191997431202352L;
	@JsonProperty("name")
	@ApiModelProperty(position = 5, value = "", example = "Albany High School")
	private String name;
	@JsonProperty("type")
	@ApiModelProperty(position = 6, value = "The set of permitted tokens for the type of organization")
	private OrgType type;
	@JsonProperty("identifier")
	@ApiModelProperty(position = 7, value = "Human readable identifier for this org")
	private String identifier;
	@JsonProperty("parent")
	@ApiModelProperty(position = 8, value = "Link to Org i.e. the parent Org 'sourcedId'")
	private GUIDRef parent;
	@JsonProperty("children")
	@ApiModelProperty(position = 9, value = "Link to Org i.e. the child Org 'sourcedId'")
	private List<GUIDRef> children = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 */
	public Org() {
	}

	/**
	 * @param name
	 * @param children
	 * @param parent
	 * @param type
	 * @param identifier
	 */
	public Org(String name, OrgType type, String identifier, GUIDRef parent, List<GUIDRef> children) {
		super();
		this.name = name;
		this.type = type;
		this.identifier = identifier;
		this.parent = parent;
		this.children = children;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("type")
	public OrgType getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(OrgType type) {
		this.type = type;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@JsonProperty("parent")
	public GUIDRef getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(GUIDRef parent) {
		this.parent = parent;
	}

	@JsonProperty("children")
	public List<GUIDRef> getChildren() {
		return children;
	}

	@JsonProperty("children")
	public void setChildren(List<GUIDRef> children) {
		this.children = children;
	}

}