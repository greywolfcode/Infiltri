package Game;

import java.util.Arrays;

import Graphics.Surface;

/**
 * Class for storing a specific room
 */
public class Room 
{
    private boolean cleared;
    private int level;
    private int[] coords;
    private RoomType type;
    
    private Surface background;
    
    public Room(int[] roomCoords, int lvl)
    {
        coords = roomCoords;
        cleared = false; //room cannot start as cleared
        level = lvl;
        
        //get background sprite
        if (level == 1)
        {
            background = SpriteHandeler.getBackground("village");
        }
        else if (level == 2)
        {
            background = SpriteHandeler.getBackground("castle");
        }
        else if (level == 3)
        {
            background = SpriteHandeler.getBackground("throne_room");
        }
    }
    public void gen()
    {
        type = RoomType.randType();
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