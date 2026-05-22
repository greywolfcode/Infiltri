package Game.Effects;

import Game.Effect;
import Game.Unit;

public class Poisin extends Effect
{
    private double time;
    private int damage = 5;
    private int prevRoundNum;
    
    public Poisin(int duration)
    {
        time = 2.5 * duration;
        prevRoundNum = 0;
    }
    public void apply(Unit unit, int roundNum)
    {
        unit.damage(damage);
        
        if (roundNum > prevRoundNum)
        {
            time --;
            roundNum = prevRoundNum;
        }
        
        if (time <= 0)
        {
            remove(unit);
        }
    }
    public void remove(Unit unit)
    {
        unit.removeEffect(this);
    }
}