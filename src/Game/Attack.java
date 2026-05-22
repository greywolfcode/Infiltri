package Game;

import java.util.HashSet;
import java.util.ArrayList;

import Game.Attacks.Arrow;
import Game.Attacks.Bash;
import Game.Attacks.FireOrb;
import Game.Attacks.PoisinSpray;
import Game.Attacks.Slash;

public abstract class Attack 
{
    public abstract Damage attack(HashSet<AttackMod> modifiers, int level);
    
    public static double getDamage(double baseDamage, HashSet<AttackMod> modifiers)
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
    public static Attack getRandAttack()
    {
        double r = Math.random();
        
        if (r  < 0.2)
        {
            return new Arrow();
        }
        else if ( r < 0.4)
        {
            return new Bash();
        }
        else if (r < 0.6)
        {
            return new FireOrb();
        }
        else if (r < 0.8)
        {
            return new PoisinSpray();
        }
        else
        {
            return new Slash();
        }
    }
    public static Attack[] getAllAttacks()
    {
        return new Attack[]{new Arrow(), new Bash(), new FireOrb(), new PoisinSpray(), new Slash()};
    }
}