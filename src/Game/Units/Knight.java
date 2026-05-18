package Game.Units;

import Game.Unit;
import Game.SpriteHandeler;

public class Knight extends Unit
{
    public Knight()
    {
        sprite = SpriteHandeler.getUnit("knight");
        health = 50.0;
        level = 1;
    }
}