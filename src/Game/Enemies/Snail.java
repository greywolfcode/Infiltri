package Game.Enemies;

import Game.Attack;
import Game.Damage;
import Game.Enemy;
import Game.SpriteHandeler;

import Graphics.Surface;

public class Snail extends Enemy
{
    private int stage;
    
    private Surface sprite1;
    private Surface sprite2;
    
    public Snail(int lvl)
    {
        health = 25.0;
        level = lvl;
        stage = 1;
        
        sprite1 = SpriteHandeler.getEnemy("snail_small");
        sprite2 = SpriteHandeler.getEnemy("snail_large");
        sprite = sprite1;
        
        attacks = new Attack[]{Attack.getRandAttack(), Attack.getRandAttack()};
    }
    
    //needs to handle two stages
    @Override
    public void damage(Damage damage)
    {
        health -= damage.amount();
        
        if (doStageChange())
        {
            
        }
        
    }
    @Override public void damage(double amount)
    {
        health -= amount;
        
        if (doStageChange())
        {
            
        }
    }
    private boolean doStageChange()
    {
        if (health <= 0 && stage == 1)
        {
            stage ++;
            health = 25.0;
            sprite = sprite2;
            
            //add two new attacks
            Attack[] old = attacks;
            attacks = new Attack[old.length + 2];
            attacks[0] = old[0];
            attacks[1] = old[1];
            attacks[2] = Attack.getRandAttack();
            attacks[3] = Attack.getRandAttack();
            
            return true;
        }
        return false;
    }
}