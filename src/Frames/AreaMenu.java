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
                //select new point and handle exiting area
                if (Data.getCurrentArea().select())
                {
                    Data.pushEvent(new String[]{"switch", "WorldMenu"});
                }
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