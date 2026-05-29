package Frames;

import Game.Data;
import Game.SaveHandeler;

import Graphics.UI.RadioButton;

public class LoseMenu extends Frame
{
    RadioButton options;
    
    public LoseMenu()
    {
        initButtons();
        SaveHandeler.deleteSave();
        Data.reset();
    }
    public boolean eventHandeler(String event)
    {
        switch (event)
        {
            case "w":
                options.move(-1);
                break;
            case "s":
                options.move(1);
                break;
            case "":
                if (options.getSelectedButton() == 0)
                {
                    Data.pushEvent(new String[]{"switch", "MainMenu"});
                }
                else
                {
                    return false;
                }
                break;
        }
        
        render();
        
        return true;
    }
    private void initButtons()
    {
        options = new RadioButton();
        options.addButton("\u25ba", "Main Menu", 26, 18);
        options.addButton("\u25ba", "Quit", 28, 20);
    }
    private void render()
    {
        Data.getWindow().writeText("You Died", 28, 16, 255, 64, 0);
        
        options.render(Data.getWindow(), 255, 255, 255);
        
        Data.getWindow().writeText("w: up", 1, 35, 255, 255, 255);         
        Data.getWindow().writeText("s: down", 1, 36, 255, 255, 255);         
        Data.getWindow().writeText("Enter: select", 1, 37, 255, 255, 255);
    }
}