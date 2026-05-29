package Game;

import java.util.ArrayList;

import Game.Area;
import Game.Data;
import Game.Enemy;
import Game.Room;

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
    private static void addAttacks(JsonBuilder builder, Attack[] attacks)
    {
        for(int i = 0; i < attacks.length; i++)
        {
            builder.addString("" + i, attacks[i].toString());
        }
    }
}