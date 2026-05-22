package Game.Effects;

import Game.Effect;
import Game.Unit;

public class Stunned extends Effect
{
    public void apply(Unit unit, int roomNum)
    {
        unit.lock();
        remove(unit);
    }
    public void remove(Unit unit)
    {
        unit.removeEffect(this);
    }
}