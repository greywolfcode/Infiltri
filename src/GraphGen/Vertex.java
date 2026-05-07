package GraphGen;

public class Vertex 
{
    private double x;
    private double y;
    
    public Vertex(double pX, double pY)
    {
        x = pX;
        y = pY;
    }
    
    //No need for setters, data should only be set on construction
    
    public double getX()
    {
        return x;
    }
    public double getY()
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
        
        //Check if the provided object is the same
        //class; subclasses will return false
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        
        final Vertex vert = (Vertex)obj; //won't be changed
        
        if (vert.getX() != x || vert.getY() != y)
        {
            return false;
        }
        
        return true;
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}