package com.be.api;

public class HttpClientConfig {
    public String login;
    public String password;
    public String baseUrl;

    public HttpClientConfig(String login, String password, String baseUrl) {
        this.login = login;
        this.password = password;
        this.baseUrl = baseUrl;
    }
}
