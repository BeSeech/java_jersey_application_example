package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardUpdates {

    public String ReplyCode;

    public String ReplyText;

    public UpdatesReplyData[] ReplyData;

    @Override
    public String toString()
    {
        return "BoardUpdates [ReplyCode = "+ReplyCode+", ReplyText = "+ReplyText+"]";
    }}
