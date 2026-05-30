package Game.Bosses;

import Game.Attack;
import Game.Enemy;
import Game.NameGenerator;
import Game.SpriteHandeler;

public class RoboBoss extends Enemy
{
    public RoboBoss()
    {
        health = 255.0;
        level = 2048;
        sprite = SpriteHandeler.getBoss("robo_boss");
        attacks = Attack.getAllAttacks();
        
        name = NameGenerator.genBossName();
    }
    public String getName()
    {
        return name;
    }
}