package Game.Units;

import Game.SpriteHandeler;
import Game.Unit;

public class Wizard extends Unit
{
    public Wizard()
    {
        sprite = SpriteHandeler.getUnit("wizard");
        health = 50.0;
        level = 1;
    }
}