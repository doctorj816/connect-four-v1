import java.util.Scanner;

public class ConnectFourBoardTest {

    public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        ConnectFourBoard b = new ConnectFourBoard();
        
        while (true)
        {
            b.print();
            System.out.println("Turn: Player " + b.getTurn());
            System.out.print("Enter 0-6 to drop, 10 to reset, 11 to quit: ");
            int ans = in.nextInt();
            if (ans >= 0 && ans <= 6)
            {
                if (b.canDrop(ans))
                {
                    b.drop(ans);
                    int winner = b.getWinner();
                    if (winner > 0)
                    {
                        b.print();
                        System.out.println("Player " + winner + " wins!");
                        playAgain(b);
                    }
                    else if (b.isFull())
                    {
                        b.print();
                        System.out.println("It's a tie!");
                        playAgain(b);
                    }
                }
                else
                {
                    System.out.println("Cannot drop in column " + ans + ". Try again.");
                }
            }
            else if (ans == 10)
            {
                b.startOver();
            }
            else
            {
                System.out.println("Thank you for playing. Bye!");
                break;
            }
        }
    }
    
    public static void playAgain(ConnectFourBoard b)
    {
        System.out.print("Play again (Y or N)? ");
        String ans = in.next();
        if (ans.equalsIgnoreCase("Y"))
        {
            b.startOver();
        }
        else
        {
            System.exit(0);
        }
    }

}
