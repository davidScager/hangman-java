package model;

import java.util.Scanner;

public class Game {
    //fields
    boolean newGame = false; //flag is set to true only when the player wants to start a new game
    int count; //counter to keep track of the players remaining lives
    String word; //Randomly picked word for the player to guess
    String update; //updates the player's progress by adding letters when guessed right
    String alphabet; //shows the player which letters they already guessed
    char input; //variable to stored player input when guessing

    //default constructor left empty until needed
    public Game(){
    }

    /**
     /*Asks the user if a new game should be started
     /* and to input either y or n.
     /* @return newGame (boolean) sets newGame flag to true or false
     */
    public boolean queryNewGame(){
        System.out.println("Do you wish to start a new game? y/n: ");
        //initialized with random char
        char answer = ' ';
        //do{}while() loop to keep trying until input is y/n
        do {
            Scanner qry1 = new Scanner(System.in);
            //cast first index as char
            try{
                answer = qry1.nextLine().charAt(0);
            }catch(StringIndexOutOfBoundsException e){
                //e.printStackTrace();
                //System.out.println("Please input 'y' or 'n'.");
            }
            if (answer == 'y') {
                newGame = true;
                //System.out.println("Player started new game.");
            } else if (answer == 'n') {
                //System.out.println("No new game started.");
            } else{
                System.out.println("Please input 'y' or 'n'.");
            }
        }while(!(answer=='y'||answer=='n'));
        return newGame;
    }

    /**
     /*Set up the variables for a nem game:
     /*count, word, update and alphabet.
     /*Calls the class method RandomWord.pickWord()
     /*Uses StringBuffer() to take account of ' and " "
     /*
     */
    public void gameSetup(){
        count=0;
        RandomWord newWord = new RandomWord();
        newWord.pickWord();
        word = newWord.randomWord;
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<word.length();i++) {
            if(word.charAt(i)==' '){
                sb.append(' ');
            }else if(word.charAt(i)=='\''){
                sb.append('\'');
            }else{
                sb.append('_'); }
        }
        update = sb.toString();
        alphabet = "abcdefghijklmnopqrstuvwxyz";
    }

    /**
     /* Checks if the guessed letter belongs to the random word.
     /* @param word (String) use the random word.
     /* @param input (char) use the letter being guessed.
     /* @return guess (boolean) true if guessed right, otherwise false.
     */
    public boolean letterCheck(String word, char input){
        boolean guess = false;
        for(int i=0; i<word.length();i++){
            char x = word.charAt(i);
            if(x!=input){
                continue;
            }else{
                guess = true;
            }
        }
        return guess;
    }

    /**
     /* Main game logic.
     /* Runs gameSetup(), creates Update class object and initializes a local message variable
     /* Keeps asking the player to input a letter until either the word is guessed or player is game over.
     /* After every guess displays current screen, updated player progress, alphabet with available letters,
     /* message depending on letterCheck() and player input request.
     /* After endgame conditions are met last screen and final message are displayed.
     */
    public void gameStart(){
        gameSetup();
        Update myUpdate = new Update();
        String message="";
        while(!(update.equals(word))) {
            if (count == 8) {
                Screen currentScreen = new Screen(count);
                System.out.println("You guessed wrong too many times.\n" +
                        "You're hanged man!");
                System.out.println("The right word was: "+word+".\n");
                break;
            }
            Screen currentScreen = new Screen(count);
            System.out.println(update + "\n" + alphabet + "\n" + message);
            if (count > 6) {
                System.out.println("Careful now...");
            }
            System.out.println("Please guess a letter or try the entire word:");
            Scanner scanner = new Scanner(System.in);
            String nameGuess = scanner.nextLine();
            if (nameGuess.length() > 2) {
                if (nameGuess.equals(word)) {
                    update = word;
                    break;
                } else {
                    count++;
                    message = "Not the right word, mate. \nYou're one step closer to the noose!";
                    continue;
                }
            }
            try {
                input = nameGuess.charAt(0);
                alphabet = alphabet.replace(input, '_');
                if (letterCheck(word, input)) {
                    update = myUpdate.updateProgress(word, update, input);
                    message = "Good guess, mate!\nKeep it up!";
                } else {
                    count++;
                    message = "Bad luck, mate. \nYou're one step closer to the noose!";
                }
            } catch (StringIndexOutOfBoundsException e) {
                //e.printStackTrace();
                //System.out.println("Please input a letter.");
            }
        }
        if (update.equals(word)) {
            Screen currentScreen = new Screen(count);
            System.out.println(" -~" + word + "~-\nWell done! \nYou've escaped the noose\n...for now.");
        }
    }

    /**
     /* Asks if player wants to start a new game, runs the game logic
     /* and reruns after endgame conditions are met (recursive).
     */
    public void newGame(){
        if (queryNewGame()) {
            System.out.println("Let's start guessing!");
            gameStart();
            newGame = false;
            newGame();
        }else{
            System.out.println("Too bad. Maybe next time.");
        }
    }

}
