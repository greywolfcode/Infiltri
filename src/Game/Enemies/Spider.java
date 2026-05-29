package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.PoisinSpray;
import Game.Attacks.Slash;

public class Spider extends Enemy
{
    public Spider(int lvl)
    {
        health = 20;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("spider");
        attacks = new Attack[]{new PoisinSpray(), new Slash(), Attack.getRandAttack()};
    }
    public Spider(int lvl, Attack[] pAttacks)
    {
        health = 20;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("spider");
        attacks = pAttacks;
    }
    public String toString()
    {
        return "spider";
    }
}