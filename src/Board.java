
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rhettfitchett
 */
public class Board {

    private char[][] theBoard;

    public Board() {
        theBoard = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                theBoard[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println(theBoard[0][0] + "|" + theBoard[0][1] + "|"
                + theBoard[0][2]);
        System.out.println("-----");
        System.out.println(theBoard[1][0] + "|" + theBoard[1][1] + "|"
                + theBoard[1][2]);
        System.out.println("-----");
        System.out.println(theBoard[2][0] + "|" + theBoard[2][1] + "|"
                + theBoard[2][2]);
    }

    public void printBlueprintBoard() {

        System.out.println("1" + "|" + "4" + "|"
                + "7");
        System.out.println("-----");
        System.out.println("2" + "|" + "5" + "|"
                + "8");
        System.out.println("-----");
        System.out.println("3" + "|" + "6" + "|"
                + "9");
        System.out.println("\n" + "Each space you can move is numbered...");

    }

    public char[][] getTheBoard() {
        return theBoard;
    }

    public boolean promptPlayAgain() {
        System.out.println("Play again? (y/n): ");
        Scanner input = new Scanner(System.in);
        char tempChoice = input.findInLine(".").charAt(0);

        if (tempChoice == 'y' || tempChoice == 'Y') {
            resetBoard();
            return true;
        }

        return false;
    }
}
