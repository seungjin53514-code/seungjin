package sj;

import java.util.*;

public class Player {

    String name;
    int hp = 100;

    List<Card> hand = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public void removeCard(Card c){
        hand.remove(c);
    }
}