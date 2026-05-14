package Frames;

import Game.Area;
import Game.Data;

public class AreaMenu extends Frame
{
    public AreaMenu()
    {
        
    }
    public boolean eventHandeler(String event)
    {
        switch(event)
        {
            case "w":
                Data.getCurrentArea().selectPrev();
                break;
            case "s":
                Data.getCurrentArea().selectNext();
                break;
            case "":
                Data.getCurrentArea().select();
                break;
        }
        
        drawGraph();
        return true;
    }
    private void drawGraph()
    {
        Area area = Data.getCurrentArea();
        area.render();
    }
}