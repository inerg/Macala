import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Author: Inerg
 * Date: 17/02/14
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board{
    private int[][] board;
    private int p1Score;
    private int p2Score;

    public Board(){
        board = new int[2][6];
        int y;
        int x;
        for(y = 0; y < 2; y++)
        {
            for(x = 0; x < 6; x++)
            {
                board[y][x] = 3;
            }
        }
    }

    /**
     * Checks where the player has selected for if it is empty
     * @param player Takes in the player that is getting checked.
     * @param selection Takes in what the player has selected.
     * @return Returns whether the action is valid or not. True means it is.
     */
    public boolean checkSelection(int player, int selection)
    {
        if(player == 1 && board[1][selection] > 0)
            return true;
        else
            if(player == 2 && board[0][selection] >0)
                return true;
            else
                return false;
    }

    /**
     * Allows a player to take an action
     * @param player Takes in the player that is getting checked.
     * @param selection Takes in what the player has selected.
     * @return Returns whether the player can take another action. True means they can do so.
     */
    public boolean playerAction(int player, int selection)
    {
        int i;
        int c;
        int selected;
        boolean selectWasZero = false;
        boolean scored = false;
        int actionLine;
        boolean canRepeat = false;
        if(player == 1)
        {
            actionLine = 1;
            selected = board[actionLine][selection];
            board[actionLine][selection] = 0;
            if(selection == 5)
            {
                selection--;
                selectWasZero = true;
            }
            for(i = selection + 1; i < 6 && selected != 0; i++)
            {
                if(selectWasZero)
                {
                    selectWasZero = false;
                    selection++;
                }
                else
                {
                    board[actionLine][i] += 1;
                    selected--;
                }
                if(i == 5 && selected != 0)
                {
                    p1Score += 1;
                    scored = true;
                    selected--;
                    if(selected > 0)
                    {
                        actionLine = 0;
                        for(c = 5; c >= 0 && selected != 0 ; c--)
                        {
                            board[actionLine][c] += 1;
                            selected--;
                            if(selected == 0)
                            {
                                c = 0;
                            }
                        }
                    }
                    else
                    {
                        canRepeat = true;
                        i = 6;
                    }
                }

            }
            if(scored != true)
            {
                if(selection < 6)
                    checkOther(1, i - 1);
                else
                    checkOther(1, i);
            }
        }
        else
        {
            actionLine = 0;
            selected = board[actionLine][selection];
            board[actionLine][selection] = 0;
            if(selection == 0)
            {
                selection++;
                selectWasZero = true;
            }
            for(i = selection - 1; i >= 0 && selected != 0 ; i--)
            {


                if(selectWasZero)
                {
                    selectWasZero = false;
                    selection++;
                }
                else
                {
                    board[actionLine][i] += 1;
                    selected--;
                }

                if(i == 0 && selected != 0)
                {
                    p2Score += 1;
                    scored = true;
                    selected--;
                    if(selected > 0)
                    {
                        actionLine = 1;
                        for(c = 0; c <= 6 && selected != 0 ; c++)
                        {
                            board[actionLine][c] += 1;
                            selected--;
                            if(selected == 0)
                            {
                                c = 0;
                            }
                        }
                    }
                    else
                    {
                        canRepeat = true;
                        i = -1;
                    }
                }

            }
            if(scored != true)
            {
                if(selection > 0)
                    checkOther(0, i + 1);
                else
                    checkOther(0, i);
            }
        }

        return canRepeat;
    }

    /**
     * Determines if the player gets the opposite sides gems.
     * @param player Takes in the player that is getting checked.
     * @param end Takes in what the ending point from the selection is.
     */
    private void checkOther(int player, int end)
    {
        if(player == 1)
        {
            if(board[1][end] == 1)
            {
                p1Score += board[1][end] + board[0][end];
                board[0][end] = 0;
                board[1][end] = 0;
            }
        }
        else
        {
            if(board[0][end] == 1)
            {
                p2Score += board[0][end] + board[1][end];
                board[1][end] = 0;
                board[0][end] = 0;
            }
        }
    }

    /**
     * Checks if the game has finished
     * @param player Takes in which player it is checking.
     * @return Returns true if that player has won.
     */
    public boolean checkFinish(int player)
    {
        int lineCheck;
        int count = 0;
        if(player == 1)
        {
            lineCheck = 1;
        }
        else
        {
            lineCheck = 0;
        }

        for(int i = 0; i < 6; i++)
        {
            count += board[lineCheck][i];
        }
        if(count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Outputs the game board in its current state.
     * @return A string of the current gameboard layout.
     */
    public String toString()
    {
        String spacing = "%4s";
        String message = "           1    2    3    4    5    6 \n" +
                "+----+ +----+----+----+----+----+----+\n" +
                "|" + String.format(spacing, p2Score) + "| ";
        int y;
        int x;

        for(y = 0; y < 2; y++)
        {

            for(x = 0; x < 6; x++)
            {
                message += "|" + String.format( spacing ,board[y][x]);
            }
            message += "|";
            if(y == 0)
            {
                message += "\n+----+ +----+----+----+----+----+----+ +----+\n" +
                        "       ";
            }
            else
            {
                message += " |" + String.format(spacing, p1Score) + "|\n" +
                        "       +----+----+----+----+----+----+ +----+";
            }
        }

        return message;
    }
}
