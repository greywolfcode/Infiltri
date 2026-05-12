package GraphGen;

import java.util.HashSet;

public class Point 
{
    private int x;
    private int y;
    
    private HashSet<Point> neighbors;
    
    public Point(int pX, pY)
    {
        x = pX;
        y= pY;
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
    public HashSet<Point> getNeighbors()
    {
        return neighbors;
    }
    public Point[] getNeighbors()
    {
        Point[] points = neighbors.toArray(new Point[0]);
        return Arrays.sort(points);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
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
        return Object.hash(x, y);
    }
    @Override
    public int comapreTo(Point point)
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