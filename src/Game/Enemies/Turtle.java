package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.Bash;

public class Turtle extends Enemy
{
    public Turtle(int lvl)
    {
        health = 25.0;
        level  = lvl;
        sprite = SpriteHandeler.getEnemy("turtle");
        attacks = new Attack[]{new Bash(), Attack.getRandAttack()};
    }
    public Turtle(int lvl, Attack[] pAttacks)
    {
        health = 25.0;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("turtle");
        attacks = pAttacks;
    }
    public String getName()
    {
        return name + " the Turtle";
    }
    public String toString()
    {
        return "turtle";
    }
}