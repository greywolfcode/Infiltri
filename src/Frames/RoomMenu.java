package Frames;

import Game.Data;
import Game.Room;

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
    }
}