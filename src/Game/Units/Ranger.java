package Game.Units;

import Game.SpriteHandeler;
import Game.Unit;

public class Ranger extends Unit
{
    public Ranger()
    {
        sprite = SpriteHandeler.getUnit("ranger");
        health = 50.0;
        level = 1;
    }
}