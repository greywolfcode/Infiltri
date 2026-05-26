package Frames;

import Game.Data;
import Game.SpriteHandeler;
import Game.Unit;

import Game.Units.Knight;
import Game.Units.Ranger;
import Game.Units.Wizard;

import Graphics.Surface;

import Graphics.UI.RadioButton;

public class UnitSelectionMenu extends Frame
{
    private RadioButton buttons;
    
    public UnitSelectionMenu()
    {
        buttons = new RadioButton();
        initButtons();
    }
    public boolean eventHandeler(String event)
    {
        switch (event)
        {
            case "a":
                buttons.move(-1);
                break;
            case "d":
                buttons.move(1);
                break;
            case "":
                if (buttons.getSelectedButton() == 0)
                {
                    Data.addUnitToParty(new Wizard());
                }
                else if (buttons.getSelectedButton() == 1)
                {
                    Data.addUnitToParty(new Ranger());
                }
                else if (buttons.getSelectedButton() == 2)
                {
                    Data.addUnitToParty(new Knight());   
                }
                
                for (Unit unit:Data.getParty())
                {
                    unit.init();
                }
                
                //no matter which you select, move to actual game
                Data.pushEvent(new String[]{"switch", "AreaMenu"});
                
                break;
        }
        
        render();
        
        return true;
    }
    private void render()
    {
        Surface wizard = SpriteHandeler.getUnit("wizard");
        Surface ranger = SpriteHandeler.getUnit("ranger");
        Surface knight = SpriteHandeler.getUnit("knight");
        
        Data.getWindow().writeText("Select Your Unit:", 1, 1, 255, 255, 255);
        Data.getWindow().blit(wizard, 1, 32-wizard.getHeight()-1);
        Data.getWindow().blit(ranger, wizard.getWidth() + 4, 32-ranger.getHeight()-1);
        Data.getWindow().blit(knight, 64-knight.getWidth()-1, 32-knight.getHeight()-1);
        
        Data.getWindow().writeText("a: left", 1, 35, 255, 255, 255);
        Data.getWindow().writeText("d: right", 1, 36, 255, 255, 255);
        Data.getWindow().writeText("Enter: select", 1, 37, 255, 255, 255);
        
        buttons.render(Data.getWindow(), 255, 255, 255);
    }
    private void initButtons()
    {
        buttons.addButton("\u25ba", "Wizard", 4, 6);
        buttons.addButton("\u25ba", "Ranger", 25, 6);
        buttons.addButton("\u25ba", "Knight", 45, 6);
    }
}