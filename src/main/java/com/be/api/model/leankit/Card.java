package com.be.api.model.leankit;

import com.be.api.model.jira.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    public String ExternalCardID;

    public String ExternalSystemName;

    public String ExternalSystemUrl;

    public String Id;

    public String Title;

    @JsonIgnore
    public String getProjectKey() {
        if (this.ExternalCardID.indexOf('-') > 0)
            return this.ExternalCardID.substring(0, this.ExternalCardID.indexOf('-'));
        return "";

    }

    @JsonIgnore
    public void fillFromIssue(Issue issue) {
        this.ExternalSystemName = "Jira";
        this.ExternalCardID = issue.key;
        this.Title = issue.fields.summary + "[" + issue.fields.status.name + "]";
        this.ExternalSystemUrl = "https://pdffiller.atlassian.net/browse/" + issue.key;
    }

    @Override
    public String toString() {
        return "Card [Id = " + Id + ", ExternalCardID = " + ExternalCardID + "]";
    }
}

