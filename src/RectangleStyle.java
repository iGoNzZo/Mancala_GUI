/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Represents a rectangle style for the board
 */
public class RectangleStyle implements BoardStyle
{
    /**
     * Draws board with style
     * @param g2 Graphics context
     * @param x x coordinate
     * @param y y coordinate 
     * @param size size of pit
     * @param stones number of stones in pit
     */
    public void drawBoard(Graphics2D g2,int x, int y, int size, int stones) {

        Rectangle2D.Double outter = new Rectangle2D.Double(x, y, size/2.05, size);
        Rectangle2D.Double inner = new Rectangle2D.Double(x+ 3, y+3, (size/2.05)-5, size-5);
        g2.setColor(Color.BLACK);
        g2.fill(outter);
        g2.setColor(Color.WHITE);
        g2.fill(inner);

        for(int i = 0; i < stones; i++)	{
            int xer = x + randomInt() + 25;
            int yer = y + randomInt() + 75;
            Ellipse2D.Double sOutter = new Ellipse2D.Double(xer, yer, 10, 10);
            Ellipse2D.Double sInner = new Ellipse2D.Double(xer, yer, 8, 8);
            g2.setColor(Color.BLACK);
            g2.fill(sOutter);

            g2.setColor(randomColor());
            g2.fill(sInner);
        }

        FontMetrics fm = g2.getFontMetrics();
        int ascent = fm.getAscent();
        String num = String.valueOf(stones);

        g2.setColor(Color.BLUE);
        g2.drawString(num, x, y);
    }

    /**
     * Draws pits with style
     * @param g2 Graphics context
     * @param x x coordinate
     * @param y y coordinate 
     * @param size size of pit
     * @param stones number of stones in pit
     */
    public void drawPit(Graphics2D g2, int x, int y, int size, int stones) {
        Rectangle2D.Double outter = new Rectangle2D.Double(x, y, size, size);
        Rectangle2D.Double inner = new Rectangle2D.Double(x+ 3, y+3, size - 5, size - 5);

        g2.setColor(Color.BLACK);;
        g2.fill(outter);
        g2.setColor(Color.WHITE);
        g2.fill(inner);

        for(int i = 0; i < stones; i++)	{
            int xer = x + randomInt() + 13;
            int yer = y + randomInt() + 13;
            Ellipse2D.Double sOutter = new Ellipse2D.Double(xer, yer, 10, 10);
            Ellipse2D.Double sInner = new Ellipse2D.Double(xer, yer, 8, 8);

            g2.setColor(Color.BLACK);
            g2.fill(sOutter);
            g2.setColor(randomColor());
            g2.fill(sInner);
        }

        FontMetrics fm = g2.getFontMetrics();
        int ascent = fm.getAscent();
        String num = String.valueOf(stones);

        g2.setColor(Color.BLUE);
        g2.drawString(num, x, y);
    }

    /**
     * Returns random int
     * @return a random int
     */
    public int randomInt() {
        Random rand = new Random();
        int random = rand.nextInt((40 - 0) + 1) + 0;
        return random;
    }

    /**
     * Returns random color
     * @return a random color
     */
    public Color randomColor()	{
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        Color randomColor = new Color(r, g, b);
        return randomColor;
    }
}