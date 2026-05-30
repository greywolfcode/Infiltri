package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

public class SmallSnail extends Enemy
{
    public SmallSnail(int lvl)
    {
        health = 10;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("snail_small");
        attacks = new Attack[]{Attack.getRandAttack()};
    }
    public SmallSnail(int lvl, Attack[] pAttacks)
    {
        health = 10;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("snail_small");
        attacks = pAttacks;
    }
    public String getName()
    {
        return name + " the Snail";
    }
    public String toString()
    {
        return "small_snail";
    }
}