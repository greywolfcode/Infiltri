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
}