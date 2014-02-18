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
        boolean selectionValid = false;
        int playerSelection = 0;

        String message = "Which box do you wish to select player " + playerNum + "?:";
        while(actionPossible)
        {


            while(selectionValid != true)
            {
                System.out.println(message);

                playerSelection = keyboardInput.nextInt() - 1;
                if(playerSelection < 6 && playerSelection >= 0)
                {
                    if (gameBoard.checkSelection(playerNum, playerSelection))
                        selectionValid = true;
                    else
                        System.out.println("You selected a box with nothing in it try again");
                }
                else
                    System.out.println("Selection out of bounds try again");
            }

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
                    selectionValid = false;
                }
        }
        return false;
    }
    }
