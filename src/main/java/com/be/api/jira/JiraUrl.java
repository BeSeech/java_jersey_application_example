package com.be.api.jira;

public class JiraUrl {
    //const
    public static final String BASE_URL = "https://pdffiller.atlassian.net/rest/api/latest";
    public static final String ISSUE = "issue";

    //var
    protected String url = "";

    //methods
    public JiraUrl base() {
        addToUrl(BASE_URL);
        return this;
    }

    public JiraUrl issue() {
        addToUrl(ISSUE);
        return this;
    }

    public JiraUrl id(String id) {
        addToUrl(id);
        return this;
    }

    public String getUrl() {
        return this.getUrl(true);
    }

    public String getUrl(Boolean skipLastDivider) {
        String result = url;
        if (skipLastDivider) {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }


    protected void addToUrl(String url) {
        this.url += url + "/";
    }

}
