package Game.Enemies;

import Game.Enemy;
import Game.SpriteHandeler;

public class SmallSnail extends Enemy; 
{
    public SmallSnail(int lvl)
    {
        health = 10;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("snail_small");
    }
}