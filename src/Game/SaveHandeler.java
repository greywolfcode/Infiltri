package Game;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

import Game.Units.Knight;
import Game.Units.Ranger;
import Game.Units.Wizard;

import GraphGen.Edge;
import GraphGen.Graph;
import GraphGen.Point;

import SmallJson.Node;
import SmallJson.SmallJson;
import SmallJson.JsonBuilder;

public class SaveHandeler 
{
    private static String path = "save.json";
    
    private static HashMap<String, Integer> areaIndexes = new HashMap<>(
            Map.ofEntries(
                Map.entry("area_0", 0),
                Map.entry("area_1", 1),
                Map.entry("area_2", 2),
                Map.entry("area_3", 3),
                Map.entry("area_4", 4),
                Map.entry("area_5", 5),
                Map.entry("area_6", 6),
                Map.entry("area_7", 7)
            )
        );
    
    private static HashMap<String, Integer> roomIndexes = new HashMap<>(
            Map.ofEntries(
                Map.entry("room_0", 0),
                Map.entry("room_1", 1),
                Map.entry("room_2", 2),
                Map.entry("room_3", 3),
                Map.entry("room_4", 4),
                Map.entry("room_5", 5),
                Map.entry("room_6", 6),
                Map.entry("room_7", 7),
                Map.entry("room_8", 8),
                Map.entry("room_9", 9)
            )
        );
    private static HashMap<String, Integer> attackIndexes = new HashMap<>(
            Map.ofEntries(
                Map.entry("attack_0", 0),
                Map.entry("attack_1", 1),
                Map.entry("attack_2", 2),
                Map.entry("attack_3", 3),
                Map.entry("attack_4", 4)
            )
        );
    
    private SaveHandeler(){}
    
