package com.be.api.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {
    public String expand;

    public String self;

    public String id;

    public Fields fields;

    public String key;
}
