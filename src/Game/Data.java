package Game;

import java.util.Stack;

import Graphics.Surface;

public class Data 
{
    private static Surface window;
    private Stack<String[]> events = new Stack<>();
    
    public static void setWindow(Surface win)
    {
        window = win;
    }
    public static Surface getWindow()
    {
        return window;
    }
    public void pushEvent(String[] event)
    {
        events.push(event);
    }
    public String[] getEvent()
    {
        if (events.empty())
        {
            return new String[] {""};
        }
        
        return events.pop();
    }
}