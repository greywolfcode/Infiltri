package GraphGen;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class Graph
{
    private static double maxDist = 100.0;
    private static double minDist = 0.0;
    
    private HashSet<Point> points;
    private HashSet<Edge> edges;
    
    public Graph(Triangle[] triangles)
    {
        points = new HashSet<Point>();
        edges = new HashSet<Edge>();
        
        for (Triangle tri:triangles)
        {
            edges.add(new Edge(tri.getV0(), tri.getV1()));
            edges.add(new Edge(tri.getV1(), tri.getV2()));
            edges.add(new Edge(tri.getV2(), tri.getV0()));
            
            for (Vertex v :tri.getVerticies())
            {

                //want points to be at nearest int
                Point p = new Point((int)v.getX(), (int)v.getY());
                
                Point currentPoint = new Point(-1, -1);
                if (points.add(p))
                {
                    currentPoint = p;
                }
                //value already exists in HashSet; find it.
                else
                {
                    for (Point point:points)
                    {
                        if (point.equals(p))
                        {
                            currentPoint = point;
                            break;
                        }
                    }
                }
                currentPoint.addNeighbors(tri.getVerticies());
            }
        }
    }
    public HashSet<Point> getPoints()
    {
        return points;
    }
    public Point[] getSortedPoints()
    {
        Point[] pointArr = points.toArray(new Point[0]);
        Arrays.sort(pointArr);
        return pointArr;
    }
    public HashSet<Edge> getEdges()
    {
        return edges;
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