import javax.swing.*;
import java.awt.*;

/**
 * Author: Robert Hable
 * Date: 10/8/2019
 * The drawing class for our hangman game.
 * CO-Authors Blake Furlano and Mason Waters
 */
public class HangmanDraw extends JComponent {
    public String[] wordGuess;
    private String word;
    private int wrongGuess;
    private boolean Win = false;
    private boolean Gallows = false;
    private boolean Head = false;
    private boolean Body = false;
    private boolean LeftArm = false;
    private boolean RightArm = false;
    private boolean LeftLeg = false;
    private boolean RightLeg = false;

    /**
     * Constructor for our Draw class
     * @param num tracks the number of incorrect guesses
     * @param word the word passed for checking
     * @param array the array of user guesses
     */
    public HangmanDraw(int num, String word, String[] array) {
        this.wordGuess = array;
        this.word = word;
        this.wrongGuess = num;
    }

    /**
     * Method to show text of winning
     * This method does not work I was unable to finish the program
     */
    public void Win() {
        Win = true;
        repaint();
    }

    /**
     * Draws the Gallows
     */
    public void Gallows() {
        Gallows = true;
        repaint();
    }

    /**
     * Draws the head
     */
    public void Head() {
        Head = true;
        repaint();
    }

    /**
     * Draws the Body.
     */
    public void Body() {
        Body = true;
        repaint();
    }

    /**
     * Draws the left Arm.
     */
    public void LeftArm() {
        LeftArm = true;
        repaint();
    }

    /**
     * Draws the right arm
     */
    public void RightArm() {
        RightArm = true;
        repaint();
    }

    /**
     * Draws the left leg
     */
    public void LeftLeg() {
        LeftLeg = true;
        repaint();
    }

    /**
     * Draws the right leg
     */
    public void RightLeg() {
        RightLeg = true;
        repaint();
    }

    /**
     * Checkng booleans for what to draw
     * @param g Graphic classs
     */
    public void paintComponent(Graphics g) {

        if (Win) {
            Font f = new Font("", Font.ROMAN_BASELINE, 30);
            g.setFont(f);
            g.drawString("You Won!", 20, 365);
        }
        if (Gallows) {
            g.setColor(Color.black);
            g.fillRect(100, 400, 600, 15); //width
            g.fillRect(395, 0, 15, 400);
            g.fillRect(200, 0, 200, 15);
            g.fillRect(200, 15, 20, 15);
        }
        if (Head) {
            g.setColor(Color.black);
            g.fillOval(171, 28, 75, 75);
            g.setColor(Color.WHITE);
            g.fillOval(181, 38, 55, 55);
            g.setColor(Color.black);
        }
        if (Body) {//Body
            g.fillRect(200, 100, 10, 150);
        }
        if (LeftArm) {
            int xleft[] = {200, 200, 160, 160};
            int yleft[] = {120, 130, 160, 150};
            g.fillPolygon(xleft, yleft, 4);
        }
        if (RightArm) {
            int xright[] = {208, 208, 253, 253};
            int yright[] = {120, 130, 160, 150};
            g.fillPolygon(xright, yright, 4);
        }
        if (LeftLeg) {
            int xLleg[] = {200, 200, 160, 160};
            int yLleg[] = {240, 250, 280, 270};
            g.fillPolygon(xLleg, yLleg, 4);
        }
        if (RightLeg) {
            int xRleg[] = {208, 208, 253, 253};
            int yRleg[] = {240, 250, 280, 270};
            g.fillPolygon(xRleg, yRleg, 4);
            Font f = new Font("", Font.ROMAN_BASELINE, 15);
            g.setFont(f);
            g.drawString("You Lose! The word was: " + word, 20, 365);
        }
    }
}
