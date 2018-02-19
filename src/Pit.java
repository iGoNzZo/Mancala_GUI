/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

/*
 * Pit class defines a pit object 
 */
public class Pit {

	public String pName;
	public int stonesInPit;
	boolean isPlayerPit;
	private int spotInArray;
	
	/** 
	 * Constructor for pit 
	 * @param stones the # of stones that will go inside the pit 
	 * @param spot the index of the array within the model 
	 * @param player the player whom the pit will belong too 
	 */
	public Pit(int stones, int spot, boolean player)	{
		stonesInPit = stones;
		isPlayerPit = player; 
		spotInArray = spot;
	}
	
	/** 
	 * Constructor for pit when a pit is passed to make a newer pit 
	 * @param o the pit to make the new pit from 
	 */
	public Pit(Pit o)
	{
		stonesInPit = o.getStonesFromPit();
		isPlayerPit = o.getPlayer();
		spotInArray = o.getIndexOfPit();
	}
	
	/** 
	 * Get the index of the pit in the pits array 
	 * @return spotInArray the index of the pit inside the pits array 
	 */
	public int getIndexOfPit()	{
		return spotInArray;
	}
	
	/** 
	 * Sets the index of the pit to the given value 
	 * @param n the value representing the index of the pit in the pits array 
	 * @precondition n >= 0
	 * @postcondition index of pit is set to n
	 */
	public void setIndexPit(int n)
	{
		spotInArray = n;
	}
	
	/** 
	 * Stones were picked up from the pit so set the # of stones to 0
	 */
	public void stonesPickedUp()	{
		stonesInPit = 0;
	}
	
	/** 
	 * Get the player that this pit belongs too
	 * @return isPlayerPit the player in which the pit belongs to 
	 */
	public boolean getPlayer()	{
		return isPlayerPit;
	}
	
	/**
	 * Set the number of stones in the pit to the given value
	 * @param int n number of stones to set
	 * @postcondition pit has n number of stones
	 */
	public void setNumOfStones(int n)
	{
		stonesInPit = n;
	}
	
	/**
	 * Add the given value of stones to the pit
	 * @param n number of stones to add
	 * @postcondition n number of stones added 
	 */
	public void addStones(int n)	{
		stonesInPit = stonesInPit + n;
	}
	
	/** 
	 * Get the number of stones currently in the pit 
	 * @return stonesInPit the # of stones in this pit 
	 */
	public int getStonesFromPit()	{
		return stonesInPit;
	}
}
