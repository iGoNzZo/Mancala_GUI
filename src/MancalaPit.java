/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

/*
 * A class holding information about the Mancala pits
 */

public class MancalaPit extends Pit
{
    private int initialStones;

    /**
     * Defines a MancalaPit class defines a Mancala Pit Object type Pit
     *
     *	for MoncalaPit class 
     * Constructor for MancalaPit which is  Pit
     * @param stones the number of stones that belong inside the Mancala Pit 
     * @param spot the Index of the MoncalaPit in the Pits Array
     * @param player the player in which the Mancala Pit belongs too 
     */
    public MancalaPit( int stones, int spot, boolean player) {
        super(stones, spot, player);

        initialStones = 0;
    }

}
