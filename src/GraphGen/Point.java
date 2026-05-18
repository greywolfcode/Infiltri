package GraphGen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Point implements Comparable<Point>
{
    private int x;
    private int y;
    
    private HashSet<Point> neighbors;
    
    public Point(int pX, int pY)
    {
        x = pX;
        y= pY;
        neighbors = new HashSet<>();
    }
    public boolean addNeighbor(Point p)
    {
        if (p.equals(this))
        {
            return false;
        }
        return neighbors.add(p);
    }
    public void addNeighbors(Point[] ps)
    {
        for (Point p:ps)
        {
            addNeighbor(p);   
        }
    }
    public void addNeighbors(Vertex[] vs)
    {
        for (Vertex v:vs)
        {
            addNeighbor(new Point((int)v.getX(), (int)v.getY()));
        }
    }
    public HashSet<Point> getNeighbors()
    {
        return neighbors;
    }
    public Point[] getNeighborsArr()
    {
        Point[] points = neighbors.toArray(new Point[0]);
        Arrays.sort(points);
        return points;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        
        final Point point = (Point)obj;
        
        if (point.getX() != x || point.getY() != y)
        {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }
    @Override
    public int compareTo(Point point)
    {
        if (point.getX() < x)
        {
            return -1;
        }
        if (point.getX() > x)
        {
            return 1;
        }
        if (point.getY() < y)
        {
            return -1;
        }
        if (point.getY() > y)
        {
            return 1;
        }
        return 0;
    }
}