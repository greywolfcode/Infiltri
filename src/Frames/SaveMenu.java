package Frames;

import Game.Data;
import Game.SaveHandeler;

import Graphics.UI.RadioButton;

public class SaveMenu extends Frame
{
    private RadioButton options; 
    private boolean hasSave;
    
    public SaveMenu()
    {
        hasSave = SaveHandeler.doesSaveExist();
        
        initButtons();
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
                    Data.pushEvent(new String[]{"switch", "AreaMenu"});
                }
                break;
        }
        
        render();
        
        return true;
    }
    private void render()
    {
        if (hasSave)
        {
            Data.getWindow().writeText("Choose Save:", 26, 14, 255, 255, 255);
        }
        else
        {
            Data.getWindow().writeText("No Saves... You should create one.", 15, 14, 255, 255, 255);
        }
        
        options.render(Data.getWindow(), 255, 255, 255);
        
        Data.getWindow().writeText("w: up", 1, 35, 255, 255, 255);         
        Data.getWindow().writeText("s: down", 1, 36, 255, 255, 255);         
        Data.getWindow().writeText("Enter: select", 1, 37, 255, 255, 255);
    }
    private void initButtons()
    {
        options = new RadioButton();
        
        options.addButton("\u25ba", "Back", 28, 16);
        
        if (hasSave)
        {
            options.addButton("\u25ba", "Load Save", 26, 18);
        }
    }
}