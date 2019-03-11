package com.be.api.model.leankit;

import java.util.ArrayList;

public class CardList {
    public ArrayList<Card> cards = null;
    public int Version = 0;

    public CardList() {
        Version = 0;
        cards = new ArrayList<>();
    }
}
