import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class ConnectFourFrame extends JFrame
{
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;

    private static final int BOARD_WIDTH = 350;
    private static final int BOARD_HEIGHT = 320;

    private ConnectFourBoard b;
    
    private JLabel columnLabel;
    private JTextField columnField;
    private JButton dropButton, resetButton, exitButton;
    private ConnectFourBoardComponent board;
    private double balance;

    public ConnectFourFrame()
    {  
        b = new ConnectFourBoard();
        board = new ConnectFourBoardComponent(b);
        board.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        createTextField();
        createButtons();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createTextField()
    {
        columnLabel = new JLabel("Column: ");

        final int FIELD_WIDTH = 10;
        columnField = new JTextField(FIELD_WIDTH);
        columnField.setText("1" + "-" + b.getWidth());
    }

    class DropButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int ans = -1;
            try
            {
                ans = Integer.parseInt(columnField.getText()) - 1;
            }
            catch (NumberFormatException e)
            {
                ans = -1;
            }
            if (ans >= 0 && ans < b.getWidth())
            {
                if (b.canDrop(ans))
                {
                    b.drop(ans);
                    board.repaint();
                    dropButtonColorize();
                    int winner = b.getWinner();
                    if (winner > 0)
                    {
                        JOptionPane.showMessageDialog(ConnectFourFrame.this, "Player " + winner + " wins!", "Game Over", JOptionPane.OK_OPTION);
                        //TODO: Ask play again?
                    }
                    else if (b.isFull())
                    {
                        JOptionPane.showMessageDialog(ConnectFourFrame.this, "It's a tie!", "Game Over", JOptionPane.OK_OPTION);
                        //TODO: Ask play again?
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(ConnectFourFrame.this, "Cannot drop in column " + (ans+1) + ". Try again.", "Message", JOptionPane.OK_OPTION);
                }
            }
            columnField.requestFocus();
            columnField.selectAll();
        }            
    }

    /*
     * Sets the dropButton color to the current turn color
     */
    private void dropButtonColorize()
    {
        dropButton.setForeground(board.getTurnColor(b.getTurn()));
    }
    
    private void createButtons()
    {
        dropButton = new JButton("Drop");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");
        
        dropButtonColorize();

        //ActionListener listener = new DropButtonListener();
        dropButton.addActionListener(new DropButtonListener());
        resetButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    b.startOver();
                    board.repaint();
                    dropButtonColorize();
                    columnField.requestFocus();
                    columnField.selectAll();
                }
            });
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
    }

    private void createPanel()
    {
        JPanel panel = new JPanel();
        panel.add(columnLabel);
        panel.add(columnField);
        panel.add(dropButton);
        panel.add(resetButton);
        panel.add(exitButton);
        panel.add(board);
        add(panel);
    }

}
