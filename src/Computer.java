
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rhettfitchett0289
 */
public class Computer extends Player {

    private String botName;
    private final char symbol = 'o';
    private int numWins;
    private Random randChoice;

    public Computer() {
        playerName = "Hal";
    }

    public Computer(String botNam) {
        botName = botNam;
    }

    /*
    Goal:
    Rule 1: If I have a winning move, take it.
    Rule 2: If the opponent has a winning move, block it.
    Rule 3: If I can create a fork (two winning ways) after this move, do it.
    Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
    Rule 5: Place in the position such as I may win in the most number of possible ways.
       
     */
    public void randBotMove(Board aBoard) {
        bd = aBoard.getTheBoard();
        randChoice = new Random();
        int chosenSpace = randChoice.nextInt(9) + 1;

        if (validMove(chosenSpace, aBoard) == true) {
            if (chosenSpace == 1) {
                bd[0][0] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 2) {
                bd[1][0] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 3) {
                bd[2][0] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 4) {
                bd[0][1] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 5) {
                bd[1][1] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 6) {
                bd[2][1] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 7) {
                bd[0][2] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 8) {
                bd[1][2] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }
            if (chosenSpace == 9) {
                bd[2][2] = 'o';
                System.out.println(botName + " has moved to " + chosenSpace);
            }

            if ((chosenSpace <= 0) || (chosenSpace > 9)) {

                randBotMove(aBoard);
            }
        } else {
            randBotMove(aBoard);
        }

    }

    public boolean checkIfMiddleEmpty(char[][] boardy) {
        if (boardy[1][1] == ' ') {
            return true;
        }
        return false;
    }

    public void botMoveMiddle(Board bored) {
        bd = bored.getTheBoard();
        bd[1][1] = 'o';

    }

    public void botMove(Board sumBored, char symbol) {
        bd = sumBored.getTheBoard();
        int chosenSpace = botTakeWinningMove(sumBored, symbol);
        if (chosenSpace == 1) {
            bd[0][0] = 'o';
        }
        if (chosenSpace == 2) {
            bd[1][0] = 'o';
        }
        if (chosenSpace == 3) {
            bd[2][0] = 'o';
        }
        if (chosenSpace == 4) {
            bd[0][1] = 'o';
        }
        if (chosenSpace == 5) {
            bd[1][1] = 'o';
        }
        if (chosenSpace == 6) {
            bd[2][1] = 'o';
        }
        if (chosenSpace == 7) {
            bd[0][2] = 'o';
        }
        if (chosenSpace == 8) {
            bd[1][2] = 'o';
        }
        if (chosenSpace == 9) {
            bd[2][2] = 'o';
        }
        
    }

    public int botTakeWinningMove(Board bored, char symbol) { // I most likely could've grouped these up with OR logic but being able to see each line labeled was nicer to me
        bd = bored.getTheBoard();

        if (bd[0][0] == symbol && bd[1][0] == symbol && bd[2][0] == ' ') { // choose bottom left
            return 3;
        }
        if (bd[0][0] == symbol && bd[2][0] == symbol && bd[1][0] == ' ') { // choose middle left
            return 2;
        }
        if (bd[1][0] == symbol && bd[2][0] == symbol && bd[0][0] == ' ') { // choose top left
            return 1;
        }
        if (bd[0][1] == symbol && bd[1][1] == symbol && bd[2][1] == ' ') { // choose bottom middle
            return 6;
        }
        if (bd[0][1] == symbol && bd[2][1] == symbol && bd[1][1] == ' ') { // choose middle
            return 5;
        }
        if (bd[1][1] == symbol && bd[2][1] == symbol && bd[0][1] == ' ') { // choose top middle
            return 4;
        }
        if (bd[0][2] == symbol && bd[1][2] == symbol && bd[2][2] == ' ') { // choose bottom right
            return 9;
        }
        if (bd[0][2] == symbol && bd[2][2] == symbol && bd[1][2] == ' ') { // choose middle right
            return 8;
        }
        if (bd[1][2] == symbol && bd[2][2] == symbol && bd[0][2] == ' ') { // choose top right
            return 7;
        }
        // now starts the horizontal conditions
        if (bd[0][1] == symbol && bd[0][2] == symbol && bd[0][0] == ' ') { // choose top left
            return 1;
        }
        if (bd[0][0] == symbol && bd[0][2] == symbol && bd[0][1] == ' ') { // choose top middle
            return 4;
        }
        if (bd[0][0] == symbol && bd[0][1] == symbol && bd[0][2] == ' ') { // choose top right
            return 7;
        }
        if (bd[1][1] == symbol && bd[1][2] == symbol && bd[1][0] == ' ') { // choose middle left
            return 2;
        }
        if (bd[1][0] == symbol && bd[1][2] == symbol && bd[1][1] == ' ') { // choose middle
            return 5;
        }
        if (bd[1][0] == symbol && bd[1][1] == symbol && bd[1][2] == ' ') { // choose middle right
            return 8;
        }
        if (bd[2][1] == symbol && bd[2][2] == symbol && bd[2][0] == ' ') { // choose bottom left
            return 3;
        }
        if (bd[2][0] == symbol && bd[2][2] == symbol && bd[2][1] == ' ') { // choose bottom middle
            return 6;
        }
        if (bd[2][0] == symbol && bd[2][1] == symbol && bd[2][2] == ' ') { // choose bottom right
            return 9;
        }
        // Diagonals are next
        if (bd[1][1] == symbol && bd[2][2] == symbol && bd[0][0] == ' ') { // choose top left 
            return 1;
        }
        if (bd[0][0] == symbol && bd[2][2] == symbol && bd[1][1] == ' ') { // choose middle
            return 5;
        }
        if (bd[0][0] == symbol && bd[1][1] == symbol && bd[2][2] == ' ') { // choose bottom right
            return 9;
        }
        if (bd[1][1] == symbol && bd[0][2] == symbol && bd[2][0] == ' ') { // choose bottom left
            return 3;
        }
        if (bd[2][0] == symbol && bd[0][2] == symbol && bd[1][1] == ' ') { // choose middle
            return 5;
        }
        if (bd[2][0] == symbol && bd[1][1] == symbol && bd[0][2] == ' ') { // choose top right
            return 7;
        }
        return 0;
        

        // most of this class was made useless once I gave it inheritance from player class and had  validation and movement methods that the bot could use also....   
    }
}
