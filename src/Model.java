/**
 * CS 151 Group Project: Mancala Game 
 * @author stevengonzalez
 * @author phyllislau
 * @author manindersingh
 * @since 12/05/15
 * @version 8.0
 */

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class (MODEL) holding information about the pits
 */
public class Model{

    final int NUM_PITS_ONLY = 12;
    final int TOTAL_PITS = 14;
    final int PLAYER_A_M = 6;
    final int PLAYER_B_M = 13;
    final int UNDO_LIMIT = 3;

    private boolean freeTurn = false;
    private boolean player = true;
    private int numberOfStones;
    private Pit[] pits;
    private Pit[] copy;
    private boolean stateOfUndo;
    private boolean previousPlayer;
    private boolean playerMovesAfterFT = false;
    private int undoCount;
    private String state;
    private int previousUndo;
    private boolean undoPressed = false;
    private boolean invalidPit = false;

    private ArrayList<ChangeListener> listeners;

    /*
     * Creates a model object
     */
    public Model(int stones)
    {
        setInitialNumOfStones(stones);
        copy = new Pit[TOTAL_PITS];
        pits = new Pit[TOTAL_PITS];
        state = "Player A's turn";

        //initialize pits with chosen number of stones
        for (int i = 0; i < pits.length; i++)
        {
            if (i == PLAYER_A_M)
            {
                pits[i] = new MancalaPit(0, i, true);
            }
            else if (i == PLAYER_B_M)
            {
                pits[i] = new MancalaPit( 0, i, false);
            }
            else if (i < PLAYER_A_M)
            {
                pits[i] = new Pit(numberOfStones, i, true);
            }
            else
            {
                pits[i] = new Pit(numberOfStones, i, false);
            }
        }

        listeners = new ArrayList<>();
        savePrevious();
        print();

    }
    
    /**
     * Sets the number of initial stones
     * @param n number of initial stones
     * @precondition n == 3 || n == 4
     * @postcondition pits will have either 3 or 4 stones
     */
    public void setInitialNumOfStones(int n)
    {
        numberOfStones = n;
    }

    /**
     * Gets copy array holding previous state of pits
     * @return a pit array holding previous state of pits
     */
    public Pit[] getCopy()
    {
        return copy;
    }

    /**
     * Gets the current undo count
     * @return the undo count
     */
    public int getUndoCount()
    {
        return undoCount;
    }

    /**
     * Sets the state of the undo button
     * @param state the state of the undo button to change to
     * @precondition state != null
     * @post condition the state of the undo button is set to state
     */
    public void setStateOfUndo(boolean state)
    {
        stateOfUndo = state;
    }

    /**
     * Sets array passed in as current pits
     * @param arr the array holding the most current information
     * @precondition arr != null 
     * @postcondition pits is set to arr
     */
    public void setPits(Pit[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            pits[i] = new Pit(arr[i]);      
        }
    }

    /**
     * Gets the initial number of stones of the pits
     * @return the number of stones the pits started with 
     */
    public int getInitialNumOfStones()
    {
        return numberOfStones;
    }

    /**
     * Gets the array representing the pits
     * @return the array represnting the pits
     */
    public Pit[] getPits()
    {
        return pits;
    }

    /**
     * Returns a string telling who's player turn it is
     * @return string indicating which player's turn it is
     */
    public String Player()
    {
        if (player)
        {
            return "Player A's turn.";
        }
        else
        {
            return "Player B's turn.";
        }
    }

    /**
     * Returns a boolean value indicating which player's turn it is
     * @return the current player
     */
    public boolean whichPlayer()
    {
        return player;
    }

    /**
     * Saves current array to copy array
     * @postcondition the copy array now holds information of current pits
     */
    public void savePrevious()
    {
        for (int i = 0; i < pits.length; i++)
        {
            copy[i] = new Pit(pits[i]);
        }
    }

    /**
     * Gets previous player
     * @return true if previous player was Player A, false if Player b
     */
    public boolean previousPlayer()
    {
        return previousPlayer;
    }

    /**
     * Sets previous player
     * @param p boolean value indicating which player
     * @precondition p == true || p == false
     * @postcondition previous player is set to p
     */
    public void setPreviousPlayer(boolean p)
    {
        previousPlayer = p;
    }

    /**
     * Sets previous undo count
     * @param u previous undo count
     * @precondition u within limit
     * @postcondition undo count set to u
     */
    public void setPreviousUndo(int u)
    {
        previousUndo = u;
    }

    /**
     * Get previous undo count
     * @return previous undo count
     */
    public int getPreviousUndo()
    {
        return previousUndo;
    }

    /**
     * Sets if undo is pressed
     * @param u boolean value indicating if undo was pressed
     * @precondition u == true || u == false
     * @postcondition undoPressed set to u
     */
    public void setUndoPressed(boolean u)
    {
        undoPressed = u;
    }

