package com.be.api.leankit;

import com.be.api.HttpClientConfig;
import com.be.api.Https;
import com.be.api.model.leankit.Board;
import com.be.api.model.leankit.BoardUpdates;
import com.be.api.model.leankit.Card;
import com.be.api.model.leankit.CardForPost;
import com.be.presentation.SomeResource;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//"beseechmv@gmail.com" "symrakvnas" "https://beseech.leankit.com/kanban/api"
public class LeankitApi {

    private static final Logger logger = Logger.getLogger(SomeResource.class.getPackage().getName());

    public static Board getBoardById(String boardId, HttpClientConfig config) throws Exception {
        Client client = Https.getHttpsClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(config.login, config.password);
        client.register(feature);

        WebTarget baseTarget = client.target(config.baseUrl);
        WebTarget request = baseTarget.path("boards/" + boardId);
        Invocation.Builder invocationBuilder = request.request(MediaType.APPLICATION_JSON);
        logger.debug("getBoardById =before=");
        Board board = invocationBuilder.get(Board.class);
        logger.debug("getBoardById =after=");
        return board;
    }

    public static BoardUpdates getUpdatesBoardById(String boardId, int version, HttpClientConfig config) throws Exception {
        Client client = Https.getHttpsClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(config.login, config.password);
        client.register(feature);

        WebTarget baseTarget = client.target(config.baseUrl);
        WebTarget request = baseTarget.path("board/" + boardId + "/boardversion/" + version + "/CheckForUpdates");
        Invocation.Builder invocationBuilder = request.request(MediaType.APPLICATION_JSON);
        logger.debug("getBoardById =before=");
        BoardUpdates boardUpdates = invocationBuilder.get(BoardUpdates.class);
        logger.debug("getBoardById =after=");
        return boardUpdates;
    }


    public static Response setCard(CardForPost card, HttpClientConfig config) throws Exception {
        Client client = Https.getHttpsClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(config.login, config.password);
        client.register(feature);

        WebTarget baseTarget = client.target(config.baseUrl);
        WebTarget request = baseTarget.path("card/update");
        Invocation.Builder invocationBuilder = request.request(MediaType.APPLICATION_JSON);
        logger.debug("getBoardById =before=");
        Response response = invocationBuilder.post(Entity.entity(card, MediaType.APPLICATION_JSON));
        logger.debug("getBoardById =after=");
        return response;
    }


}
