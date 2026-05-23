package Game.Units;

import Game.Attack;
import Game.SpriteHandeler;
import Game.Unit;

import Game.Attacks.FireOrb;
import Game.Attacks.PoisinSpray;

public class Wizard extends Unit
{
    public Wizard()
    {
        sprite = SpriteHandeler.getUnit("wizard");
        health = 50.0;
        level = 1;
        
        attacks = new Attack[]{new FireOrb(), new PoisinSpray(), Attack.getRandAttack()};
    }
}