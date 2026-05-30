package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.Bash;
import Game.Attacks.Slash;

public class Amalgum extends Enemy
{
    public Amalgum(int lvl)
    {
        health = 100;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("amalgum");
        attacks = new Attack[]{new Bash(), new Slash(), Attack.getRandAttack()};
    }
    public Amalgum(int lvl, Attack[] pAttacks)
    {
        health = 100;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("amalgum");
        attacks = pAttacks;
    }
    public String getName()
    {
        return name + " the Amalgum";
    }
    public String toString()
    {
        return "amalgum";
    }
}