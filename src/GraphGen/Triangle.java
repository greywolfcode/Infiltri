package GraphGen;

public class Triangle 
{
    private Vertex v0;
    private Vertex v1;
    private Vertex v2;
    
    private Circle circumCircle;
    
    public Triangle(Vertex pV0, Vertex pV1, Vertex pV2)
    {
        v0 = pV0;
        v1 = pV1;
        v2 = pV2;
        
        circumCircle = new Circle(v0, v1, v2);
    }
    public Circle getCircumCircle()
    {
        return circumCircle;
    }
    public Vertex getV0()
    {
        return v0;
    }
    public Vertex getV1()
    {
        return v1;
    }
    public Vertex getV2()
    {
        return v2;
    }
    
    public boolean isInTriangle(Vertex point)
    {
        if (circumCircle.isInCircle(point) >= 0)
        {
            return true;
        }
        return false;
    }
    
    public boolean sharesEdge(Triangle tri)
    {
        if (tri.getV0().equals(v0) || tri.getV0().equals(v1) || tri.getV0().equals(v2)
            || tri.getV1().equals(v0) || tri.getV1().equals(v1) || tri.getV2().equals(v2)
            || tri.getV2().equals(v0) || tri.getV2().equals(v1) || tri.getV2().equals(v2))
        {
            return true;
        }

        return false;
    }
}