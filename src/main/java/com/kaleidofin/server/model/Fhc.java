package com.kaleidofin.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import JsonToMapConverted.JsonToMapConverter;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "fhc_transaction")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(
	    name = "json", 
	    typeClass = JsonStringType.class
	)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Fhc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank
    @Column(name="session_id")
    private String sessionId;

    @NotBlank
    @Column(name="name")
    private String name;
    
    @NotBlank
    @Column(name="email")
    private String email;

//    @NotBlank
    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private Map<String, Object> requestPayload;

    
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> responsePayload;
    
    @NotBlank
    @Column(name="status")
    private String status;
    
    
    @Column(name="type_form_id")
    private Integer typeFormId;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId(String session_id) {
        return session_id;
    }

    public void setSessionId(String session_id) {
        this.sessionId = session_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Map<String, Object> getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(Map<String, Object> requestPayload) {
        this.requestPayload = requestPayload;
    }
    
    public Map<String, Object> getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(Map<String, Object> responsePayload) {
        this.responsePayload = responsePayload;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getTypeFormId() {
        return typeFormId;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeFormId(int typeFormId) {
        this.typeFormId = typeFormId;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}