    public static boolean deleteSave()
    {
        try
        {
            Path savePath = Paths.get(path);
            return Files.deleteIfExists(savePath);
        }
        catch (IOException e)
        {
            return false;
        }
    }
    public static boolean doesSaveExist()
    {
        File file = new File(path);
        
        return file.exists() && file.isFile();
    }
    public static void load()
    {
        Node loadedData = SmallJson.read(path);
        
        //always starts as a Json Object
        HashMap<String, Node> data = loadedData.getAsObject();
        
        //load progress data
        HashMap<String, Node> progress = data.get("progress").getAsObject();
        
        Data.setCurrentAreaNum(progress.get("area").getAsDouble().intValue());
        Data.setCurrentAreaLevel(progress.get("level").getAsDouble().intValue());
        
        //load player data
        HashMap<String, Node> playerData = data.get("player").getAsObject();
        
        Unit player;
        double playerHealth = playerData.get("health").getAsDouble();
        int playerLevel = playerData.get("level").getAsDouble().intValue();
        Attack[] playerAttacks = new Attack[3];
        
        HashMap<String, Node> playerAttackData = playerData.get("attacks").getAsObject();
        playerAttacks[0] = Attack.getAttack(playerAttackData.get("attack_0").getAsString());
        playerAttacks[1] = Attack.getAttack(playerAttackData.get("attack_1").getAsString());
        playerAttacks[2] = Attack.getAttack(playerAttackData.get("attack_2").getAsString());
        
        //load player
        switch (playerData.get("type").getAsString())
        {
            case "knight":
                player = new Knight(playerHealth, playerLevel, playerAttacks);
                break;
            case "ranger":
                player = new Ranger(playerHealth, playerLevel, playerAttacks);
                break;
            case "wizard":
                player = new Wizard(playerHealth, playerLevel, playerAttacks);
                break;
        }
        
        //load world
        HashMap<String, Node> world = data.get("world").getAsObject();
        
        HashMap<String, Node> levelOneAreas = world.get("level_1").getAsObject();
        Area[] levelOne = loadAreas(levelOneAreas);
        
        HashMap<String, Node> levelTwoAreas = world.get("level_2").getAsObject();
        Area[] levelTwo = loadAreas(levelTwoAreas);
        
    }
    public static void save()
    {
        JsonBuilder builder = new JsonBuilder();  
        
        //Add progress data
        JsonBuilder progress = builder.addObject("progress");
        
        progress.addNumber("area", Double.valueOf(Data.getCurrentAreaNum()));
        progress.addNumber("level", Double.valueOf(Data.getCurrentAreaLevel()));
        
        //Add player data
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
        //add important indexes
        area.addNumber("startPoint", Double.valueOf(currentArea.getStartIndex()));
        area.addNumber("endPoint", Double.valueOf(currentArea.getEndIndex()));
        area.addNumber("currentPoint", Double.valueOf(currentArea.getCurrentPointIndex()));
        area.addNumber("selectedPoint", Double.valueOf(currentArea.getSelectedPointIndex()));
        
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
            
            JsonBuilder neighbors = point.addObject("neighbors");
            
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
            builder.addString("attack_" + i, attacks[i].toString());
        }
    }
    private static Area[] loadAreas(HashMap<String, Node> areas)
    {
        Area[] output = new Area[areas.size()];
        
        for (String key:areas.keySet())
        {
            output[areaIndexes.get(key)] = loadArea(areas.get(key).getAsObject());
        }
        
        return output;
    }
    private static Area loadArea(HashMap<String, Node> area)
    {
        int level = area.get("level").getAsDouble().intValue();
        
        int startIndex = area.get("startPoint").getAsDouble().intValue();
        int endIndex = area.get("endPoint").getAsDouble().intValue();
        int currentPointIndex = area.get("currentPoint").getAsDouble().intValue();
        int selectedPointIndex = area.get("selectedPoint").getAsDouble().intValue();
        
        ArrayList<Room> rooms = loadRooms(area.get("rooms").getAsObject());
        
        Graph graph = loadGraph(area.get("graph").getAsObject());
        
        return new Area(level, rooms, graph, startIndex, endIndex, currentPointIndex, selectedPointIndex);
    }
    private static Graph loadGraph(HashMap<String, Node> graph)
    {
        //load edges
        HashMap<String, Node> edgesData = graph.get("edges").getAsObject();
        HashSet<Edge> edges = new HashSet<>();
        
        for (Node value:edgesData.values())
        {
            edges.add(loadEdge(value.getAsObject()));
        }
        
        //load points
        HashMap<String, Node> pointsData = graph.get("points").getAsObject();
        HashSet<Point> points = new HashSet<>();
        
        for (Node point:pointsData.values())
        {
            loadPoint(point.getAsObject(), points);
        }
        
        return new Graph(points, edges);
    }
    private static void loadPoint(HashMap<String, Node> pointData, HashSet<Point> points)
    {
        //create actual point
        int x = pointData.get("coords").getAsObject().get("x").getAsDouble().intValue();
        int y = pointData.get("coords").getAsObject().get("y").getAsDouble().intValue();
        
        Point p = new Point(x, y);
        
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
        
        //add neighbors to point
        HashMap<String, Node> neighborsData = pointData.get("neighbors").getAsObject();
        
        for (Node neighbor:neighborsData.values())
        {
            HashMap<String, Node> coords = neighbor.getAsObject().get("coords").getAsObject();
            
            int nX = coords.get("x").getAsDouble().intValue();
            int nY = coords.get("y").getAsDouble().intValue();
            Point n = new Point(nX, nY);
            
            currentPoint.addNeighbor(n);
        }
        
    }
    private static Edge loadEdge(HashMap<String, Node> edge)
    {
        HashMap<String, Node> v0 = edge.get("v0").getAsObject();
        
        double v0X = v0.get("x").getAsDouble();
        double v0Y = v0.get("y").getAsDouble();
        
        HashMap<String, Node> v1 = edge.get("v1").getAsObject();
        
        double v1X = v1.get("x").getAsDouble();
        double v1Y = v1.get("y").getAsDouble();
        
        return new Edge(v0X, v0Y, v1X, v1Y);
    }
    private static ArrayList<Room> loadRooms(HashMap<String, Node> rooms)
    {
        Room[] roomArr = new Room[rooms.size()];
        
        for (String key:rooms.keySet())
        {
            roomArr[roomIndexes.get(key)] = loadRoom(rooms.get(key).getAsObject());
        }
        
        return new ArrayList<>(Arrays.asList(roomArr));
    }
    private static Room loadRoom(HashMap<String, Node> room)
    {
        int level = room.get("level").getAsDouble().intValue();
        
        int x = room.get("x").getAsDouble().intValue();
        int y = room.get("y").getAsDouble().intValue();
        
        boolean cleared = room.get("cleared").getAsBoolean();
        
        RoomType type = RoomType.valueOf(room.get("type").getAsString());
        
        Enemy enemy = loadEnemy(room.get("Enemy").getAsObject());
        
        return new Room(cleared, level, x, y, type, enemy);
        
    }
    private static Enemy loadEnemy(HashMap<String, Node> enemyData)
    {
        String type = enemyData.get("type").getAsString();
        
        int level = enemyData.get("level").getAsDouble().intValue();
        
        HashMap<String, Node> attacksData = enemyData.get("attacks").getAsObject();
        
        Attack[] attacks = new Attack[attacksData.size()];
        
        for (String key:attacksData.keySet())
        {
            attacks[attackIndexes.get(key)] = Attack.getAttack(attacksData.get(key).getAsString());
        }
        
        return EnemyGenerator.getEnemy(type, level, attacks);
    }
}