package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    public String ReplyCode;

    public String ReplyText;

    public ReplyData[] ReplyData;

    @Override
    public String toString()
    {
        return "Board [ReplyCode = "+ReplyCode+", ReplyText = "+ReplyText+"]";
    }}
