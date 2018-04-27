
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
public class Game {

    private Player thePlayer;
    private char[][] theBoard;
    private Computer pooterPlayer;
    private boolean tieGame = false;
    private boolean playerWinGame = false;
    private boolean botWinGame = false;
    private boolean playGameAgain = false;
    private int moveCounter;

    public boolean checkWin(char[][] thisBoard) {
        theBoard = thisBoard;

        if ((theBoard[0][0] == theBoard[0][1]) && (theBoard[0][0] == theBoard[0][2])) { //top left to bottom left
            if (theBoard[0][0] == 'x') { // I used https://www.programcreek.com/2014/04/check-if-array-contains-a-value-java/
                playerWin();
                return true;
            }
            if (theBoard[0][0] == 'o') {
                botWin();
                return true;
            }

        }
        if ((theBoard[1][0] == theBoard[1][1]) && (theBoard[1][0] == theBoard[1][2])) { //top middle to bottom middle
            if (theBoard[1][0] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[1][0] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[2][0] == theBoard[2][1]) && (theBoard[0][0] == theBoard[2][2])) { //top right to bottom right
            if (theBoard[2][0] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[2][0] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[0][0] == theBoard[1][0]) && (theBoard[0][0] == theBoard[2][0])) { //top left to top right
            if (theBoard[0][0] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[0][0] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[0][1] == theBoard[1][1]) && (theBoard[0][1] == theBoard[2][1])) {  //middle left to middle right
            if (theBoard[0][1] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[0][1] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[0][2] == theBoard[1][2]) && (theBoard[0][2] == theBoard[2][2])) { //bottom left to bottom right
            if (theBoard[0][2] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[0][2] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[0][0] == theBoard[1][1]) && (theBoard[0][0] == theBoard[2][2])) { //top left to bottom right
            if (theBoard[0][0] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[0][0] == 'o') {
                botWin();
                return true;
            }
        }
        if ((theBoard[0][2] == theBoard[1][1]) && (theBoard[0][2] == theBoard[2][0])) { //bottom left to top right
            if (theBoard[0][2] == 'x') {
                playerWin();
                return true;
            }
            if (theBoard[0][2] == 'o') {
                botWin();
                return true;
            }
        }

        return false;
    }

