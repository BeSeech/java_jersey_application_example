package com.be.business.jira;

import com.be.api.HttpClientConfig;
import com.be.api.jira.JiraApi;
import com.be.api.model.jira.Issue;

import java.util.Arrays;

public class IssueService {
    public static final String[] PROJECT_KEY = new String[] {"SNSP", "SNAPI", "SN", "SNF", "AS", "MKT", "RCT"};

    public static Boolean isProjectKey(String key) {
        return Arrays.asList(IssueService.PROJECT_KEY).contains(key);
    }

    public static Issue getIssue(String issueId) {
        try {
            HttpClientConfig httpClientConfig = new HttpClientConfig("kononchuk.valerii@pdffiller.team", "symrakvnas", "https://pdffiller.atlassian.net/rest/api/latest");
            Issue issue = JiraApi.getIssueById(issueId, httpClientConfig);

            return issue;
        } catch (Exception e) {
            return null;
        }
    }

}
