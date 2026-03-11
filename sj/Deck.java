package sj;

import java.util.*;

public class Deck {

    List<Card> cards = new ArrayList<>();
    Random r = new Random();

    public Deck(){

        for(int i=0;i<60;i++){

            if(i%2==0)
                cards.add(new Card("검","attack",10+r.nextInt(10),0));

            else
                cards.add(new Card("방패","defense",0,5+r.nextInt(8)));
        }
    }

    public Card draw(){

        return cards.get(r.nextInt(cards.size()));
    }
}