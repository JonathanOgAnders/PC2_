package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collector;

public class CheckDrawCards
{
    private Deck deck;
    private Deck.Card[] drawnCards;

    public void startCheckDrawnCards()
    {
        do
        {
            while(!checkIfEmptyIndex())
            {
                compareDrawnCards();
            }
        }while (!(deck.isDeckEmpty()));
        compareDrawnCards();
    }

    public CheckDrawCards(Deck deck)
    {
        this.deck = deck;
        this.drawnCards = deck.getDrawnCards();
    }

    public boolean checkIfEmptyIndex()
    {
        for (int i = 0; i < drawnCards.length; i++)
        {
            if(drawnCards[i] == null)
            {
                return true;
            }
        }
        return false;
    }

    public synchronized void compareDrawnCards()
    {
        int size = deck.getDrawnCards().length;
        for (int i = 0; i < size; i++)
        {
            if(deck.getDrawnCards()[i] != null)
            {
                if(i == size-1)
                {
                    System.out.println(Arrays.deepToString(deck.getDrawnCards()));
                    Arrays.fill(deck.getDrawnCards(),null);
                    synchronized (Game.myLockObject)
                    {
                        Game.myLockObject.notifyAll();
                    }
                }
            }
        }
    }
}
