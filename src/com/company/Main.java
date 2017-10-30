package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int number = 4 ;
        Deck deck = new Deck(number);
        deck.setCardValues(0);
        Game game = new Game(number, deck);

        for (int i = 0; i < game.getPlayers().size(); i++)
        {
            System.out.println(game.getPlayers().get(i).toString());
        }

        game.startGame();
    }
}
