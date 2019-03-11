package com.be.api.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
    public String name;

    public String self;

    public String description;

    public String iconUrl;

    public String id;

}
