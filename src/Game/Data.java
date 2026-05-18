package Game;

import java.util.Stack;

import Graphics.Surface;

public class Data 
{
    private static Surface window;
    private static Stack<String[]> events = new Stack<>();
    
    private static Area[] levelOneAreas;
    private static Area[] levelTwoAreas;
    
    private static int currentArea;
    private static int areaLevel;
    
    private static Room currentRoom;
    
    private Data(){}
    
    public static void genAreas()
    {
        currentArea = 0;
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
        
        //will eventually be final room
        return new Area(3);
    }
    public static Area getCurrentArea()
    {
        return getArea(areaLevel, currentArea);
    }
    public static int getCurrentAreaNum()
    {
        return currentArea;
    }
    public static int getCurrentAreaLevel()
    {
        return areaLevel;
    }
    public static Room getCurrentRoom()
    {
        return currentRoom;
    }
    public static Surface getWindow()
    {
        return window;
    }
    public static void setWindow(Surface win)
    {
        window = win;
    }
    public static void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    public static void setCurrentAreaNum(int num)
    {
        currentArea = num;
        getCurrentArea().load();
    }
    public static void setCurrentAreaLevel(int level)
    {
        areaLevel = level;
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
    public static boolean hasEvent()
    {
        return events.size() != 0;
    }
}