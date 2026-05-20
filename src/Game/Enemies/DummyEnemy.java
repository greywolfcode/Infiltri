package Game.Enemies;

import Game.Enemy;

import Graphics.Surface;

public class DummyEnemy extends Enemy
{
    public DummyEnemy(int lvl)
    {
        health = 1.0;
        level = lvl;
        sprite = genSprite();
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
}