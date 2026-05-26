package Game.Attacks;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Attack;
import Game.AttackMod;
import Game.Damage;
import Game.Effect;

import Game.Effects.Poisin;

public class PoisinSpray extends Attack
{
    private double baseDamage = 2.5;
    
    public Damage attack(HashSet<AttackMod> modifiers, int level)
    {
        ArrayList<Effect> effects = Effect.getEffect(modifiers, level);
        double damage = Attack.getDamage(baseDamage, modifiers);
        
        if (!(modifiers.contains(AttackMod.NO_POISIN)))
        {
            effects.add(new Poisin(level));
            
            //double poisin if level is over four
            if (level > 4)
            {
                effects.add(new Poisin(level/2));
            }
        }
        
        return new Damage(damage, effects);
    }
    public String toString()
    {
        return "Poisin Spray";
    }
}