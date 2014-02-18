import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: Inerg
 * Date: 17/02/14
 * Time: 4:38 PM
 * Assumes player input will only be integers
 */
public class main {
    public static void main(String[] args)
    {
        Players play = new Players();
        int numGen;
        int firstPlayer;
        int playerOne = 1;
        int playerTwo = 2;

        Random playerSelect = new Random();

        System.out.println("Generating number for random player selection");
        numGen = playerSelect.nextInt(100000);
        if(numGen > 50000)
        {
            firstPlayer = 1;
        }
        else
        {
            firstPlayer = 2;
        }
        System.out.println("Number is " + numGen + "\n" +
                "First player is: Player " + firstPlayer);
        boolean gameFinished = false;

        while(gameFinished != true)
        {
            if(firstPlayer == 1)
            {
                gameFinished = play.playerAction(playerOne);
                gameFinished = play.playerAction(playerTwo);
            }
            else
            {
                gameFinished = play.playerAction(playerTwo);
                gameFinished = play.playerAction(playerOne);
            }
        }
    }
}
