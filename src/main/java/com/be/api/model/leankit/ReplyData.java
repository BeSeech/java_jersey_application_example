package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyData
{
    public int Version;

    //public CardTypes[] CardTypes;

    //public BoardUsers[] BoardUsers;

    public String AvailableTags;

    public String Id;

    public Line[] Lanes;

    //public Backlog[] Backlog;

    public String Title;


    @Override
    public String toString()
    {
        return "ClassPojo [Version = "+Version+", Id = "+Id+", Title = "+Title+"]";
    }
}
