package Frames;

import Game.Data;
import Game.SaveHandeler;

import Graphics.UI.RadioButton;

public class WonMenu extends Frame
{
    RadioButton options;
    
    public WonMenu()
    {
        initButtons();
        SaveHandeler.save();
    }
    public boolean eventHandeler(String event)
    {
        switch (event)
        {
            case "":
                Data.getParty().get(0).levelUp();
                Data.pushEvent(new String[]{"switch", "AreaMenu"});
                
        }
        
        render();
        return true;
    }
    private void initButtons()
    {
        options = new RadioButton();
        options.addButton("\u25ba", "Continue", 28, 19);
    }
    private void render()
    {
        options.render(Data.getWindow(), 255, 255, 255);
        Data.getWindow().writeText("You Win!", 28, 16, 64, 255, 0);
        Data.getWindow().writeText("You gained 1 level!", 24, 17, 255, 255, 255);
        
        Data.getWindow().writeText("Enter: select", 1, 35, 255, 255, 255);
    }
}