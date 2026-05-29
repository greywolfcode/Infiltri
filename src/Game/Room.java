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
    private Enemy enemy;
    
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
    public Room(boolean pCleared, int lvl, int x, int y, RoomType pType, Enemy pEnemy)
    {
        cleared = pCleared;
        level = lvl;
        coords = new int[]{x, y};
        type = pType;
        enemy = pEnemy;
    }
    public void gen()
    {
        type = RoomType.randType();
        enemy = EnemyGenerator.getEnemy(level);
        enemy.init();
    }
    public int getLevel()
    {
        return level;   
    }
    public int[] getCoords()
    {
        return coords;
    }
    public RoomType getType()
    {
        return type;
    }
    public void setCleared(boolean isCleared)
    {
        cleared = isCleared;
    }
    public boolean getCleared()
    {
        return cleared;
    }
    public Surface getBackground()
    {
        return background;
    }
    public Enemy getEnemy()
    {
        return enemy;
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