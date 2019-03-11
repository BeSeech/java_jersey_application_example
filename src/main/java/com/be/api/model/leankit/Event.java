package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    public String CardId;
    public String EventType;
    public String EventDateTime;
    public String Message;

}
