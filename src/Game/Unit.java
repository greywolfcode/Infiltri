package Game;

import java.util.HashSet;

import Graphics.Surface;

public abstract class Unit 
{
    protected double health;
    protected int level;
    protected Surface sprite;
    protected boolean locked = false;
    protected HashSet<Effect> effects = new HashSet<>();
    
    public void damage(Damage damage)
    {
        health -= damage.amount();
    }
    public void damage(double amount)
    {
        health -= amount;
    }
    public Surface getSprite()
    {
        return sprite;
    }
    public void addEffect(Effect effect)
    {
        effects.add(effect);
    }
    public void removeEffect(Effect effect)
    {
        effects.remove(effect);
    }
    public void applyEffects(int roundNum)
    {
        for (Effect effect:effects)
        {
            effect.apply(this, roundNum);
        }
    }
    
    /**
     * Prevents further actions from being taken
     */
    public void lock()
    {
        locked = true;
    }
    public void unlock()
    {
        locked = false;
    }
    
}