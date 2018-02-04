package com.be;

import com.be.presentation.SomeResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig
{
    public JerseyApplication()
    {
        super();
        register(SomeResource.class);
        register(JsonProvider.class);
        register(JacksonFeature.class);

    }
}