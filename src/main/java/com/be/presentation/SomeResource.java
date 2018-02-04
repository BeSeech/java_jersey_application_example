package com.be.presentation;

import com.be.business.SomeBean;
import com.be.business.SomeBeanService;
import com.be.helpers.OperationResult;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.Integer.parseInt;

@Path("/")
public class SomeResource
{
    @EJB
    private SomeBeanService someBeanService;

    private static final Logger logger = Logger.getLogger(SomeResource.class.getPackage().getName());

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postsmbTest(SomeBean newsmbTest) throws Exception
    {
        OperationResult or = someBeanService.addsmbTest(newsmbTest);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), or.getErrorMessage()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putsmbTest(@PathParam("id") String id, SomeBean someBean) throws Exception
    {
        if (someBean == null) {
            throw new WebApplicationException(404);
        }
        someBean.setId(id);
        OperationResult or = someBeanService.updatesmbTest(someBean);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), or.getErrorMessage()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SomeBean getsmbTest(@PathParam("id") String id) throws Exception
    {
        logger.debug("Try to get SomeBean with id: " + id);
        SomeBean someBean = null;
        if (id.equals("test")) {
            someBean = new SomeBean();
            someBean.setId("Test SomeBean Id");
            someBean.setSomedoubleField(07.07);
        }
        else {
            someBean = someBeanService.getsmbTest(id);
        }
        if (someBean == null) {
            throw new WebApplicationException(404);
        }

        return someBean;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deletesmbTest(@PathParam("id") String id)
    {
        OperationResult or = someBeanService.deletesmbTest(id);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), or.getErrorMessage()).build();
    }
}