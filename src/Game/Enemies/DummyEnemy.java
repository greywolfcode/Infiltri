package Game.Enemies;

import Game.Attack;
import Game.Enemy;

import Graphics.Surface;

public class DummyEnemy extends Enemy
{
    public DummyEnemy(int lvl)
    {
        health = 1.0;
        level = lvl;
        sprite = genSprite();
        attacks = new Attack[]{};
    }
    private Surface genSprite()
    {
        String[][] data = {
            {"/", "-", "-", "\\"},
            {"|", " ", " ", "|"},
            {"|", " ", " ", "|"},
            {"\\", "-", "-", "/"}
        };
        
        return new Surface(data);
    }
    public String toString()
    {
        return "dummy_enemy";
    }
}