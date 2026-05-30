package Game.Enemies;

import Game.Attack;
import Game.Enemy;
import Game.SpriteHandeler;

import Game.Attacks.FireOrb;
import Game.Attacks.PoisinSpray;

public class ScaryTriangle extends Enemy
{
    public ScaryTriangle(int lvl)
    {
        health = 50.0;
        level = lvl;
        sprite = SpriteHandeler.getEnemy("scary_triangle");
        
        attacks = new Attack[]{new FireOrb(), new PoisinSpray(), Attack.getRandAttack()};
    }
    public ScaryTriangle(int lvl, Attack[] pAttacks)
    {
        health = 50.0;
        level = lvl;
        SpriteHandeler.getEnemy("scary_triangle");
        
        attacks = pAttacks;
    }
    public String getName()
    {
        return name + " the Scary Triangle";
    }
    public String toString()
    {
        return "scary_triangle";
    }
}