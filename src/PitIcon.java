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
 * PitIcon class - defines a pitIcon object 
 */
public class PitIcon implements Icon{

    private int size;
    private BoardStyle style;
    public int numStonesInPit;
    public String pName;
    public int indexing;
    public boolean player;

    /** 
     * PitIcon constructor
     * 
     * @param stones the number of stones to draw inside the icon 
     * @param in the index of the pit in the models array
     * @param p the player whom the pit belongs too 
     * @param BoardStyle the boartStyle the pit should follow 
     * @param sSize the size to draw the pit 
     */
    public PitIcon(int stones, int in, boolean p, BoardStyle b, int sSize)	{
        style = b;
        size = sSize;
        numStonesInPit = stones;
        indexing = in;
        player = p;
    }

    /** 
     * Gets the number of stones that are currently drawn in the pit 
     * @return numStonesInPit the # of stones in the pit 
     */
    public int getStonesFromPit()	{
        return numStonesInPit;
    }

    /** 
     * Get the index of the corresponding pit in the pits array 
     * @return indexing the index of the pit in the pits array  
     */
    public int getIndexOfPit()	{
        return indexing;
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
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        style.drawPit(g2, x, y, size, numStonesInPit);
    }
}
