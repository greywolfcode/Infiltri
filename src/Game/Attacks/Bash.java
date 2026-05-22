package Game.Attacks;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Attack;
import Game.AttackMod;
import Game.Effect;
import Game.Damage;

import Game.Effects.Stunned;

public class Bash extends Attack
{
    private double baseDamage = 2.5;
    private double baseStun = 0.25;
    
    public Damage attack(HashSet<AttackMod> modifiers, int level)
    {
        //use modifiers to add effects
        ArrayList<Effect> effects = Effect.getEffect(modifiers, level);
    
        double damage = Attack.getDamage(baseDamage, modifiers);
        
        
        //Only apply stun if allowed to
        if (!(modifiers.contains(AttackMod.NO_STUN)))
        {
            double chance = Math.random();
            if (chance < baseStun * level)
            {
                effects.add(new Stunned());
            }
        }
        
        return new Damage(damage * level, effects);
    }
    public String toString()
    {
        return "Bash";
    }
}