import java.util.Scanner;

import ConsoleControl.Screen;

import Frames.Frame;
import Frames.MainMenu;

import Game.Data;

import Graphics.Surface;

public class Main 
{
    
    private static Frame currentFrame;
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        
        
        Surface window = new Surface(64, 32);
        window.fill(0, 0, 0);
        Data.setWindow(window);
        
        currentFrame = new MainMenu();
        currentFrame.eventHandeler("");
        
        window.update();
        
        String input;
        while (true)
        {
            //reset window
            window.fill(0, 0, 0);
            
            //handle input
            input = in.nextLine();
            
            if (!currentFrame.eventHandeler(input))
            {
                break;
            }
            
            Screen.clear();
            window.update();
        }
    }
}