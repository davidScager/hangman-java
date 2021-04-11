package model;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class RandomWord {
    private String[] words; //array for all words from a selected file
    String randomWord; //String variable to store the random word

    //initializes the String array for the specific file
    RandomWord(){
        words = new String[3000];
    }

    //Method to be updated with language/difficulty selection with either MySql database or separate files
    public static String fileSelector(){
        return "wordListAmerican3000.txt";
    }

    /**
     /* Fills the words array from file using fileSelector().
     /* then initializes the randomWord variable with a
     /* randomly picked word from the words array.
     */
    public void pickWord() {
        File file = new File(fileSelector());
        int lines = 0;
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String word = scanner.nextLine();
                //System.out.println(word);
                words[lines]=word;
                lines++;
            }
        }catch(FileNotFoundException f){
            f.printStackTrace();
            System.out.println("File not found.");
        }
        randomWord = words[(int)(Math.random()*lines)];
    }

}
