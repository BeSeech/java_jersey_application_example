package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatesReplyData {
    public Boolean HasUpdates;

    public int CurrentBoardVersion;

    public Event[] Events;

    public String Id;

    public Line[] AffectedLanes;

    @Override
    public String toString() {
        return "UpdatesReplyData [Version = " + CurrentBoardVersion + ", Id = " + Id + "]";
    }
}
