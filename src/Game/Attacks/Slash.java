package Game.Attacks;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Attack;
import Game.AttackMod;
import Game.Damage;
import Game.Effect;

public class Slash extends Attack
{
    double baseDamage = 7;
    
    public Damage attack(HashSet<AttackMod> modifiers, int level)
    {
        ArrayList<Effect> effects = Effect.getEffect(modifiers, level);
        
        double damage = Attack.getDamage(baseDamage, modifiers);
        
        return new Damage(damage, effects);
    }
    public String toString()
    {
        return "Slash";
    }
}