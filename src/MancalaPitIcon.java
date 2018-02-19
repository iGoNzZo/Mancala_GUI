/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/*
 * MancalaPit class defines MoncalaPit Icon object
 */
public class MancalaPitIcon implements Icon{

    //size and style of the mancala pit
    private int size;
    private BoardStyle style;
    public int stonesInPit;

    /** 
     * Constructor for Mancala pit object
     * @param size of the pit
     * @param style of the pit
     */
    public MancalaPitIcon(BoardStyle b, int sSize,int stones )
    {
        style = b;
        size = sSize;
        stonesInPit = stones;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int getIconHeight() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconWidth() {
        return size/2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        style.drawBoard(g2, x, y, size, stonesInPit);
    }
}
