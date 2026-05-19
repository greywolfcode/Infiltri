package Game.Enemies;

import Game.Enemy;
import Game.SpriteHandeler;

public class Spider extends Enemy
{
    public Spider(int lvl)
    {
        health = 20;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("spider");
    }
}