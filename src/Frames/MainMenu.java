/*
Main Menu title generated using:
    https://www.asciiart.eu/text-to-ascii-art
    
Main Menu title font (Larry 3d) credits:
    larry3d.flf by Larry Gelberg (larryg@avs.com) 
    (stolen liberally from Juan Car's puffy.flf) 
    tweaked by Glenn Chappell <ggc@uiuc.edu> 
    Version 1.2 2/24/94
*/

package Frames;

import Game.Data;

import Graphics.Surface;

public class MainMenu extends Frame
{
    private Surface title;
    private Surface options;
    
    private int cursorPos = 0; //store where cursor pos is on screen
    
    public MainMenu()
    {
        initSurfaces();   
    }
    public boolean eventHandeler(String input)
    {
        optionsReset();
        
        switch (input)
        {
            case "w":
                cursorPos = 0;
                options.setChar("\u25ba", 0, 0, 128, 128, 128);
                break;
            case "s":
                cursorPos = 2;
                options.setChar("\u25ba", 0, 2, 128, 128, 128);
                break;
            case "quit":
                return false;
            case "ent":
                if (cursorPos == 2)
                {
                    return false;
                }
                else if (cursorPos == 0)
                {
                    Data.pushEvent({"switch", "AreaMenu"});
                }
                break;
            default:
                options.setChar("\u25ba", 0, cursorPos, 128, 128, 128);
        }
        
        draw();

        return true;
    }
    private void draw()
    {
        //render the frame
        Surface window = Data.getWindow();
        window.blit(title, 4, 0);
        window.blit(options, 26, 14);
    }
    private void initSurfaces()
    {
        String[][] titleData = { 
            {" ", "_", "_", "_", "_", "_", "_", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "_", "_", "_", " ", " ", " ", " ", " ", " ", "_", "_", "_", " ", " ", " ", " ", "_", "_", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}, 
            {"/", "\\", "_", "_", " ", " ", "_", "\\", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "/", "'", "_", "_", "_", "\\", " ", "_", "_", " ", "/", "\\", "_", " ", "\\", " ", " ", "/", "\\", " ", "\\", "_", "_", " ", " ", " ", " ", " ", " ", " ", " ", " ", "_", "_", " ", " ", " "}, 
            {"\\", "/", "_", "/", "\\", " ", "\\", "/", " ", " ", " ", " ", " ", "_", "_", "_", " ", "/", "\\", " ", "\\", "_", "_", "/", "/", "\\", "_", "\\", "\\", "/", "/", "\\", " ", "\\", " ", "\\", " ", "\\", " ", ",", "_", "\\", " ", " ", "_", " ", "_", "_", " ", "/", "\\", "_", "\\", " ", " "}, 
            {" ", " ", "\\", " ", "\\", " ", "\\", " ", " ", " ", "/", "'", " ", "_", " ", "`", "\\", " ", "\\", " ", ",", "_", "_", "\\", "/", "\\", " ", "\\", " ", "\\", " ", "\\", " ", "\\", " ", "\\", " ", "\\", " ", "\\", "/", " ", "/", "\\", "`", "'", "_", "_", "\\", "/", "\\", " ", "\\", " ", " "}, 
            {" ", " ", " ", "\\", "_", "\\", " ", "\\", "_", "_", "/", "\\", " ", "\\", "/", "\\", " ", "\\", " ", "\\", " ", "\\", "_", "/", "\\", " ", "\\", " ", "\\", " ", "\\", "_", "\\", " ", "\\", "_", "\\", " ", "\\", " ", "\\", "_", "\\", " ", "\\", " ", "\\", "/", " ", "\\", " ", "\\", " ", "\\", " "}, 
            {" ", " ", " ", "/", "\\", "_", "_", "_", "_", "_", "\\", " ", "\\", "_", "\\", " ", "\\", "_", "\\", " ", "\\", "_", "\\", " ", " ", "\\", " ", "\\", "_", "\\", "/", "\\", "_", "_", "_", "_", "\\", "\\", " ", "\\", "_", "_", "\\", "\\", " ", "\\", "_", "\\", " ", " ", "\\", " ", "\\", "_", "\\"}, 
            {" ", " ", " ", "\\", "/", "_", "_", "_", "_", "_", "/", "\\", "/", "_", "/", "\\", "/", "_", "/", "\\", "/", "_", "/", " ", " ", " ", "\\", "/", "_", "/", "\\", "/", "_", "_", "_", "_", "/", " ", "\\", "/", "_", "_", "/", " ", "\\", "/", "_", "/", " ", " ", " ", "\\", "/", "_", "/"} 
        };
        
        title = new Surface(titleData);
        title.setCharColour(255, 255, 255);
        
        options = new Surface(10, 4);
        optionsReset();
        options.setChar("\u25ba", 0, 0, 128, 128, 128);
        draw();
    }
    private void optionsReset()
    {
        options.clear();
        options.writeText("New Game", 2, 0, 255, 255, 255);
        options.writeText("Quit", 2, 2, 255, 255, 255);
    }
}