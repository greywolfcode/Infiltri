package Game.Units;

import Game.Attack;
import Game.SpriteHandeler;
import Game.Unit;

import Game.Attacks.Arrow;
import Game.Attacks.Slash;

public class Ranger extends Unit
{
    public Ranger()
    {
        sprite = SpriteHandeler.getUnit("ranger");
        health = 50.0;
        level = 1;
        
        attacks = new Attack[]{new Arrow(), new Slash(), Attack.getRandAttack()};
    }
}