    /**
     * Gets the pit corresponding to index
     * @param index the spot the pit is located in in the array
     * @return a pit object 
     * @precondition index >= 0
     */
    public Pit get(int index)
    {
        return pits[index];
    }

    /**
     * Gets the state of the game
     * @return state the string containing information about the state of the game
     */
    public String getState()
    {
        return state;
    }

    /**
     * Moves stones accordingly to the Mancala game rules
     * @param p the index representing the pit that is clicked on
     * @precondition p >= 0 && p <= 14
     * @postcondition the stones are moved accordingly to rules
     */
    public void moveStones(int p)
    {
        //save previous pits
        savePrevious();

        //save previous player
        setPreviousPlayer(player);
        
        //enables undo button to be clicked
        setStateOfUndo(true);

        int currentStones = pits[p].getStonesFromPit();


        //invalid pits
        //result: does not change player and tells player to click some other pit
        if (pits[p].getPlayer() != player) //player clicks on opponents pits
        {
            invalidPit = true;
            state = "Invalid move: opponent's pits. Please try again.";
            if (undoPressed)
            {
                undoCount = previousUndo;
                undoPressed = false;
            }
            else if (previousPlayer != player)
            {
                if (playerMovesAfterFT)
                {
                    undoCount = previousUndo;
                }
                else
                {
                    undoCount = 0;
                }
            }

            notifyListeners();
            print();
            return;
        }
        if (currentStones == 0)        //player clicks empty pit
        {
            invalidPit = true;
            state = "Invalid move: empty pit. Please try again.";

            if (undoPressed)
            {
                undoCount = previousUndo;
                undoPressed = false;
            }
            else if (previousPlayer != player)
            {
                if (playerMovesAfterFT)
                {
                    undoCount = previousUndo;
                }
                else
                {
                    undoCount = 0;
                }
            }

            notifyListeners();
            print();
            return;
        }
        if (p == PLAYER_A_M || p == PLAYER_B_M)          //player clicks on mancala
        {
            invalidPit = true;
            state = "Invalid move: mancala pits. Please try again.";

            if (undoPressed)
            {
                undoCount = previousUndo;
                undoPressed = false;
            }
            else if (previousPlayer != player)
            {
                if (playerMovesAfterFT == false)
                {
                    undoCount = previousUndo;
                }
                else
                {
                    undoCount = 0;
                }
            }

            notifyListeners();
            print();
            return;
        }
        // if none of the above
        int index = p;
        pits[p].setNumOfStones(0);          //empties clicked pit

        for (int i = currentStones; i > 0; i--)         //counting down number of stones 
        {
            index++;

            //loop back to beginning of array
            if (index > 13)
            {
                index = 0; //set index to 0
            }

            //add one stone to next index
            pits[index].addStones(1);

            //if player goes through opponent's mancala, take stone out
            if ((index == PLAYER_A_M && player != pits[PLAYER_A_M].getPlayer()) || (index == PLAYER_B_M && player != pits[PLAYER_B_M].getPlayer()))
            {
                pits[index].addStones(-1);
                i++;
            }

            if (i == 1) //if last stone lands in your mancala, player can go again 
            {
                if ((player && index == PLAYER_A_M && !over()) || (player == false && index == PLAYER_B_M && !over()))
                {
                    state = "Free turn. Play again!";
                    freeTurn = true;
                    playerMovesAfterFT = false;
                    if (undoPressed)
                    {
                        undoCount = previousUndo;
                        undoPressed = false;
                    }
                    else if (invalidPit)
                    {
                        undoCount = previousUndo;
                        invalidPit = false;
                    }
                    else if (previousPlayer == player)
                    {
                        undoCount = 0;
                        previousUndo = 0;
                    }

                    notifyListeners();
                    print();
                    return;
                }
            }

        }
        //if last stone goes in empty pit on player's side, add last stone and opposite pit's stones to own mancala
        if (pits[index].getStonesFromPit() == 1) //if a pit has one stone, that means it was previously empty
        {
            int oppindex = NUM_PITS_ONLY - index;
            if (player == true && pits[index].getPlayer() == true && (oppindex != PLAYER_A_M) )
            {
                pits[PLAYER_A_M].addStones(pits[oppindex].getStonesFromPit()+1);
                pits[index].setNumOfStones(0);
                pits[oppindex].setNumOfStones(0);
            }
            else if (player == false && pits[index].getPlayer() == false && (oppindex != PLAYER_B_M))
            {
                pits[PLAYER_B_M].addStones(pits[oppindex].getStonesFromPit()+1);
                pits[index].setNumOfStones(0);
                pits[oppindex].setNumOfStones(0);
            }
        }

        player = !player; //switches plyaer
        if (undoPressed)
        {
            undoCount = previousUndo;
            undoPressed = false;
        }
        else if (invalidPit)
        {
            undoCount = previousUndo;
            invalidPit = false;
        }
        else if (previousPlayer != player)
        {
            if (playerMovesAfterFT == false)
            {
                undoCount = previousUndo;
                playerMovesAfterFT = true;
            }

            else
            {
                undoCount = 0;
            }
        }
        else if (previousPlayer == player)
        {
            if (playerMovesAfterFT == false)
            {
                undoCount = previousUndo;
                playerMovesAfterFT = true;
            }

            else
            {
                undoCount = 0;
            }

        }

        if (over()) //game over
        {
            notifyListeners();
            return;
        }
        else //if game is not over, determine which player's turn is next
        {
            state = Player();
        }
        notifyListeners();
        print();
        return;
    }

