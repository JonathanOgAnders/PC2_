package com.company;

public enum Suit
{
    CLUBS(1, "Clubs"), DIAMONDS(2, "Diamonds"), HEARTS(3, "Hearts"), SPADES(4, "Spades");

    private int rank;
    private String suitName;

    Suit(int rank, String suitName)
    {
        this.rank = rank;
        this.suitName = suitName;
    }

    public int getRank()
    {
        return rank;
    }

    public String getSuitName()
    {
        return suitName;
    }
}
