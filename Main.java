package com.hangman;

public class Main {
    public static void main(String[] args) {
        Game myGame = new Game(); //create Game class object
        //display welcome message, initial screen and game rules
        System.out.println("""
                   | Welcome to|
                         o
                 -~H-A-N-G-M-A-N~-
                 ________^________
                |                 |
                |   _________     |
                |   |/    |       |
                |   |     O       |
                |   |    /|\\      |
                |   |     ^       |
                |   |    / \\      |
                |   |\\            |
                |  ------------   |
                |  |/        \\|   |
                |_________________|
                
                Each turn you can either guess one letter or the full word.
                But beware!
                If you guess wrong too many times you're Hanged!
                """);
        myGame.newGame(); //run the game
    }
}
