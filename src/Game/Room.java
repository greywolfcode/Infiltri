package Game;

import java.util.Arrays;

/**
 * Class for storing a specific room
 */
public class Room 
{
    private boolean cleared;
    private int[] coords;
    
    public Room(int[] roomCoords)
    {
        coords = roomCoords;
        cleared = false; //room cannot start as cleared
    }
    public void setCleared(boolean isCleared)
    {
        cleared = isCleared;
    }
    public boolean getCleared()
    {
        return cleared;
    }
    public String toString()
    {
        String loc ="Room at: " + Arrays.toString(coords);
        if (cleared)
        {
            return loc + " and is cleared";
        }
        else
        {
            return loc + " and is not cleared";
        }
    }
}