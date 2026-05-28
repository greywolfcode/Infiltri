package Game.Attacks;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Attack;
import Game.AttackMod;
import Game.Damage;
import Game.Effect;

import Game.Effects.Fire;

/**
 * Low damage, high fire attack
 */ 
public class FireOrb extends Attack
{
    double baseDamage = 2;
    
    public Damage attack(HashSet<AttackMod> modifiers, int level)
    {
        ArrayList<Effect> effects = Effect.getEffect(modifiers, level);
        
        
        if (!(modifiers.contains(AttackMod.NO_FIRE)))
        {
            effects.add(new Fire(level * 2));
        }
        
        return new Damage(baseDamage, effects);
    }
    public String toString()
    {
        return "Fire Orb";
    }
}