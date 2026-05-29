package Game;

import java.util.ArrayList;

import Game.Area;
import Game.Data;
import Game.Enemy;
import Game.Room;

import GraphGen.Edge;
import GraphGen.Graph;
import GraphGen.Point;

import SmallJson.SmallJson;
import SmallJson.JsonBuilder;

public class SaveHandeler 
{
    private static String path = "save.json";
    
    private SaveHandeler(){}
    
    public static void save()
    {
        JsonBuilder builder = new JsonBuilder();  
        
        //Add player attacks
        JsonBuilder player = builder.addObject("player");
        
        Unit playerUnit = Data.getParty().get(0);
        player.addString("sprite", playerUnit.toString());
        player.addNumber("health", playerUnit.getMaxHealth());
        player.addNumber("level", Double.valueOf(playerUnit.getLevel()));
        
        JsonBuilder playerAttacks = player.addObject("attacks");
        
        Attack[] playerAttacksArr = playerUnit.getAttacks();
        addAttacks(playerAttacks, playerAttacksArr);
        
        //write world 
        JsonBuilder world = builder.addObject("world");
        
        ///write level 1 areas
        JsonBuilder levelOneAreas = world.addObject("level_1");
        
        Area[] levelOneAreasArr = Data.getLevelOneAreas();
        
        for (int i = 0; i < levelOneAreasArr.length; i++)
        {
            JsonBuilder area = levelOneAreas.addObject("area_" + i);
            
            Area currentArea = levelOneAreasArr[i];
            
            addArea(area, currentArea);
        }
        
        //write level two areas
        JsonBuilder levelTwoAreas = world.addObject("level_2");
        
        Area[] levelTwoAreasArr = Data.getLevelTwoAreas();
        
        for (int i = 0; i < levelTwoAreasArr.length; i++)
        {
            JsonBuilder area = levelTwoAreas.addObject("area_" + i);
            
            Area currentArea = levelTwoAreasArr[i];
            
            addArea(area, currentArea);
        }
        
        //boss room always the same, no need to save it
        
        SmallJson.write(builder, path);
    }
    private static void addArea(JsonBuilder area, Area currentArea)
    {
        //add graph
        JsonBuilder graph = area.addObject("graph");
        addGraph(graph, currentArea.getGraph());
        
        //add rooms
        ArrayList<Room> rooms = currentArea.getRooms();
        
        for (int i = 0; i  < rooms.size(); i++)
        {
            JsonBuilder room = area.addObject("room_" + i);
            
            Room currentRoom = rooms.get(i);
            
            room.addBoolean("cleared", currentRoom.getCleared());
            room.addNumber("level", Double.valueOf(currentRoom.getLevel()));
            
            int[] coords = currentRoom.getCoords();
            room.addNumber("x", Double.valueOf(coords[0]));
            room.addNumber("y", Double.valueOf(coords[1]));
            
            room.addString("type", currentRoom.getType().name());
            
            //write enemy data
            JsonBuilder enemy = room.addObject("enemy");
            
            Enemy currentEnemy = currentRoom.getEnemy();
            
            enemy.addString("type", currentEnemy.toString());
            enemy.addNumber("level", Double.valueOf(currentEnemy.getLevel()));
            
            JsonBuilder enemyAttacks = enemy.addObject("attacks");
            
            addAttacks(enemyAttacks, currentEnemy.getAttacks());
        }
    }
    private static void addGraph(JsonBuilder builder, Graph graph)
    {
        JsonBuilder points = builder.addObject("points");
        Point[] pointsArr = graph.getSortedPoints();
        
        for (int i = 0; i < pointsArr.length; i++)
        {
            JsonBuilder point = points.addObject("point_" + i);
            
            Point currentPoint = pointsArr[i];
            
            JsonBuilder coords = point.addObject("coords");
            
            coords.addNumber("x", Double.valueOf(currentPoint.getX()));
            coords.addNumber("y", Double.valueOf(currentPoint.getY()));
            
            JsonBuilder neighbors = points.addObject("neighbors");
            
            Point[] neighborArr = currentPoint.getNeighborsArr();
            
            for (int j = 0; j < neighborArr.length; j++)
            {
                JsonBuilder neighbor = neighbors.addObject("neighbor_" + j);
                Point currentNeighbor = neighborArr[j];
                
                JsonBuilder nCoords = neighbor.addObject("coords");
                
                nCoords.addNumber("x", Double.valueOf(currentNeighbor.getX()));
                nCoords.addNumber("y", Double.valueOf(currentNeighbor.getY()));
                
            }
        }
        
        JsonBuilder edges = builder.addObject("edges");
        Edge[] edgesArr = graph.getEdgesArr();
        
        for (int i = 0; i < edgesArr.length; i++)
        {
            JsonBuilder edge = edges.addObject("edge_" + i);
            Edge currentEdge = edgesArr[i];
            
            JsonBuilder v0 = edge.addObject("v0");
            JsonBuilder v0Coords = v0.addObject("coords");
            
            v0Coords.addNumber("x", currentEdge.getV0().getX());
            v0Coords.addNumber("y", currentEdge.getV1().getY());
            
            JsonBuilder v1 = edge.addObject("v1");
            JsonBuilder v1Coords = v1.addObject("coords");
            
            v1Coords.addNumber("x", currentEdge.getV1().getX());
            v1Coords.addNumber("y", currentEdge.getV1().getY());
        }
    }
    private static void addAttacks(JsonBuilder builder, Attack[] attacks)
    {
        for(int i = 0; i < attacks.length; i++)
        {
            builder.addString("" + i, attacks[i].toString());
        }
    }
}