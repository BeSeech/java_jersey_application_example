package com.be.api.model.leankit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Line
{
    public String Description;

    public String ActivityName;

    public String IsDefaultDropLane;

    public String ActivityId;

    public String[] ChildLaneIds;

    public String Title;

    public String Index;

    public String ClassType;

    public String Orientation;

    public String IsDrillthroughDoneLane;

    public String CardLimit;

    public String Active;

    public String Type;

    public String LaneState;

    public String[] SiblingLaneIds;

    public String TaskBoardId;

    public String Id;

    public String Width;

    public Card[] Cards;

    public String ParentLaneId;

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", ActivityName = "+ActivityName+", IsDefaultDropLane = "+IsDefaultDropLane+", ActivityId = "+ActivityId+", ChildLaneIds = "+ChildLaneIds+", Title = "+Title+", Index = "+Index+", ClassType = "+ClassType+", Orientation = "+Orientation+", IsDrillthroughDoneLane = "+IsDrillthroughDoneLane+", CardLimit = "+CardLimit+", Active = "+Active+", Type = "+Type+", LaneState = "+LaneState+", SiblingLaneIds = "+SiblingLaneIds+", TaskBoardId = "+TaskBoardId+", Id = "+Id+", Width = "+Width+", Cards = "+Cards+", ParentLaneId = "+ParentLaneId+"]";
    }
}