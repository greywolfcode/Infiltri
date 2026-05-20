package Game;

import Graphics.Surface;

public abstract class Unit 
{
    protected double health;
    protected int level;
    protected Surface sprite;
    
    public void damage(Damage damage)
    {
        health -= damage.amount();
    }
    public Surface getSprite()
    {
        return sprite;
    }
}