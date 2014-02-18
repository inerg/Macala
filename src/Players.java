import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Author: Inerg
 * Date: 17/02/14
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Players {
    Board gameBoard;
    Scanner keyboardInput;
    Players()
    {
        gameBoard = new Board();
        keyboardInput = new Scanner(System.in);
    }

    public boolean playerAction(int playerNum)
    {
        boolean actionPossible = true;
        int playerSelection;
        String message = "Which box do you wish to select player " + playerNum + "?:";
        while(actionPossible)
        {
            System.out.println(message);
            playerSelection = keyboardInput.nextInt();

            if(gameBoard.checkSelection(playerNum, playerSelection));
            {
                actionPossible = gameBoard.playerAction(playerNum, playerSelection);
                if(gameBoard.checkFinish(playerNum))
                {
                    actionPossible = false;
                    return true;
                }
                if(actionPossible)
                {
                    message = "Your gem landed into your basket go again Player 1\n" +
                            "Which box do you wish to select player " + playerNum + "?:";
                }
            }
        }
        return false;
    }
    }
