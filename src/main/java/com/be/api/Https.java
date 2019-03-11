package com.be.api;

import com.be.presentation.SomeResource;
import org.apache.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Https {
    private static final Logger logger = Logger.getLogger(SomeResource.class.getPackage().getName());

    public static Client getHttpsClient() throws Exception {
        SSLContext sc = SSLContext.getInstance("SSL");
        TrustManager[] trustAllCerts = { new InsecureTrustManager() };
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
        return ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
    }

}
