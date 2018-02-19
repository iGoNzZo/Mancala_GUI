/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MancalaTest {
    static BoardStyle b;
    static Model m;
    public static void main(String[] args) {

		
		
        final JFrame choiceOfStyle = new JFrame();
        JPanel stylePanel = new JPanel();
        JButton style1 = new JButton("Circle Style");
        JButton style2 = new JButton("Rectangle Style");
        JLabel pickStyle = new JLabel("Pick a board style!");
        pickStyle.setHorizontalAlignment(JLabel.CENTER);
        choiceOfStyle.add(pickStyle, BorderLayout.NORTH);
        stylePanel.add(style1);
        stylePanel.add(style2);
        choiceOfStyle.add(stylePanel);
        choiceOfStyle.pack();
        choiceOfStyle.setLocationRelativeTo(null);
        choiceOfStyle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choiceOfStyle.setVisible(true);

        final JFrame numOfStones = new JFrame();
        JPanel stonePanel = new JPanel();
        JButton threeStones = new JButton("3");
        JButton fourStones = new JButton("4");
        JLabel pickStones = new JLabel("Pick a number of stones in each pit!");
        pickStones.setHorizontalAlignment(JLabel.CENTER);
        numOfStones.add(pickStones, BorderLayout.NORTH);
        stonePanel.add(threeStones, BorderLayout.CENTER);
        stonePanel.add(fourStones, BorderLayout.SOUTH);
        numOfStones.add(stonePanel);
        numOfStones.pack();
        numOfStones.setLocationRelativeTo(null);
        numOfStones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        style1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    b = new CircleStyle();
                    choiceOfStyle.dispose();
                    numOfStones.setVisible(true);
                }
            });

        style2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    b = new RectangleStyle();
                    choiceOfStyle.dispose();
                    numOfStones.setVisible(true);
                }
            });

        threeStones.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numOfStones.dispose();
                    m = new Model(3);
                    MancalaBoard mb = new MancalaBoard(b, m);
                }
            });

        fourStones.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    m = new Model(4);
                    numOfStones.dispose();
                    MancalaBoard mb = new MancalaBoard(b, m);
                }
            });

		
    }
}
