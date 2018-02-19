/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Defines a mancala board frame object
 */
public class MancalaBoard extends JFrame implements ChangeListener {

    //Number of pits for board game
    private int ALL_PITS = 14;
    private BoardStyle beez;
    //number of pits per player
    private int NUM_PITS = 6;
    private Model m;
    public JPanel topPanel;		
    public JPanel undoPanel;
    public JPanel pitsPanel;

    /** 
     * Frame constructor for mancala board frame
     * @param b the boardstyle to implement for the mancala board game 
     * @param model the data object for the mancala board game 
     */
    public MancalaBoard(BoardStyle b, Model model)
    {	
        //STYLE
        this.m = model;
        this.beez = b; 
        this.m.addChangeListener(this);

        //frame for game board

        setTitle("Californians - Steven Gonzalez, Phyllis Lau, and Maninder Singh - CS151 Group Project");
        setSize(400,500);
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        makeFrame();
    }

    /** 
     * Adds to the main frame of the mancala board game 
     * @postcondition frame is made with boardstyle b
     */
    public void makeFrame()
    {
        //add labels for the pits to the frame for players A and B
        //make the game board and add it to the frame
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel labelPanel = new JPanel();
      
        JLabel manB = new JLabel("Mancala B                                                        ");
        JLabel playerBLabel = makePlayerLabel("<----- Player B");
        JLabel manA = new JLabel("                                                        Mancala A");
        labelPanel.add(manB, BorderLayout.WEST);
        labelPanel.add(playerBLabel, BorderLayout.CENTER);
        labelPanel.add(manA, BorderLayout.EAST);
        topPanel.add(labelPanel, BorderLayout.NORTH);
        topPanel.add(makeGameBoard(), BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        undoPanel = new JPanel();
        undoPanel.setLayout(new BorderLayout());	
        undoPanel.add(makePlayerLabel("Player A ----->"), BorderLayout.CENTER);
        undoPanel.add(makeUndoBar(), BorderLayout.SOUTH);
        add(undoPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /** 
     * Makes the panel that is the mancala board game 
     * @return gBoard JPanel a mancala game board with pits and mancala pits
     * @postcondition makes gameboard
     */
    public JPanel makeGameBoard()
    {
        //game board panel
        JPanel gBoard = new JPanel();
        gBoard.setPreferredSize(new Dimension(700, 225));
        gBoard.setLayout(new BorderLayout());
        gBoard.setBackground(Color.lightGray);

        //add player pits + mancala pits to the game board JPanel
        gBoard.add(makeMancalaPit(false), BorderLayout.EAST);
        gBoard.add(makePits(), BorderLayout.CENTER);
        gBoard.add(makeMancalaPit(true), BorderLayout.WEST);
        return gBoard;
    }

    /**
     * Make labels panel
     * @param player the player who the labels will belong too
     * @return labels JPanel the label for the given players pits 
     * @precondition player.length > 0
     * @postcondition makes label of player
     */
    public JPanel makeLabels(String player)
    {
        JPanel labels = new JPanel();
        labels.setLayout(new FlowLayout());

        labels.add(new JLabel("                                    "));
        for(int i = NUM_PITS; i > 0; i--)
        {
            String spacer = "                           ";
            String topPits = " " + player + String.valueOf(i) + spacer;
            labels.add(new JLabel(topPits));
        }
        labels.add(new JLabel("          "));
        return labels;
    }

    /* 
     * Make the player pits for the gameBoard
     * @return pitsPanel JPanel a JPanel containing pits for both players
     */
    public JPanel makePits()
    {
        int stones;
        int in;
        boolean p;

        pitsPanel = new JPanel();
        pitsPanel.setLayout(new GridLayout(2, 6));

        for(int j = 0; j < 12; j ++)
        {
            String id;
            final JButton pit;
            final PitIcon pitter;
            if (j < 6)
            {
                id = "B" + "" + String.valueOf(String.valueOf((11-j)%6+1));
                stones = m.get(12-j).getStonesFromPit();
                in = 12-j;
                p = false;

                pitter = new PitIcon(stones, in, p, beez, 75);
                pit = new JButton(pitter);

                pit.setText(id);
                pit.setHorizontalTextPosition(JButton.CENTER);
                pit.setVerticalTextPosition(JButton.NORTH);
            }
            else
            {
                id =  "A" + "" + String.valueOf((j-6) +1);
                stones = m.get(j-6).getStonesFromPit();
                in = j-6;
                p = true;

                pitter = new PitIcon(stones, in, p, beez, 75);
                pit = new JButton(pitter);

                pit.setText(id);
                pit.setHorizontalTextPosition(JButton.CENTER);
                pit.setVerticalTextPosition(JButton.NORTH);
            }

            pit.addActionListener(new ActionListener()	{
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int indexOfP = pitter.getIndexOfPit();

                        m.moveStones(indexOfP);
                    }
                });
            pit.setRolloverEnabled(false);
            pitsPanel.add(pit);
        }
        return pitsPanel;
    }

    /** 
     * Makes a mancala pit for the given player  
     * @param p the player whom the mancala pit will belong too
     * @return mancalaPit a JPanel with a mancalaPit in it 
     */
    public JLabel makeMancalaPit(Boolean p)
    {
        JLabel mancalaPit;
        if(p == false)	{
            mancalaPit = new JLabel(new MancalaPitIcon(beez, 170, m.get(6).stonesInPit));
        }
        else	{
            mancalaPit = new JLabel(new MancalaPitIcon(beez, 170, m.get(13).stonesInPit));
        }
        return mancalaPit;
    }

    /** 
     * Make player label
     * @param s the string that defines player A or player B
     * @return label a JLabel with the given player name
     */
    public JLabel makePlayerLabel(String s)
    {
        JLabel label = new JLabel(s, SwingConstants.CENTER);
        return label;
    }
    
    public JLabel makeMA()
    {
    	JLabel label = new JLabel("Mancala A", SwingConstants.RIGHT);
    	return label;
    }
    
    public JLabel makeMB()
    {
    	JLabel label = new JLabel("Mancala B", SwingConstants.LEFT);
    	return label;
    }

    /** 
     * Makes a JPanel that houses the undo button as states players turn and 
     * how many undo's are left for the current player 
     * @return undoBar a JPanel with an undo button and information about the current state of the game 
     */
    public JPanel makeUndoBar()	{
        JPanel undoBar = new JPanel();
        undoBar.setLayout(new BorderLayout());

        int undoC = m.getUndoCount();
        String spacer = "          ";

        JButton undo = new JButton("Undo Last Move");
        undo.addActionListener(new ActionListener()	{
                @Override
                public void actionPerformed(ActionEvent e) {
                    m.undo();
                }		});

        String undoLabel =  spacer + "# of undo's left: " + String.valueOf(3 - undoC);

        String stateOfGame =  m.getState() + spacer;

        JPanel undoStuff = new JPanel();
        undoStuff.add(new JLabel(undoLabel));
        undoStuff.add(undo);

        undoBar.add(new JLabel(stateOfGame), BorderLayout.WEST);

        undoBar.add(undoStuff, BorderLayout.EAST);
        return undoBar;
    }

    /**
     * Makes frame displaying the winner
     * @postcondition frame is created to display winner
     */
    public void winnerFrame()
    {

        JFrame winnerFrame  = new JFrame();
        winnerFrame.isAlwaysOnTop();
        winnerFrame.setSize(100,100);
        JLabel displayWinner = new JLabel(m.determineWinner());
        displayWinner.setHorizontalAlignment(JLabel.CENTER);
        winnerFrame.add(displayWinner, BorderLayout.NORTH);
        JButton close = new JButton("CLOSE");
        close.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }

            });

        winnerFrame.add(close, BorderLayout.SOUTH);
        winnerFrame.pack();
        winnerFrame.setLocationRelativeTo(null);
        winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winnerFrame.setVisible(true);

    }

	

    /**
     *  {@inheritDoc}
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        m.getPits();
        topPanel.setVisible(false);
        undoPanel.setVisible(false);
        makeFrame();
        if (m.getState().equals("Player A wins!") || m.getState().equals("Player B wins!") || m.getState().equals("Tie!"))
        {
            winnerFrame();

            for (Component c: pitsPanel.getComponents())
            {
                c.setEnabled(false);
            }

        }

    }
}