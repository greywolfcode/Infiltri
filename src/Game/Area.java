package Game;

import java.util.Arrays;
import java.util.ArrayList;

import GraphGen.Edge;
import GraphGen.Graph;
import GraphGen.Point;

/**
 * Class for storing details of a specific area
 */
public class Area 
{
    private int level;
    private ArrayList<Room> rooms;
    private Graph graph;
    
    private int startIndex;
    private int endIndex;
    private int currentPointIndex;
    private int selectedPointIndex;
    
    //cache these so don't need to look them up;
    private Point startPoint;
    private Point endPoint;
    private Point currentPoint;
    private Point selectedPoint;
    
    public Area(int areaLevel)
    {
        level = areaLevel;
        rooms = new ArrayList<>();
    }
    public void generateArea()
    {
        graph = Graph.gen(10, 0, 64, 0, 32);
        genStartAndEnd();
        for (Point point:graph.getPoints())
        {
            int[] coords = new int[]{point.getX(), point.getY()};
            Room room = new Room(coords, level);
            room.gen();
            rooms.add(room);
        }
    }
    public void load()
    {
        initArea();
    }
    
    public void selectNext()
    {
        if (selectedPointIndex == currentPoint.getNeighborsArr().length -1)
        {
            selectedPointIndex = 0;
        }
        else
        {
            selectedPointIndex++;
        }
        selectedPoint = currentPoint.getNeighborsArr()[selectedPointIndex];
    }
    public void selectPrev()
    {
        if (selectedPointIndex == 0)
        {
            selectedPointIndex = currentPoint.getNeighborsArr().length - 1;
        }
        else
        {
            selectedPointIndex--;
        }
        selectedPoint = currentPoint.getNeighborsArr()[selectedPointIndex];
    }
    public void select()
    {
        //just setting currentPoint to selectedPoint breaks neighbors
        currentPointIndex = getIndex(selectedPoint);
        currentPoint = graph.getSortedPoints()[currentPointIndex];

        selectedPoint = currentPoint.getNeighborsArr()[0];
        selectedPointIndex = 0;
        
        //switch to new menu
        if (currentPoint.equals(endPoint))
        {
            Data.pushEvent(new String[]{"switch", "WorldMenu"});
        }
        else
        {
            Data.setCurrentRoom(rooms.get(currentPointIndex)); //this will always map each to point to the same room
            Data.pushEvent(new String[]{"switch", "RoomMenu"});
        }
    }
    
    public void render()
    {
        //draw edges first so they are not on top of points
        for (Edge edge:graph.getEdges())
        {
            Data.getWindow().drawLine((int)edge.getV0().getX(), (int)edge.getV0().getY(), (int)edge.getV1().getX(), (int)edge.getV1().getY(), 64, 64, 64);
        }
        // lines attached to current point should be brighter
        for (Point neighbor:currentPoint.getNeighborsArr())
        {
            Data.getWindow().drawLine(currentPoint.getX(), currentPoint.getY(), neighbor.getX(), neighbor.getY(), 128, 128, 128);
        }
        
        Point[] points = graph.getSortedPoints();
        for (Point point:points)
        {
            //draw important points different colours
            if (point.equals(currentPoint))
            {
                Data.getWindow().drawPoint(point.getX(), point.getY(), 0, 0, 128);
            }
            else if (point.equals(selectedPoint))
            {
                Data.getWindow().drawPoint(point.getX(), point.getY(), 0, 127, 255);
            }
            else if (point.equals(startPoint))
            {
                Data.getWindow().drawPoint(point.getX(), point.getY(), 0, 64, 0);
            }
            else if (point.equals(endPoint))
            {
                Data.getWindow().drawPoint(point.getX(), point.getY(), 64, 0, 0);
            }
            else
            {
                Data.getWindow().drawPoint(point.getX(), point.getY(), 255, 255, 255);
            }
        }
        
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
    /**
     * Finds the start and end points.
     * Start: find leftmost point, lowest if there are multiple.
     * End: Finds rightmost point, highest if there are multiple.
     */
    private void genStartAndEnd()
    {
        Point[] points = graph.getSortedPoints();
        
        Point start = points[0];
        Point end = points[0];
        for (int i = 1; i < points.length; i++)
        {
            Point currentPoint = points[i];
            if (currentPoint.getX() < start.getX())
            {
                start = currentPoint;
                startIndex = i;
            }
            else if (currentPoint.getX() == start.getX())
            {
                if (currentPoint.getY() < start.getY())
                {
                    start = currentPoint;
                    startIndex = i;
                }
            }
            
            if (currentPoint.getX() > end.getX())
            {
                end = currentPoint;
                endIndex = i;
            }
            else if (currentPoint.getX() == end.getX())
            {
                if (currentPoint.getY() > end.getY())
                {
                    end = currentPoint;
                    endIndex = i;
                }
            }
        }
        startPoint = start;
        endPoint = end;
        
        initArea();
    }
    private void initArea()
    {
        currentPoint = startPoint;
        currentPointIndex = startIndex;
        
        selectedPoint = currentPoint.getNeighborsArr()[0];
        selectedPointIndex = 0;
    }
    private int getIndex(Point point)
    {
        Point[] points = graph.getSortedPoints();
        return Arrays.binarySearch(points, point);
    }
}