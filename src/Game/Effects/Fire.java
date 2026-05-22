package Game.Effects;

import Game.Effect;
import Game.Unit;

public class Fire extends Effect
{
    private double damage;
    private double time;
    private int prevRoundNum;
    
    public Fire()
    {
        damage = 1.0;
        time = 1.5;
        prevRoundNum = 0;
    }
    public Fire(double multiplier)
    {
        damage = multiplier;
        time = 1.5 * multiplier;
        prevRoundNum = 0;
    }
    
    public void apply(Unit unit, int roundNum)
    {
        unit.damage(damage);
        
        if (roundNum > prevRoundNum)
        {
            time--;
            prevRoundNum = roundNum;
        }
        
        //remove when time is up
        if (time <= 0)
        {
            remove(unit);
        }
    }
    
    public void remove(Unit unit)
    {
        unit.removeEffect(this);
    }
    public String toString()
    {
        String desc ="Fire: does " + damage + " damage for " + time + " turns";
        return desc;
    }
}