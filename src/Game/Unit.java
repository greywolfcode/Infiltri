package Game;

import java.util.HashSet;

import Graphics.Surface;

public abstract class Unit 
{
    protected double health;
    protected double maxHealth;
    protected int level;
    protected Surface sprite;
    protected boolean locked = false;
    protected HashSet<Effect> effects = new HashSet<>();
    protected Attack[] attacks;
    protected HashSet<AttackMod> mods = new HashSet<>();
    
    
    public void init()
    {
        maxHealth = health;
    }
    public void regen()
    {
        if (health >= maxHealth)
        {
            return;
        }
        
        if (health + 2 < maxHealth)
        {
            health += 2;
        }
        else 
        {
            health += maxHealth - health;
        }
    }
    public void maxRegen()
    {
        health = maxHealth;
    }
    public void levelUp()
    {
        level++;
        
        maxHealth *= 2;
        
        maxRegen();
    }
    public double getMaxHealth()
    {
        return maxHealth;
    }
    public String getHealthPercent()
    {
        return (int)(health / maxHealth * 100) + "%";
    }
    public double getHealth()
    {
        return health;
    }
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
    public Attack[] getAttacks()
    {
        return attacks;
    }
    public Damage getDamage(int index)
    {
        return attacks[0].attack(mods, level);
    }
    public Damage getRandDamage()
    {
        int index = (int)(Math.random() * attacks.length);
        return getDamage(index);
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