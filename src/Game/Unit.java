package Game;

import Graphics.Surface;

public abstract class Unit 
{
    protected double health;
    protected int level;
    protected Surface sprite;
    
    public void damage(double damageAmount)
    {
        health -= damageAmount;
    }
    public Surface getSprite()
    {
        return sprite;
    }
}