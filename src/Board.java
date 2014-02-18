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
    public boolean playerAction(int player, int selection)
    {
        int i;
        int c;
        int selected;
        int actionLine;
        boolean canRepeat = false;
        if(player == 1)
        {
            actionLine = 1;
            selected = board[actionLine][selection];
            board[actionLine][selection] = 0;
            for(i = selected; i < 6 ; i++)
            {
                board[actionLine][i] += 1;
                selected--;
                if(i == 5 && selected != 0)
                {
                    p1Score += 1;
                    selected--;
                    if(selected != 0)
                    {
                        actionLine = 0;
                        for(c = 6; c >= 0 ; c--)
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
        }
        else
        {
            actionLine = 0;
            selected = board[actionLine][selection];
            board[actionLine][selection] = 0;
            for(i = selected; i >= 0 ; i--)
            {
                board[actionLine][i] += 1;
                selected--;
                if(i == 0 && selected != 0)
                {
                    p2Score += 1;
                    selected--;
                    if(selected != 0)
                    {
                        actionLine = 0;
                        for(c = 6; c > 1 ; c++)
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
        }

        return canRepeat;
    }

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
