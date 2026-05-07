package GraphGen;

public class Edge
{
    private Vertex v0;
    private Vertex v1;
    
    public Edge(Vertex pV0, Vertex pV1)
    {
        v0 = pV0;
        v1 = pV1;
    }
    
    //No need for setter, data should only be set on construction
    
    public Vertex getV0()
    {
        return v0;
    }
    public Vertex getV1()
    {
        return v1;
    }
    public double getDist()
    {
        return Math.sqrt(Math.pow((v1.getX() - v0.getX()), 2) + Math.pow((v1.getY() - v0.getY()), 2));
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
        
        final Edge edge = (Edge)obj; //won't be changed
        
        if (!edge.getV0().equals(v0))
        {
            return false;
        }
        if (!edge.getV1().equals(v1))
        {
            return false;
        }
        
        return true;
    }
    
    public String toString()
    {
        return "Edge of length " + getDist() + " between " + v0 + " and " + v1;
    }
}