package Turtle;

import Game.Enemy;
import Game.SpriteHandeler;

public class Turtle extends Enemy
{
    public Turtle(int lvl)
    {
        health = 25.0;
        level  = lvl;
        sprite = SpriteHandeler.getEnemy("turtle");
    }
}