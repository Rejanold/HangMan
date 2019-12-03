import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Author: Robert Hable
 * Date: 10/8/2019
 * Construction of our game panel and all information needed to play the game
 * CO-Authors Blake Furlano and Mason Waters
 */


/**
 * The data fields for our hangman panel
 */
public class HangmanPanel extends JPanel {
    String wordToGuess = "";
    //Masterpanel for stuff
    private JPanel masterpanel = new JPanel();
    //for my top row
    private JPanel topPanel = new JPanel();
    private JLabel wordGuess = new JLabel("Word To Guess: ");
    private JTextField word = new JTextField(16);
    //Label for how many letters the word has.
    private JPanel rowTwo = new JPanel();
    private JPanel rowThree = new JPanel();
    private JLabel guess = new JLabel("Guess a letter: ");
    private JTextField userGuess = new JTextField(30);
    private JButton submitButton = new JButton("Enter your guess");
    //bottom part for dispalying the letters already guessed.
    private JPanel rowFour = new JPanel();
    private JLabel guessedLettersLabel = new JLabel("Letters Guessed: ");
    private JTextField lettersGuessedField = new JTextField(25);
    public ArrayList<String> correctWord = new ArrayList<String>();
    public ArrayList<String> guessedLetters = new ArrayList<String>();
    public ArrayList<String> hiddenWord = new ArrayList<String>();
    public String[] blankWord = null;
    public String[] userGuesses;
    public int incorGuess = 0;
    public int guesses;
    private HangmanDraw hangmanDraw;


    /**
     * Constructor for our hangman Panel
     * @param wordToGuess this is the word passed from the
     * command line argument dictionary or whatever file is passed.
     */
    public HangmanPanel(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        System.out.println(wordToGuess); //error checking
        JLabel letters = new JLabel("The word to guess has " + wordToGuess.length() + " letters");
        setLayout(new BorderLayout());
        masterpanel.setLayout(new BoxLayout(masterpanel, BoxLayout.PAGE_AXIS));
        topPanel.add(wordGuess);
        JTextField word = new JTextField(16);
        topPanel.add(word);
        word.setEditable(false);
        masterpanel.add(topPanel);
        rowTwo.add(letters);
        masterpanel.add(rowTwo);
        rowThree.add(guess);
        rowThree.add(userGuess);
        rowThree.add(submitButton);
        ActionListener subButt = new buttonListener();
        submitButton.addActionListener(subButt);
        masterpanel.add(rowThree);
        rowFour.add(lettersGuessedField);
        lettersGuessedField.setEditable(false);
        masterpanel.add(rowFour);
        add(masterpanel, BorderLayout.NORTH);
        blankWord = new String[wordToGuess.length()];
        Arrays.fill(blankWord, "_");
        hangmanDraw = new HangmanDraw(incorGuess, wordToGuess, blankWord);
        hangmanDraw.setPreferredSize(new Dimension(375,375));
        add(hangmanDraw);
        userGuesses = new String[guesses];


    }

    /**
     * THe actual method to play the game, it checks for valid input and if it exists in the word
     * if the letter exists it replaced the blank array if not it calls on our DrawHangman class
     * to add appropriate peices.
     * @param input user guess from the editable field
     */
    public void playGame(String input) {
        String[] correctWord = new String[wordToGuess.length()];
        wordGuess.setText(Arrays.toString(blankWord));
        for (int i = 0; i < wordToGuess.length(); i++) {
            String attempt = wordToGuess.substring(i, i + 1);
            correctWord[i] = attempt;
        }
        int correctGuess = 0;
        if (exists(input, correctWord) && !exists(input, userGuesses)) {
            for (int i = 0; i < correctWord.length; i++) {
                if (input.equals(correctWord[i])) {
                    blankWord[i] = input;
                    wordGuess.setText(Arrays.toString(blankWord));
                    correctGuess++;
                    List<String> check = Arrays.asList(blankWord);
                }
            }
            userGuesses = Arrays.copyOf(userGuesses, userGuesses.length + 1);
            userGuesses[guesses] = input;
            lettersGuessedField.setText(Arrays.toString(userGuesses));
            guesses++;

        } else if (!exists(input, correctWord) && !exists(input, userGuesses)) {
            incorGuess++;
            if(incorGuess == 1){
                hangmanDraw.Gallows();
            }
            if(incorGuess == 2){
                hangmanDraw.Head();
            }
            if(incorGuess == 3){
                hangmanDraw.Body();
            }
            if(incorGuess == 4){
                hangmanDraw.LeftArm();
            }
            if(incorGuess == 5){
                hangmanDraw.RightArm();
            }
            if(incorGuess == 6){
                hangmanDraw.LeftLeg();
            }
            if(incorGuess == 7){
                hangmanDraw.RightLeg();
            }
            userGuesses = Arrays.copyOf(userGuesses, userGuesses.length + 1);
            userGuesses[guesses] = input;
            lettersGuessedField.setText(Arrays.toString(userGuesses));
            guesses++;
        } else {
            JOptionPane.showMessageDialog(null, "You have guessed that already. ");
        }
    }

    /**
     * Method to accept input from the user via the submit button
     */
    public class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = userGuess.getText();
            String test = input.toLowerCase();
            int check = input.length();
            if (check > 1) {
                JOptionPane.showMessageDialog(null, "You need to guess one letter.");
                userGuess.setText("");
            } else if (!Character.isLetter(input.charAt(0))) {
                JOptionPane.showMessageDialog(null, "You need to guess one letter.");
                userGuess.setText("");
            } else {
                playGame(test);
                userGuess.setText("");
            }
        }
    }

    /**
     * Method to check for the correct letter
     * @param letter the letter passed for the guess
     * @param arr the letters in the array that will be walked
     * @return returns true or false determined by the letter guessed
     */
    public boolean exists(String letter, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (letter.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }
}