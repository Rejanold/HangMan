import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 * Author: Robert Hable
 * Date: 10/8/2019
 * Driver for our game giving basic information accepting a file for play and setting our frame up.
 * CO-Authors Blake Furlano and Mason Waters
 */

/**
 * Setting up our game panel size and location
 */
public class Game extends JFrame {
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 750;
    public static final int X_LOC = 100;
    public static final int Y_LOC = 100;

    /**
     * constructs our hangman panel from the HangmanPanel class
     * @param word the word passed fromt he command line
     */
    public Game(String word){
        JPanel hangpan = new HangmanPanel(word);
        add(hangpan);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(X_LOC, Y_LOC);


    }

    /**
     * accepts user input as the first command line argument as well as as telling the frame how to perform
     * @param args command line argument
     * @throws FileNotFoundException if the user enters some nonsense.
     */
    public static void main(String[] args) throws FileNotFoundException {

        if(args.length == 1){ //Checking to see if the users file exists
            String word = wordToGuess(args[0]);
            System.out.println(word);
            JFrame frame = new Game(word);
            frame.setTitle("Hangman Don't kill the poor guy!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        }
        else {
            System.out.print("Really!!!! Check your spelling!!!");
            System.exit(0);
        }





    }

    /**
     * accepts the file passed and generates an array list where we pick a random word
     * and the words size to be used in the hangman panel     *
     * @param fileName the filename recieved from args
     * @return returns the size of the word
     * @throws FileNotFoundException if the user enters nonsens
     */
    public static String wordToGuess(String fileName) throws FileNotFoundException {
        Random r = new Random();
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("This file is not present!" );
            System.exit(0);
        }
        ArrayList<String> words = new ArrayList<String>();
        Scanner input = new Scanner(file);
        while(input.hasNextLine()) {
            String randWord = input.next();
            words.add(randWord);

        }
        int rand = r.nextInt((words.size()));
        return words.get(rand);


    }
}
