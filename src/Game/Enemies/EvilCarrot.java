package Game.Enemies;

import Game.Enemy;
import Game.SpriteHandeler;

public class EvilCarrot extends Enemy
{
    public EvilCarrot(int lvl)
    {
        health = 45;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("evil_carrot");
    }
}