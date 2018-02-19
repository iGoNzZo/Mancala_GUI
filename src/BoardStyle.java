/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Interface to draw board in a certain style
 */
public interface BoardStyle 
{
    /**
     * Draws board with style
     * @param g2 Graphics context
     * @param x x coordinate
     * @param y y coordinate 
     * @param size size of pit
     * @param stones number of stones in pit
     */
    void drawBoard(Graphics2D g2, int x, int y, int size, int stones);

    /**
     * Draws pits with style
     * @param g2 Graphics context
     * @param x x coordinate
     * @param y y coordinate 
     * @param size size of pit
     * @param stones number of stones in pit
     */
    void drawPit(Graphics2D g2, int x, int y, int size, int stones);

    /**
     * Returns random int
     * @return a random int
     */
    int randomInt();

    /**
     * Returns random color
     * @return a random color
     */
    Color randomColor();
}