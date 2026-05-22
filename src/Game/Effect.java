package Game;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Effects.Fire;
import Game.Effects.Poisin;
import Game.Effects.Stunned;

public abstract class Effect 
{
    public abstract void apply(Unit unit, int roundNum);
    public abstract void remove(Unit unit);
    
    public static ArrayList<Effect> getEffect(HashSet<AttackMod> modifiers, int level)
    {
        ArrayList<Effect> effects = new ArrayList<>();
        
        for (AttackMod mod:modifiers)
        {   
            switch (mod)
            {
                case AttackMod.FIRE:
                    effects.add(new Fire(level));
                    break;
                case AttackMod.POISIN:
                    effects.add(new Poisin(level));
                    break;
                case AttackMod.STUN:
                    effects.add(new Stunned());
                    break;
            }
        }
        
        return effects;
    }
}