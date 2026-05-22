package Game.Attacks;

import java.util.ArrayList;
import java.util.HashSet;

import Game.Attack;
import Game.AttackMod;
import Game.Effect;
import Game.Damage;

public class Arrow extends Attack
{
    private double baseDamage = 1;
    
    public Damage attack(HashSet<AttackMod> modifiers, int level)
    {
        Arraylist<Effect> effects = Effect.getEffects(modifiers, level);
        
        double damage = Attack.getDamage(baseDamage, modifiers);
        
        return new Damage(baseDamage * level * 1.5, effects);
    }
}