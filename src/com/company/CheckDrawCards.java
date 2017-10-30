package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collector;

public class CheckDrawCards
{
    private Deck deck;
    private Deck.Card[] drawnCards;

    // Kører så længe decket ikke er tomt, og prøver at sammenligne kort i arrayet med trukkede kort.
    // tjekker også til sidst, da trådene godt kan nå at tage kort fra decket mellem der spørges på om arrayet er tomt,
    // og decket er tomt.
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

    //får arrayet med trådenes trukkede kort fra decket.
    public CheckDrawCards(Deck deck)
    {
        this.deck = deck;
        this.drawnCards = deck.getDrawnCards();
    }

    //Tjekker om arrayet med trukkede kort er tomt.
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

    //
    public synchronized void compareDrawnCards()
    {
        int size = deck.getDrawnCards().length;
        for (int i = 0; i < size; i++)
        {
//            if(deck.getDrawnCards()[i] != null)
//            {
                if(i == size-1)
                {
                    System.out.println(Arrays.deepToString(deck.getDrawnCards()));
                    Arrays.fill(deck.getDrawnCards(),null);
                    synchronized (Game.myLockObject)
                    {
                        Game.myLockObject.notifyAll();
                    }
                }
//            }
        }
    }
}
