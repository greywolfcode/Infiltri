package Game;

import java.util.HashSet;
import java.util.ArrayList;

public abstract class Attack 
{
    Damage attack(HashSet<AttackMod> modifiers, int level);
    
    public static double getDamage(double baseDamage, ArrayList<AttackMod> modifiers)
    {
        double damage = baseDamage;
        
        for (AttackMod mod:modifiers)
        {
            switch (mod)
            {
                case AttackMod.DOUBLE:
                    damage *= 2;
                case AttackMod.TRIPLE:
                    damage *= 3;
                case AttackMod.ZERO:
                    return 0; //no need to check other modifiers
            }
        }
        return damage;
    }
}