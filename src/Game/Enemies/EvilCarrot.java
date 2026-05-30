package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.Arrow;
import Game.Attacks.PoisinSpray;

public class EvilCarrot extends Enemy
{
    public EvilCarrot(int lvl)
    {
        health = 45;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("evil_carrot");
        attacks = new Attack[]{new Arrow(), new PoisinSpray(), Attack.getRandAttack()};
    }
    public EvilCarrot(int lvl, Attack[] pAttacks)
    {
        health = 45;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("evil_carrot");
        attacks = pAttacks;
    }
    public String getName()
    {
        return name + " the Evil Carrot";
    }
    public String toString()
    {
        return "evil_carrot";
    }
}