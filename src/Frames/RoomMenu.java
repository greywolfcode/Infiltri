package Frames;

import java.util.ArrayList;

import Game.Data;
import Game.Room;
import Game.Unit;

public class RoomMenu extends Frame
{
    private Room room;
    
    public RoomMenu()
    {
        room = Data.getCurrentRoom();
    }
    public boolean eventHandeler(String event) 
    {
        render();
        return true;
    }
    private void render()
    {
        Data.getWindow().blit(room.getBackground(), 0, 0);
        
        ArrayList<Unit> units = Data.getParty();
        Unit main = units.get(0);
        Data.getWindow().blit(main.getSprite(), 5, 32-main.getSprite().getHeight());
    }
}