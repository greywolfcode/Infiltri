package Game;

import java.util.Stack;

import Graphics.Surface;

public class Data 
{
    private static Surface window;
    private Stack<String[]> events = new Stack<>();
    
    private Area[] levelOneAreas();
    private Area[] levelTwoAreas();
    
    private int currentArea;
    private int areaLevel;
    
    public static void genAreas()
    {
        currentArea = 1;
        areaLevel = 1;
        
        levelOneAreas = new Area[8];
        for (int i = 0; i < 8; i++)
        {
            levelOneAreas[i] = new Area(1);
            levelOneAreas[i].generateArea();
        }
        
        levelTwoAreas = new Area[4];
        for (int i = 0; i < 4; i++)
        {
            levelTwoAreas[i] = new Area(2);
            levelTwoAreas[i].generateArea();
        }
    }
    public static Area getArea(int level, int areaNum)
    {
        if (level == 1)
        {
            return levelOneAreas[areaNum];
        }
        else if (level == 2)
        {
            return levelTwoAreas[areaNum];
        }
    }
    public static void setWindow(Surface win)
    {
        window = win;
    }
    public static Surface getWindow()
    {
        return window;
    }
    public static void pushEvent(String[] event)
    {
        events.push(event);
    }
    public static String[] getEvent()
    {
        if (events.empty())
        {
            return new String[] {""};
        }
        
        return events.pop();
    }
    public boolean hasEvent()
    {
        return events.size() != 0;
    }
}