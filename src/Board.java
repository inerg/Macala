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
