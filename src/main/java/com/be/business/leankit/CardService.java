package com.be.business.leankit;

import com.be.api.HttpClientConfig;
import com.be.api.leankit.LeankitApi;
import com.be.api.model.leankit.*;
import com.be.business.jira.IssueService;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class CardService {
    public static HttpClientConfig getHttpClientConfig() {
        return new HttpClientConfig("beseechmv@gmail.com", "symrakvnas", "https://beseech.leankit.com/kanban/api");
    }

    public static CardList getUpdatedCards(String boardId, int version) {
        try {
            HttpClientConfig httpClientConfig = getHttpClientConfig();
            BoardUpdates boardUpdates = LeankitApi.getUpdatesBoardById(boardId, version, httpClientConfig);
            if (!boardUpdates.ReplyData[0].HasUpdates) {
                return new CardList();
            }

            Event[] events = boardUpdates.ReplyData[0].Events;
            ArrayList<String> updatedCardId = new ArrayList<>();
            for (int i = 0; i < events.length; i++) {
                updatedCardId.add(events[i].CardId);
            }


            Line[] lines = boardUpdates.ReplyData[0].AffectedLanes;
            CardList result = new CardList();
            result.Version = boardUpdates.ReplyData[0].CurrentBoardVersion;

            for (int l = 0; l < lines.length; l++) {
                Card[] cards = lines[l].Cards;
                for (int c = 0; c < cards.length; c++) {
                    if (updatedCardId.contains(cards[c].Id)) {
                        result.cards.add(cards[c]);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }


    public static CardList getCards(String boardId) {
        try {
            HttpClientConfig httpClientConfig = getHttpClientConfig();
            Board board = LeankitApi.getBoardById(boardId, httpClientConfig);

            Line[] lines = board.ReplyData[0].Lanes;
            CardList result = new CardList();
            result.Version = board.ReplyData[0].Version;

            for (int l = 0; l < lines.length; l++) {
                Card[] cards = lines[l].Cards;
                for (int c = 0; c < cards.length; c++) {
                    result.cards.add(cards[c]);
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static CardList getUpdatedCardsWithJiraHeaders(String boardId, int version) {
        try {
            CardList cardsList = getUpdatedCards(boardId, version);
            CardList result = new CardList();
            result.Version = cardsList.Version;

            for (int c = 0; c < cardsList.cards.size(); c++) {
                if (IssueService.isProjectKey(cardsList.cards.get(c).getProjectKey()))
                    result.cards.add(cardsList.cards.get(c));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static CardList getCardsWithJiraHeaders(String boardId) {
        try {
            CardList cardsList = getCards(boardId);
            CardList result = new CardList();
            result.Version = cardsList.Version;

            for (int c = 0; c < cardsList.cards.size(); c++) {
                if (IssueService.isProjectKey(cardsList.cards.get(c).getProjectKey()))
                    result.cards.add(cardsList.cards.get(c));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static Response setCard(CardForPost card) {
        try {
            HttpClientConfig httpClientConfig = getHttpClientConfig();
            Response response = LeankitApi.setCard(card, httpClientConfig);
            return response;
        } catch (Exception e) {
            return null;
        }
    }
}
