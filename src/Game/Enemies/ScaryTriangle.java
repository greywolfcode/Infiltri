package Game.Enemies;

import Game.Enemy;
import Game.SpriteHandeler;

public class ScaryTriangle extends Enemy
{
    public ScaryTriangle(int lvl)
    {
        health = 50.0;
        level= lvl;
        sprite = SpriteHandeler.getEnemy("scary_triangle");
    }
}