    public boolean checkTie(char[][] againBoard) {
        int unusedSpaceCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (againBoard[i][j] == ' ') {
                    unusedSpaceCounter += 1;
                }

            }
        }
        if (unusedSpaceCounter >= 1) {
            return false;
        }
        return true;
    }

    public void play1(Board sumBoard, Player humanPlayer) { // player makes move (probably with player.play1)
        playGameAgain = false;
        tieGame = false;
        playerWinGame = false;
        botWinGame = false;
        // these booleanse are reset every time the player makes a move, I'm an unfamiliar with some java loop syntax. If player resets game then these are made sure to be false, definitely could have been more efficient...
        theBoard = sumBoard.getTheBoard();
        thePlayer = humanPlayer;
        sumBoard.printBlueprintBoard();
        printBoard(theBoard);
        thePlayer.playerMove(sumBoard);
        moveCounter += 1;
        theBoard = thePlayer.getBd();

        if (thePlayer.playerQuitGame() == true) {
            tieGame();
        }

        checkTie(theBoard);
        checkWin(theBoard);

    }

    public int play2(Board sumBoard, Computer pootPlayer) { // computer makes move
        /*
        
    Rule 1: If I have a winning move, take it.
    Rule 2: If the opponent has a winning move, block it.
    Rule 3: If I can create a fork (two winning ways) after this move, do it.
    Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
    Rule 5: Place in the position such as I may win in the most number of possible ways.

         */

        theBoard = sumBoard.getTheBoard();
        pooterPlayer = pootPlayer;
        checkTie(theBoard);

        if (moveCounter == 9) { // if there are no more moves to make, bot will not do anything so that the loop can be broken
            System.out.println("Bot has run out of moves...");
            theBoard = pooterPlayer.getBd();
            checkWin(theBoard);
            checkTie(theBoard);
            return 0;

        }
        if (pooterPlayer.checkIfMiddleEmpty(theBoard) == true) { // if middle is empty the bot will take it with a method just for taking middle
            pooterPlayer.botMoveMiddle(sumBoard);
            moveCounter += 1; // updates how many moves have been made by both players
            theBoard = pooterPlayer.getBd(); // updates the board for future use (I hope)
            checkWin(theBoard); // checks if it has made the winning move, slightly outdated but still possibly useful
            checkTie(theBoard); // checks if it has caused a tie
            return 1;
        }

        if (pooterPlayer.botTakeWinningMove(sumBoard, 'o') != 0) { // bot tests if 'o' has a winning move, returning the board position as an integer or 0 if not
            pooterPlayer.botMove(sumBoard, 'o'); // since bot realized the method above didn't return zero it moves to the board's integer position
            moveCounter += 1;
            theBoard = pooterPlayer.getBd();
            checkWin(theBoard);
            checkTie(theBoard); // I don't think it can tie at this point but it can't be true here anyway
            return 2;
        }
        if (pooterPlayer.botTakeWinningMove(sumBoard, 'x') != 0) { // by this point bot couldnt take middle, doesn't have a inning move for itself, but will test if the player does
            pooterPlayer.botMove(sumBoard, 'x'); // if the bot is here it has detected that 'x' has a winning move and will attempt to put itself there instead
            moveCounter += 1;
            theBoard = pooterPlayer.getBd();
            checkWin(theBoard);
            checkTie(theBoard); // I don't think it can tie at this point but it can't be true here anyway
            return 3;
        }

        pooterPlayer.randBotMove(sumBoard); // at this point there's nothing to do but move randomly... middle must be taken, bot can't win and player can't win yet
        moveCounter += 1;
        theBoard = pooterPlayer.getBd();
        checkWin(theBoard);
        checkTie(theBoard);
        return 4;

    }

    /*
 public void loopGame(Board stupidBoard, Player againPlayer) { // a different idea I hadn't implemented for looping the game multiple iterations, instead the main loops the game by probing various booleans at the end of turns
        play1(stupidBoard, againPlayer);
    }
     */
    public void playerWin() {
        System.out.println((thePlayer.getPlayerName()) + " wins the game!");
        playerWinGame = true;
    }

    public void botWin() {
        System.out.println("The bot wins the game!");
        botWinGame = true;

    }

    public void tieGame() {
        tieGame = true;
        promptPlayAgain();

    }

    public void printBoard(char[][] printBoard) {
        System.out.println(printBoard[0][0] + "|" + printBoard[0][1] + "|"
                + printBoard[0][2]);
        System.out.println("-----");
        System.out.println(printBoard[1][0] + "|" + printBoard[1][1] + "|"
                + printBoard[1][2]);
        System.out.println("-----");
        System.out.println(printBoard[2][0] + "|" + printBoard[2][1] + "|"
                + printBoard[2][2]);
    }

    public boolean getPlayerWinGame() {
        return playerWinGame;
    }

    public boolean getBotWinGame() {
        return botWinGame;

    }

    public boolean getTieGame() {
        return tieGame;
    }

    public void promptPlayAgain() {
        char tempAnswer;
        System.out.println("Play again? (y/n): ");
        Scanner inputAgain = new Scanner(System.in);
        tempAnswer = inputAgain.findInLine(".").charAt(0);
        if ((tempAnswer == 'y') || (tempAnswer == 'Y')) {
            playGameAgain = true;
        }
    }

    public boolean playGameAgain() {
        return playGameAgain;
    }

    public void loopGame(Board sumBoard, Player humanPlayer, Computer pootPlayer) {
        do {
            if (moveCounter == 9) {
                if ((getPlayerWinGame() == false) && (getBotWinGame() == false)) {
                    tieGame = true;
                }
            }
            play1(sumBoard, humanPlayer);

            if (thePlayer.playerQuitGame() == true) {
                break;
            }
            if (playGameAgain == true) {
                sumBoard.resetBoard();
                moveCounter = 0;
                loopGame(sumBoard, humanPlayer, pootPlayer);

                break;
            }
            if (getPlayerWinGame() == true) {
                sumBoard.printBoard();
                promptPlayAgain();
                if (playGameAgain == false) {
                    break;
                }
                if (playGameAgain == true) {
                    sumBoard.resetBoard();
                    moveCounter = 0;
                    loopGame(sumBoard, humanPlayer, pootPlayer);
                    break;
                }
            }
            if (moveCounter == 9) {
                sumBoard.printBoard();
                promptPlayAgain();
                if (playGameAgain == false) {
                    break;
                }
                if (playGameAgain == true) {
                    sumBoard.resetBoard();
                    moveCounter = 0;
                    loopGame(sumBoard, humanPlayer, pootPlayer);
                    break;
                }
            }

            play2(sumBoard, pootPlayer);

            if (botWinGame == true) {
                sumBoard.printBoard();
                promptPlayAgain();
                if (playGameAgain == false) {
                    break;
                }
                if (playGameAgain == true) {
                    sumBoard.resetBoard();
                    loopGame(sumBoard, humanPlayer, pootPlayer);
                    break;
                }
            }

        } while ((getPlayerWinGame() == false) && (getBotWinGame() == false) && (getTieGame() == false) || (playGameAgain() == true));
    }
}
