package com.be.api.jira;

import com.be.api.HttpClientConfig;
import com.be.api.Https;
import com.be.api.model.jira.Issue;
import com.be.presentation.SomeResource;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//"kononchuk.valerii@pdffiller.team" "symrakvnas" "https://pdffiller.atlassian.net/rest/api/latest"
public class JiraApi {

    private static final Logger logger = Logger.getLogger(SomeResource.class.getPackage().getName());

    public static Issue getIssueById(String issueId, HttpClientConfig config) throws Exception{
        Client client = Https.getHttpsClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(config.login, config.password);
        client.register(feature);

        WebTarget baseTarget = client.target(config.baseUrl);
        WebTarget request = baseTarget.path(new JiraUrl().issue().id(issueId).getUrl()).queryParam("fields", "summary,status");
        Invocation.Builder invocationBuilder = request.request(MediaType.APPLICATION_JSON);
        System.out.println("getBoardById =before= sysout");
        logger.debug("getIssueById =before=");
        Issue issue = invocationBuilder.get(Issue.class);
        logger.debug("getIssueById =after=");
        logger.debug("issue = " + issue.toString());
        return issue;

    }

}
