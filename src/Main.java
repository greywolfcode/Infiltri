import java.util.Scanner;

import ConsoleControl.Screen;

import Frames.Frame;
import Frames.AreaMenu;
import Frames.MainMenu;
import Frames.RoomMenu;
import Frames.WorldMenu;

import Game.Data;
import Game.SpriteHandeler;

import Graphics.Surface;

public class Main 
{
    private static Frame currentFrame;
    
    private static Surface window;
    
    public static void main(String[] args) 
    {
        SpriteHandeler.init();
        
        Scanner in = new Scanner(System.in);
        
        window = new Surface(64, 32);
        window.fill(0, 0, 0);
        Data.setWindow(window);
        
        Game.Data.genAreas();
        
        currentFrame = new MainMenu();
        currentFrame.eventHandeler("null"); //don't want to run a command
        
        window.update();
        
        String input;
        while (true)
        {
            //reset window
            window.fill(0, 0, 0);
            
            //process events
            while (Game.Data.hasEvent())
            {
                parseEvent(Data.getEvent());
            }
            
            //handle input
            input = in.nextLine();
            
            if (!currentFrame.eventHandeler(input))
            {
                break;
            }
            
            render();
        }
        
        in.close();
    }
    private static void render()
    {
        Screen.clear();
        window.update();
    }
    private static void parseEvent(String[] event)
    {
        switch (event[0])
        {
            case "switch":
                switchFrame(event[1]);
                break;
        }
    }
    private static void switchFrame(String newFrame)
    {
        switch (newFrame)
        {
            case "AreaMenu":
                currentFrame = new AreaMenu();
                break;
            case "MainMenu":
                currentFrame = new MainMenu();
                break;
            case "RoomMenu":
                currentFrame = new RoomMenu();
                break;
            case "WorldMenu":
                currentFrame = new WorldMenu();
                break;
        }
        
        //write new frame to the screen
        currentFrame.eventHandeler("null"); //don't want to run a command
        render();
        
        
    }
}