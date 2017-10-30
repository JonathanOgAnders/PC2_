package com.company;

import java.util.Arrays;

public class Player implements Runnable
{
    private Deck deck;
    private String name;
    private int number;

    Deck.Card playerCard;
    private Deck.Card[] drawnCards;

    public Player (Deck deck, String s, int number, Deck.Card[] array)
    {
        this.deck = deck;
        name = s;
        this.number = number;
        this.drawnCards = array;
    }

    // run for player(threads).
    // Så længe decket ikke er tomt, prøver den at trækker et kort, og kalder wait() på et statisk final objekt.
    @Override
    public void run()
    {
        while (!(deck.isDeckEmpty()))
        {
            drawnCards[number] = deck.drawOneCard();
            try
            {
                synchronized (Game.myLockObject)
                {
                    Game.myLockObject.wait();
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Deck.Card getPlayerCard()
    {
        return playerCard;
    }

    public String getName()
    {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    public Deck.Card[] getDrawnCards()
    {
        return drawnCards;
    }

    //    public Player()
    //    }
    //    {
}
