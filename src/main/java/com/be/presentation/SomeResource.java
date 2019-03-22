package com.be.presentation;

import com.be.api.leankit.LeankitApi;
import com.be.api.model.jira.Issue;
import com.be.api.model.leankit.Card;
import com.be.api.model.leankit.CardForPost;
import com.be.api.model.leankit.CardList;
import com.be.business.SomeBean;
import com.be.business.SomeBeanService;
import com.be.business.jira.IssueService;
import com.be.business.leankit.BoardInfo;
import com.be.business.leankit.CardService;
import com.be.helpers.OperationResult;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

@Path("/")
public class SomeResource {
    @EJB
    private SomeBeanService someBeanService;

    private static final Logger logger = Logger.getLogger(SomeResource.class.getPackage().getName());

    //private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSmbTest(SomeBean newsmbTest) throws Exception {
        OperationResult or = someBeanService.addSmbTest(newsmbTest);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), or.getErrorMessage()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putSmbTest(@PathParam("id") String id, SomeBean someBean) throws Exception {
        if (someBean == null) {
            throw new WebApplicationException(404);
        }
        someBean.setId(id);
        OperationResult or = someBeanService.updateSmbTest(someBean);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), or.getErrorMessage()).build();
    }

    private ArrayList<CardForPost> updateCardsFromIssues(String boardId, CardList cardList) {
        ArrayList<CardForPost> result = new ArrayList<>();
        if (BoardInfo.getVersion(boardId) <= cardList.Version) {
            BoardInfo.setVersion(boardId, cardList.Version);
        }
        logger.debug("New board version is : " + BoardInfo.getVersion(boardId));
        for (int c = 0; c < cardList.cards.size(); c++) {
            logger.debug("Updating card with ExternalCardID: " + cardList.cards.get(c).ExternalCardID);
            Issue issue = IssueService.getIssue(cardList.cards.get(c).ExternalCardID.trim());
            CardForPost cardForUpdate = new CardForPost(cardList.cards.get(c).Id);
            cardForUpdate.fillFromIssue(issue);
            CardService.setCard(cardForUpdate);
            result.add(cardForUpdate);
        }
        return result;
    }

    @GET
    @Path("/proxy/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<CardForPost> getBoardCardsProxy(@PathParam("id") String id) throws Exception {
        logger.debug("Proxy to board id: " + id);

        CardList cardList = CardService.getCardsWithJiraHeaders(id);
        return updateCardsFromIssues(id, cardList);
    }

    @GET
    @Path("/proxy/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<CardForPost> getBoardCardsUpdateProxy(@PathParam("id") String id) throws Exception {
        logger.debug("Proxy to update board id " + id + " from board version " + BoardInfo.getVersion(id));

        CardList cardList = CardService.getUpdatedCardsWithJiraHeaders(id, BoardInfo.getVersion(id));
        return updateCardsFromIssues(id, cardList);
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SomeBean getSmbTest(@PathParam("id") String id) throws Exception {
        logger.debug("Try to get SomeBean with id: " + id);
        SomeBean someBean = null;
        if (id.equals("getBoardById")) {
            someBean = new SomeBean();
            someBean.setId("Test SomeBean Id");
            someBean.setSomeDoubleField(07.07);
        } else {
            someBean = someBeanService.getSmbTest(id);
        }
        if (someBean == null) {
            logger.debug("Try to get null SomeBean");
            throw new WebApplicationException(404);
        }

        return someBean;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteSmbTest(@PathParam("id") String id) {
        OperationResult or = someBeanService.deletesmbTest(id);
        if (or.isOk()) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), or.getErrorMessage()).build();
    }
}