package GraphGen;

import java.util.HashSet;

public class Graph 
{
    private static double maxDist = 100.0;
    private static double minDist = 0.0;
    
    private ArrayList<Point> points;
    
    public Graph(Triangle[] pTriangles)
    {
        points = new HashSet<Point>();
        
        for (Tringle tri:triangles)
        {
            for (Vertex v :tri.getVerticies())
            {
                Point p = new Point(v.getX(), v.getY());
                
                Point currentPoint;
                if (points.add(p))
                {
                    currentPoint = p;
                }
                //value already exists in HashSet; find it.
                else
                {
                    for (Point point:points)
                    {
                        if point.equals(p)
                        {
                            currentPoint = p
                            break;
                        }
                    }
                }
                currentPoint.addNeighbors(tri.getVerticies());
            }
        }
    }
    public HashSet<Points> getPoints()
    {
        return points;
    }
    
    public static Graph gen(int numPoints, double minX, double maxX, double minY, double maxY)
    {
        Triangle superTri = genSuperTri(minX, maxX, minY, maxY);
        
        ArrayList<Triangle> triangles = new ArrayList<>();
        triangles.add(superTri);
        
        //add numPoints random points
        for (int i = 0; i < numPoints; i++)
        {
            double x = minX + (Math.random() * (maxX - minX));
            double y = minY + (Math.random() * (maxY - minY));
            
            Vertex newPoint = new Vertex(x, y);
            addVertex(newPoint, triangles);
        }
        
        //remove unnessecary triangles
        for (int i = triangles.size() - 1; i >= 0; i--)
        {
            Triangle currentTri = triangles.get(i);
            //remove triangles that contact super triangle
            if (currentTri.sharesEdge(superTri))
            {
                triangles.remove(i);
            }
            //remove triangles with edges too big or too small
            else
            {
                Edge edge0 = new Edge(currentTri.getV0(), currentTri.getV1());
                Edge edge1 = new Edge(currentTri.getV1(), currentTri.getV2());
                Edge edge2 = new Edge(currentTri.getV2(), currentTri.getV0());
                
                if (edge0.getDist() < minDist || edge0.getDist() > maxDist)
                {
                    triangles.remove(i);
                }
                else if (edge1.getDist() < minDist || edge1.getDist() > maxDist)
                {
                    triangles.remove(i);
                }
                else if (edge2.getDist() < minDist || edge2.getDist() > maxDist)
                {
                    triangles.remove(i);
                }
            }
        }
        return new Graph(triangles.toArray(new Triangle[0]));
    }
    private static Triangle genSuperTri(double minX, double maxX, double minY, double maxY)
    {
        // generate super triangle
        double dx = maxX - minX * 10;
        double dy = maxY - minY * 10;
        
        Vertex v0 = new Vertex(minX - dx, minY - dy * 3);
        Vertex v1 = new Vertex(minX - dx, maxX + dy);
        Vertex v2 = new Vertex(maxX + dx * 3, maxX + dy);
        
        return new Triangle(v0, v1, v2);
    }
    /**
     * Adds new vertex to Triangle ArrayList in place
     */
    private static void addVertex(Vertex point, ArrayList<Triangle> triangles)
    {
        ArrayList<Edge> edges = new ArrayList<>();
        
        //remove all triangles containing the point
        for (int i = triangles.size() - 1; i >= 0; i--)
        {
            Triangle currentTri = triangles.get(i);
            
            if (currentTri.isInTriangle(point))
            {
                edges.add(new Edge(currentTri.getV0(), currentTri.getV1()));
                edges.add(new Edge(currentTri.getV1(), currentTri.getV2()));
                edges.add(new Edge(currentTri.getV2(), currentTri.getV0()));
                
                triangles.remove(i);
            }
        }
        
        removeDuplicates(edges);
        
        for (Edge edge:edges)
        {
            triangles.add(new Triangle(edge.getV0(), edge.getV1(), point));
        }
    }
    /**
     * Remove duplicate edges from Edge ArrayList in place
     */
    private static void removeDuplicates(ArrayList<Edge> edges)
    {
        for (int i = edges.size() - 1; i >= 0; i--)
        {
            boolean unique = true;
            Edge currentEdge = edges.get(i);
            
            for (int j = i -1; j >= 0; j--)
            {
                if (currentEdge.equals(edges.get(j)))
                {
                    unique = false;   
                }
            }
            
            if (!unique)
            {
                edges.remove(i);
            }
        }
    }
}