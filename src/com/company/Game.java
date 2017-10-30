package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Game
{
    public static final Object myLockObject = new Object();
    private Deck deck;
    private ArrayList<Thread> players;
    private CheckDrawCards checkDrawCards;

    public Game(int j, Deck deck)
    {
        this.deck = deck;
        checkDrawCards = new CheckDrawCards(deck);
        players = new ArrayList<>();
        initPlayers(j);
    }

    public void startGame()
    {
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).start();
        }
        checkDrawCards.startCheckDrawnCards();
    }

    //tilføj players -threads- til player array
    public void initPlayers(int i)
    {
        for (int j = 0; j < i; j++)
        {
            players.add(new Thread(new Player(deck,"Thread"+j,j,deck.getDrawnCards()),"Thread"+j));
        }
    }

    public ArrayList<Thread> getPlayers()
    {
        return players;
    }

    public Deck getDeck()
    {
        return deck;
    }
}