    /**
     * Returns boolean value indicating whether game is over or not
     * @return true if game is over, false if not
     */
    public boolean over() 
    {
        int playerAsum = 0;
        int playerBsum = 0;

        //checks if one player A's side has all empty pits 
        for (int i = 0; i < PLAYER_A_M; i++)
        {
            playerAsum += pits[i].getStonesFromPit();
        }

        if (playerAsum == 0)
        {
            for (int i = 7; i < PLAYER_B_M; i++)
            {
                playerBsum += pits[i].getStonesFromPit();
                pits[i].setNumOfStones(0); //clears pit after adding
                notifyListeners();
            }
            pits[PLAYER_B_M].addStones(playerBsum);
            state = determineWinner();

            print();
            return true;
        }

        //checks if player B's side has empty pits
        playerAsum = 0; // reset A after first loop
        for (int i = 7; i < PLAYER_B_M; i++)
        {
            playerBsum += pits[i].getStonesFromPit();
        }
        if (playerBsum == 0)
        {
            playerAsum = 0;
            for (int i = 0; i < PLAYER_A_M; i++)
            {
                playerAsum += pits[i].getStonesFromPit();
                pits[i].setNumOfStones(0);
                notifyListeners();
            }
            pits[PLAYER_A_M].addStones(playerAsum);
            state = determineWinner();

            print();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Determines the winner after game has ended
     * @return string indicating the winner
     */
    public String determineWinner() 
    {
        
        if (pits[PLAYER_A_M].getStonesFromPit() > pits[PLAYER_B_M].getStonesFromPit())
        {
            return "Player A wins!";
        }
        else if (pits[PLAYER_B_M].getStonesFromPit() > pits[PLAYER_A_M].getStonesFromPit())
        {
            return "Player B wins!";
        }
        else
        {
            return "Tie!";
        }

    }

    /**
     * Print the board in console
     */
    public void print()
    {
        System.out.print("B:   ");
        for (int i = 12; i > 6; i--)
        {
            System.out.print( Integer.toString(pits[i].getStonesFromPit())
                + " ");
        }
        System.out.println();
        System.out.println("   " + pits[13].getStonesFromPit()
            + " a b c d e f " + pits[6].getStonesFromPit());
        System.out.print("A:   ");
        for (int i = 0; i < 6; i++)
        {
            System.out.print(pits[i].getStonesFromPit() + " ");
        }
        System.out.println();
        System.out.println();
    }

    
    /**
     * Undos previous move by player
     * @postcondition if can undo by Mancala rules, returns everything to previous state; otherwise, do nothing
     */
    public void undo()
    {
        undoCount++;

        if (stateOfUndo)
        {
            //set copy array as pits and notify changelisteners to repaint
            if (undoCount <= UNDO_LIMIT)
            {
                setPits(copy);

                if (freeTurn && playerMovesAfterFT)
                {
                    player = !player;
                    freeTurn = false;
                    state = Player().substring(0, Player().length()-1) + " again.";
                }
                else if (freeTurn) //move stones already switches player back
                {
                    freeTurn = false;
                    state = Player();
                }
                else
                {   
                    player = !player;
                    state = Player();
                }
                setPreviousUndo(undoCount);
                undoPressed = true;
            }
            else
            {
                //do nothing if undo limit has been reached
                state = "Undo limit has been reached. " + Player();
            }
        }
        else
        {
            state = "Must select a pit before undo!";
            undoCount--;
        }
        if (undoCount > UNDO_LIMIT)
        {
            //reset count if limit is reached
            undoCount = 0;
        }
        setStateOfUndo(false);
        notifyListeners();

        print();
    }

    /**
     * Adds a ChangeListener to the model
     * @param c
     * @precondition c != null
     * @postcondition ChangeListener is added to the collection of ChangeListeners
     */
    public void addChangeListener(ChangeListener c)
    {
        listeners.add(c);
    }

    /**
     * Notifies listeners of any change in model
     * @postcondition listeners are notified of changes
     */
    public  void notifyListeners()
    {
        for (ChangeListener l: listeners)
        {
            l.stateChanged(new ChangeEvent(this));
        }
    }

}