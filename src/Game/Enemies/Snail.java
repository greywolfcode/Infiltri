package Game.Enemies;

import Game.Enemy
import Game.SpriteHandeler;

import Graphics.Surface;

public class Snail extends Enemy
{
    private int stage;
    
    private Surface sprite1;
    private Surface sprite2;
    
    public Snail(int lvl)
    {
        health = 25.0;
        level = lvl;
        stage = 1;
        
        sprite1 = SpriteHandeler.getEnemy("snail_small");
        sprite2 = SpriteHandeler.getEnemy("snail_large");
        sprite = sprite1;
    }
    
    //needs to handle two stages
    @Override
    public void damage(double damageAmount)
    {
        health -= damageAmount;
        
        if (health <= 0 && stage == 1)
        {
            stage ++;
            health = 25.0;
            sprite = sprite2;
        }
    }
}