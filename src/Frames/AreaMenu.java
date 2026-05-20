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
        
        render();
        return true;
    }
    private void render()
    {
        drawGraph();
        
        Data.getWindow().writeText("a: move to left area", 1, 35, 255, 255, 255);
        Data.getWindow().writeText("s: move to right area", 1, 36, 255, 255, 255);
        Data.getWindow().writeText("Enter: select", 1, 37, 255, 255, 255);
        
        Data.getWindow().drawPoint(32, 35, 0, 0, 128);
        Data.getWindow().writeText(": Your Location", 33, 35, 255, 255, 255);
        
        Data.getWindow().drawPoint(32, 36, 0, 127, 255);
        Data.getWindow().writeText(": Location to move to", 33, 36, 255, 255, 255);
        
        Data.getWindow().drawPoint(32, 37, 0, 64, 0);
        Data.getWindow().writeText(": Start location", 33, 37, 255, 255, 255);
        
        Data.getWindow().drawPoint(32, 38, 64, 0, 0);
        Data.getWindow().writeText(": Exit", 33, 38, 255, 255, 255);
    }
    private void drawGraph()
    {
        Area area = Data.getCurrentArea();
        area.render();
    }
}