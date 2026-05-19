package Game.Enemies;

import Game.Enemy;
import Game.SpriteHandeler;

public class Eyeball extends Enemy
{
    public Eyeball(int lvl)
    {
        health = 75;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("eyeball");
    }
}