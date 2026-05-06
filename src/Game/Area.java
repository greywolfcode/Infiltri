package Game;

import java.util.ArrayList;

/**
 * Class for storing details of a specific area
 */
public class Area 
{
    private int level;
    private ArrayList<Room> rooms;
    
    public Area(int areaLevel)
    {
        level = areaLevel;
        rooms = new ArrayList<>();
    }
    public void generateArea()
    {
        rooms.add(new Room(new int[]{0, 0}));
    }
    public String toString()
    {
        String out = "Area of difficulty: " + level +"\nRooms: \n";
        for(Room room:rooms)
        {
            out += "  " + room.toString() + "\n";
        }
        return out;
    }
}