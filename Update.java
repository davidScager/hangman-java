package com.hangman;

public class Update {

    /**
     /* Updates the player progress feedback by replacing the right '_'s
     /* with the player input.
     /* Uses integer array (locations()) to replace single or multiple instances
     /* of the input.
     /* @param word (String) word player has to guess
     /* @param progress (String) containing '_'s initially
     /* @param input (char) letter guessed by the player
     /* @return sb.toString() (String) StringBuffer
     */
    public String updateProgress(String word, String progress, char input){
        char[] progressArray = new char[word.length()];
        for(int i=0; i<word.length();i++){
            progressArray[i]=progress.charAt(i);
        }
        int[] updateLocs = locations(word,input);
        for(int i=0;i<word.length();i++) {
            if(updateLocs[i]==1){
                progressArray[i]=input;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (char c : progressArray) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     /* Searches a string for instances of a character and
     /* returns the locations of that character as integer
     /* array containing 0's and 1's.
     /* @param word (String) given word
     /* @param input (char) character to record instances
     /* @return locs (int[]) integer array of word.length(); 1=input present, 0=not present
     */
    private int[] locations(String word, char input){
        int start = 0;
        int[] locs = new int[word.length()];
        for(int i = start; i<word.length(); i++){
            if(input==word.charAt(i)){
                locs[i]=1;
            }else{
                locs[i]=0;
            }
            start++;
        }
        return locs;
    }

}
