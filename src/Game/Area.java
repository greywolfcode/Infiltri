package Game;

import java.util.ArrayList;

import GraphGen.Graph;

/**
 * Class for storing details of a specific area
 */
public class Area 
{
    private int level;
    private ArrayList<Room> rooms;
    private Graph graph;
    
    public Area(int areaLevel)
    {
        level = areaLevel;
        rooms = new ArrayList<>();
    }
    public void generateArea()
    {
        rooms.add(new Room(new int[]{0, 0}));
        graph = Graph.gen(20, 0, 100, 0, 100);
    }
    public void render()
    {
        
    }
    public String toString()
    {
        String out = "Area of difficulty: " + level +"\nRooms: \n";
        for(Room room:rooms)
        {
            out += "  " + room.toString() + "\n";
        }
        out += "points\n";
        out += graph.toString();
        return out;
    }
}