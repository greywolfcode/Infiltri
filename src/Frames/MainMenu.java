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

import Graphics.UI.RadioButton;

public class MainMenu extends Frame
{
    private Surface title;
    private Surface options;
    
    private int cursorPos = 0; //store where cursor pos is on screen
    
    private RadioButton buttons;
    
    public MainMenu()
    {
        buttons = new RadioButton();
        initSurfaces();   
        initButtons();
    }
    public boolean eventHandeler(String input)
    {
        switch (input)
        {
            case "w":
                buttons.move(-1);
                break;
            case "s":
                buttons.move(1);
                break;
            case "quit":
                return false;
            case "":
                if (buttons.getSelectedButton() == 1)
                {
                    return false;
                }
                else if (buttons.getSelectedButton() == 0)
                {
                    Data.pushEvent(new String[]{"switch", "UnitSelectionMenu"});
                }
                break;
        }
        
        draw();

        return true;
    }
    private void draw()
    {
        //prepare options
        options.clear();
        buttons.render(options, 255, 255 , 255);
        
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
        draw();
    }
    private void optionsReset()
    {
        buttons.setSelectedButton(0);
    }
    private void initButtons()
    {
        buttons.addButton("\u25ba", "Play", 0, 0);
        buttons.addButton("\u25ba", "Quit", 0, 2);
    }
}