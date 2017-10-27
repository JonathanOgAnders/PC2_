package com.company;

import java.util.Collections;
import java.util.Stack;

public class Deck
{
    private int cardsInDeck;
    private Stack<Card> stack = new Stack<>();
    private Deck.Card[] drawnCards;

    //sync til tråd
    public synchronized Deck.Card drawOneCard()
    {
        System.out.println(Thread.currentThread().getName() + ": " + stack.peek());
        return stack.pop();
    }

    public Deck(int number)
    {
        for (int i = 0; i < 1; i++)
        {
            for(Suit suit : Suit.values())
            {
                for(Rank value : Rank.values())
                {
                    stack.add(new Card(suit,value));
                }
            }
        }
        cardsInDeck = stack.size();
        Collections.shuffle(stack);
        drawnCards = new Deck.Card[number];
    }

    public void setCardValues(int x)
    {
        if(x == 0)
        {
            Rank.ACE.setValue(14);
        }
        else
        {
            Rank.ACE.setValue(1);
        }
    }

    //inner class for kort - kan sammenligne
    public class Card implements Comparable<Card>
    {
        Suit suit;
        Rank rank;

        public Card(Suit suit, Rank value)
        {
            this.suit = suit;
            this.rank = value;
        }

        @Override //credit AndersK
        public int compareTo(Card o)
        {
            if(rank.getValue() == o.rank.getValue())
            {
                if(suit.getRank() > o.suit.getRank())
                {
                    return 1;
                }
                return -1;
            }
            if(rank.getValue() > o.rank.getValue())
            {
                return 1;
            }
            return -1;
        }

        public String toString()
        {
            return rank + " of " + suit;
        }
    }

    //ikke synchronized
    public Card draw()
    {
        return stack.pop();
    }

    public int getCardsInDeck()
    {
        return stack.size();
    }

    public Stack<Card> getStack()
    {
        return stack;
    }

    public Card[] getDrawnCards()
    {
        return drawnCards;
    }

    public synchronized boolean isDeckEmpty()
    {
        return stack.isEmpty();
    }
}
