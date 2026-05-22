package Frames;

import java.util.ArrayList;

import Game.Data;
import Game.Enemy;
import Game.Room;
import Game.Unit;

public class RoomMenu extends Frame
{
    private Room room;
    int roundNum;
    
    public RoomMenu()
    {
        room = Data.getCurrentRoom();
        roundNum = 0;
    }
    public boolean eventHandeler(String event) 
    {
        Data.getParty().get(0).applyEffects(roundNum);
        room.getEnemy().applyEffects(roundNum);
        
        render();
        roundNum++;
        return true;
    }
    private void render()
    {
        Data.getWindow().blit(room.getBackground(), 0, 0);
        
        //blit player sprite
        ArrayList<Unit> units = Data.getParty();
        Unit main = units.get(0);
        Data.getWindow().blit(main.getSprite(), 5, 32-main.getSprite().getHeight());
        
        //blit enemy sprite
        Enemy enemy = room.getEnemy();
        Data.getWindow().blit(enemy.getSprite(), 64-1-enemy.getSprite().getWidth(), 32-enemy.getSprite().getHeight());
    }
}