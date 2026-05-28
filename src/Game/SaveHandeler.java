package Game;

import SmallJson.SmallJson;
import SmallJson.JsonBuilder;

public class SaveHandeler 
{
    private SaveHandeler(){}
    
    public static void save()
    {
        JsonBuilder builder = new JsonBuilder();  
        
        //Add player attacks
        JsonBuilder player = builder.addObject("player");
        
        Unit playerUnit = Data.getParty().get(0);
        player.addString("sprite", player.toString());
        player.addNumber("health", playerUnit.getMaxHealth());
        player.addNumber("level", Double.valueOf(playerUnit.getLevel()));
        
        JsonBuilder playerAttacks = player.addObject("attacks");
        
        Attack[] playerAttacksArr = playerUnit.getAttacks();
        for(int i = 0; i < playerAttacksArr.length; i++)
        {
            playerAttacks.addString("" + i, playerAttacksArr[i].toString());
        }
        
    }
}