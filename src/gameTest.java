/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rhettfitchett0289
 */
public class gameTest {

    public static void main(String[] args) {
        Game theGame = new Game();
        Board ticTacBoard = new Board();
        Player thePlayer = new Player();
        Computer theBot = new Computer();

        thePlayer.promptUsername();
        System.out.println("Welcome to Tic-Tac-Toe " + thePlayer.getPlayerName() + "!");
        
        theGame.loopGame(ticTacBoard, thePlayer, theBot);
        // the loop is a do while that continuously has the bot and player play while also checking every loop whether any game ending conditions have been met or the player wants to stop
        
        //      The bot has a priority list:
        // 1) Take middle
        // 2) Take bot-winning tiles
        // 3) Take player-winning tiles
        // 4) make a random move if none of that is possible, thus creating and preventing player forks
        
        // There are a few bugs I found that happen since the way the game works has been altered drastically over time since the beginning
        //      I have redundant functions that exist either:
        // 1)for debugging the program
        // 2) the game has changed and the functions didn't need to be removed
        

    }
}
