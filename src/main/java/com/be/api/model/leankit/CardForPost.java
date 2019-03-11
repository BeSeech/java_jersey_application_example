package com.be.api.model.leankit;

import com.be.api.model.jira.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardForPost {
    public String ExternalCardID;

    public String ExternalSystemName;

    public String ExternalSystemUrl;

    public String CardId;

    public String Title;


    public CardForPost(String cardId) {
        this.CardId = cardId;
    }

    @JsonIgnore
    public void fillFromIssue(Issue issue) {
        this.ExternalSystemName = "Jira";
        this.ExternalCardID = issue.key;
        this.Title = issue.fields.summary + " [" + issue.fields.status.name + "]";
        this.ExternalSystemUrl = "https://pdffiller.atlassian.net/browse/" + issue.key;
    }

    @Override
    public String toString() {
        return "CardForPost [Id = " + CardId + ", ExternalCardID = " + ExternalCardID + "]";
    }
}

