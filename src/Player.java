
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rhettfitchett0289
 */
public class Player {

    protected String playerName;
    private final char symbol = 'x';
    private int numWins;
    protected char[][] bd;
    private boolean playerQuits = false;
    private Computer fakePlayer;

    public Player() {
        playerName = "Bob";
    }

    public Player(String playerNam, int numWinn) {
        playerName = playerNam;
        numWins = numWinn;
    }

    public void promptUsername() {
        System.out.println("Enter a username: ");
        Scanner inputName = new Scanner(System.in);
        playerName = inputName.nextLine();
    }

    public void playerMove(Board aBoard) {
        // Asks player for input, tests if input is not empty, then sets
        // the space to x for the player, or, tells the player to
        // repick the space by recursively calling the method
        bd = aBoard.getTheBoard();
        System.out.println(playerName + ", number where x will be placed(1-9, to stop playing type 0): ");
        Scanner input = new Scanner(System.in);
        int chosenSpace = input.nextInt();

        if (validMove(chosenSpace, aBoard) == true) {
            if (chosenSpace == 1) {
                bd[0][0] = 'x';
            }
            if (chosenSpace == 2) {
                bd[1][0] = 'x';
            }
            if (chosenSpace == 3) {
                bd[2][0] = 'x';
            }
            if (chosenSpace == 4) {
                bd[0][1] = 'x';
            }
            if (chosenSpace == 5) {
                bd[1][1] = 'x';
            }
            if (chosenSpace == 6) {
                bd[2][1] = 'x';
            }
            if (chosenSpace == 7) {
                bd[0][2] = 'x';
            }
            if (chosenSpace == 8) {
                bd[1][2] = 'x';
            }
            if (chosenSpace == 9) {
                bd[2][2] = 'x';
            }
            if (chosenSpace == 0) {
                playerQuits = true;
            }
            if ((chosenSpace < 0) || (chosenSpace > 9)) {
                System.out.println("Error, improper value, try 1-9...");
                playerMove(aBoard);
            }
        } else {
            System.out.println("Error, improper value, try 1-9...");
            playerMove(aBoard);
        } // I couldn't get the method for checking if non-integer values were entered to work
    }

    public boolean validMove(int chosenSpace, Board againBoard) {
        bd = againBoard.getTheBoard();

        if ((chosenSpace == 1) && (bd[0][0] != ' ')) {
            return false;
        }
        if ((chosenSpace == 2) && (bd[1][0] != ' ')) {
            return false;
        }
        if ((chosenSpace == 3) && (bd[2][0] != ' ')) {
            return false;
        }
        if ((chosenSpace == 4) && (bd[0][1] != ' ')) {
            return false;
        }
        if ((chosenSpace == 5) && (bd[1][1] != ' ')) {
            return false;
        }
        if ((chosenSpace == 6) && (bd[2][1] != ' ')) {
            return false;
        }
        if ((chosenSpace == 7) && (bd[0][2] != ' ')) {
            return false;
        }
        if ((chosenSpace == 8) && (bd[1][2] != ' ')) {
            return false;
        }
        if ((chosenSpace == 9) && (bd[2][2] != ' ')) {
            return false;
        }
        return true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public char[][] getBd() {
        return bd;
    }

    public boolean playerQuitGame() {
        return playerQuits;

    }
    
    
}
