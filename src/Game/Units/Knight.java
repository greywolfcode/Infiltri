package Game.Units;

import Game.Attack;
import Game.Unit;
import Game.SpriteHandeler;

import Game.Attacks.Bash;
import Game.Attacks.Slash;

public class Knight extends Unit
{
    public Knight()
    {
        sprite = SpriteHandeler.getUnit("knight");
        health = 50.0;
        level = 1;
        
        attacks = new Attack[]{new Bash(), new Slash(), Attack.getRandAttack()};
    }
    public Knight(double pHealth, int pLevel, Attack[] pAttacks)
    {
        sprite = SpriteHandeler.getUnit("knight");
        health = pHealth;
        level = pLevel;
        
        attacks = pAttacks;
    }
    public String toString()
    {
        return "knight";
    }
}