package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.Slash;

public class Eyeball extends Enemy
{
    public Eyeball(int lvl)
    {
        health = 75;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("eyeball");
        attacks = new Attack[]{new Slash(), Attack.getRandAttack(), Attack.getRandAttack()}
    }
    public String toString()
    {
        return "eyeball";
    }
}