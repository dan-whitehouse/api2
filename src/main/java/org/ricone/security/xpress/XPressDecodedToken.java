package org.ricone.security.xpress;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.security.BaseDecodedToken;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"application_id", "iat", "exp", "iss"})
public class XPressDecodedToken implements Serializable {
    private final static long serialVersionUID = -2798265389832101234L;
    @JsonProperty("application_id")
    private String application_id;
    @JsonProperty("iat")
    private Date iat;
    @JsonProperty("exp")
    private Date exp;
    @JsonProperty("iss")
    private String iss;

    XPressDecodedToken() {
        super();
    }

    @JsonProperty("application_id")
    public String getApplicationId() {
        return application_id;
    }
    @JsonProperty("application_id")
    public void setApplicationId(String application_id) {
        this.application_id = application_id;
    }

    @JsonProperty("iat")
    public Date getIat() {
        return iat;
    }
    @JsonProperty("iat")
    public void setIat(long iat) {
        this.iat = new Date(Long.parseLong(iat + "000"));
    }

    @JsonProperty("exp")
    public Date getExp() {
        return exp;
    }
    @JsonProperty("exp")
    public void setExp(long exp) {
        this.exp = new Date(Long.parseLong(exp + "000"));
    }

    @JsonProperty("iss")
    public String getIss() {
        return iss;
    }
    @JsonProperty("iss")
    public void setIss(String iss) {
        this.iss = iss;
    }
}