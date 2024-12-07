/**
 * 
 * @author Justin Jang
 * @version v1.0 11/24/2019 
 */

public class ConnectFourBoard {
    public static final int DEFAULT_WIDTH = 7;
    public static final int DEFAULT_HEIGHT = 6;
    public static final int DEFAULT_FOUR = 4;
    
    private int[][] grid;
    private int width;
    private int height;
    private int turn;    // Player's turn: 1 or 2
    private int FOUR;
    
    public ConnectFourBoard()
    {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        grid = new int[width][height];
        turn = 1;
        FOUR = DEFAULT_FOUR;
    }
    
    public ConnectFourBoard(int newWidth, int newHeight, int numConnect)
    {
        width = newWidth;
        height = newHeight;
        grid = new int[width][height];
        turn = 1;
        FOUR = numConnect;
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getTurn() { return turn; }
    public int get(int w, int h) { return grid[w][h]; }
    
    /**
     * Changes which player's turn (between 1 and 2)
     */
    public void flipTurn()
    {
        turn = 3 - turn;  // Flips 1 to 2 OR 2 to 1
    }
    /*
     * What is the other color (turn)
     */
    private static int otherColor(int t) { return 3 - t; }

    /**
     * Zeros out the entire game board
     */
    public void clearBoard()
    {
        for (int w = 0; w < width; ++w)
        {
            for (int h = 0; h < height; ++h)
            {
                grid[w][h] = 0;
            }
        }
    }
    /**
     * Clears the board and sets turn to Player 1
     */
    public void startOver()
    {
        clearBoard();
        turn = 1;
    }
    
    /**
     * @param column
     * @return true if can drop a piece in the column (column is not full)
     */
    public boolean canDrop(int column)
    {
        //Assuming proper "drop physics", only have to check the top
        return grid[column][0] == 0;
    }
    /**
     * Drop a piece (of current turn) in the column
     * Assumes canDrop(column) is true
     * @param column
     */
    public void drop(int column)
    {
        int i = height - 1;  // Start at bottom
        while (i >= 0 && grid[column][i] > 0)
        {
            i--;
        }
        if (i >= 0)
        {
            grid[column][i] = turn;
            flipTurn();
        }
    }
    
    /**
     * Checks if the board is completely full
     * @return true if full, false otherwise
     */
    public boolean isFull()
    {
        for (int w = 0; w < width; ++w)
        {
            for (int h = 0; h < height; ++h)
            {
                if (grid[w][h] == 0) return false;
            }
        }
        return true;
    }
    
    /*
     * Checks to see if FOUR-in-a-row starting from (w,h) 
     * sx and sy indicate the step in the width and height directions to check
     * sx is +1 or -1
     * sy is +1 or -1
     * 
     * Returns 1 or 2 if winner here, 0 if no winner here
     */
    private int checkSpace(int w, int h, int sx, int sy)
    {
        int targetColor = grid[w][h];
        if (targetColor == 0) return 0;
        for (int i = 1; i < FOUR; ++i)
        {
            int c = grid[w + sx*i][h + sy*i];
            if (c != targetColor) return 0;
        }
        return targetColor;
    }
    
    /**
     * 
     * @return the winner (player 1 or 2, 0 if no winner)
     */
    public int getWinner()
    {
        for (int w = 0; w < width; ++w)
        {
            for (int h = 0; h < height; ++h)
            {
                int result;
                // Check patterns like these
                // x 0 0 0   
                // - 0 - -
                // - - 0 -
                // - - - 0
                if (w < width + 1 - FOUR)
                {
                    result = checkSpace(w, h, 1, 0);  //right
                    if (result > 0) return result;
                    
                    if (h < height + 1 - FOUR)
                    {
                        result = checkSpace(w, h, 1, 1);  //right-down
                        if (result > 0) return result;
                    }
                }
                
                // Check patterns like these
                // - - - x   
                // - - 0 0
                // - 0 - 0
                // 0 - - 0
                if (h < height + 1 - FOUR)
                {
                    result = checkSpace(w, h, 0, 1);  //down
                    if (result > 0) return result;
                    
                    if (w >= FOUR - 1)
                    {
                        result = checkSpace(w, h, -1, 1);  //left-down
                        if (result > 0) return result;
                    }
                }
                
            }
        }
        return 0;
    }
    
    /**
     * Print ASCII version of board
     */
    public void print()
    {
        for (int h = 0; h < height; ++h)
        {
            for (int w = 0; w < width; ++w)
            {
                System.out.print(grid[w][h] + " ");
                
            }
            System.out.println();
        }
    }
}
