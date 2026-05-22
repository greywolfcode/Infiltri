package Game.Attacks;

import java.util.ArrayList;
import java.util.HashMap;

import Game.Attack;
import game.AttackMod;
import Game.Damage;
import Game.Effect;

public class PoisinSpray extends Attack
{
    private double baseDamage = 2.5;
    
    public Damage attack(HashMap<AttackMod> modifiers, int level)
    {
        ArrayList<Effect> effects = Effect.getEffects(modifiers, level);
        double damage = Attack.getDamage(baseDamage, modifiers);
        
        if (!(modifier.contains(AttackMod.NO_POISIN)))
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
}