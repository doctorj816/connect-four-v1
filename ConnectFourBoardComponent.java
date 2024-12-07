import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class ConnectFourBoardComponent extends JComponent
{
    public static final Color COLOR1 = Color.RED;
    public static final Color COLOR2 = Color.BLUE;
    public static final Color COLOR_BG = Color.LIGHT_GRAY;
    
    private ConnectFourBoard b; //reference to a board object
    
    public ConnectFourBoardComponent(ConnectFourBoard newBoard)
    {
        b = newBoard;
    }

    public static Color getTurnColor(int c)
    {
        if (c == 1)
            return COLOR1;
        else if (c == 2)
            return COLOR2;
        return COLOR_BG;
    }
    
    public void paintComponent(Graphics g)
    {
        int width = b.getWidth();
        int height = b.getHeight();
        final int GAP = 5;
        final int TOP_GAP = 20;
        final int FONT_FUDGE = 5;
        final int SIZE = Math.min((getWidth() - GAP) / width,
                                  (getHeight() - GAP - TOP_GAP) / height) - GAP;
        final int SPACE = SIZE + GAP;
        
        for (int x = 0; x < width; ++x)
        {
            g.drawString(""+(x+1), GAP+x*SPACE + SPACE/2 - FONT_FUDGE, TOP_GAP);
        }
        
        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                g.setColor(getTurnColor(b.get(x, y)));
                g.fillOval(GAP+x*SPACE,  TOP_GAP+GAP+y*SPACE, SIZE, SIZE);
            }
        }
    }
}
