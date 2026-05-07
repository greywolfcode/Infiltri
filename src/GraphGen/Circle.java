package GraphGen;

public class Circle 
{
    Vertex centre;
    double radius;
    
    public Circle(Vertex v0, Vertex v1, Vertex v2)
    {
        //calculate circle from 3 points
        double s0 = Math.pow(v0.getX(), 2) + Math.pow(v0.getY(), 2);
        double s1 = Math.pow(v1.getX(), 2) + Math.pow(v1.getY(), 2);
        double s2 = Math.pow(v2.getX(), 2) + Math.pow(v2.getY(), 2);
        
        double m10 = v0.getX() * v1.getY() + v1.getX() * v2.getY() + v2.getX() 
                     * v0.getY() - (v1.getX() * v0.getY() + v2.getX() 
                     * v1.getY() + v0.getX() * v2.getY());
        double m11 = s0 * v1.getY() + s1 * v2.getY() + s2 * v0.getY() 
                     - (s1 * v0.getY() + s2 * v1.getY() + s0 * v2.getX());
        double m12 = s0 * v1.getX() + s1 * v2.getX() + s2 * v0.getX() - (s1 
                     * v0.getX() + s2 * v1.getX() + s0 * v2.getX());
        
        double x = 0.5 * m11 / m10;
        double y = -0.5 * m12 / m10;
        double r = Math.sqrt(Math.pow((v0.getX() - x), 2) + Math.pow((v0.getY() - y), 2));
        
        centre = new Vertex(x, y);
        radius = r;
    }
    public Vertex getCentre()
    {
        return centre;
    }
    public double getRadius()
    {
        return radius;
    }
    
    /**
     * takes in: the vertex
     * outputs: integer representing where the point is
     *          -1 is outside, 0 if on the circle, 1 if inside
     */
    public int isInCircle(Vertex point)
    {
        double dist = Math.pow((centre.getX() - point.getX()), 2) + Math.pow((centre.getY() - point.getY()), 2);
        double radSquared = Math.pow(radius, 2);
        
        if (dist > radSquared)
        {
            return -1;
        }
        else if (dist == radSquared)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}