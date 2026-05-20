package Game;

import java.util.HashMap;
import java.util.Map;

import Game.Bosses.RoboBoss;

import Game.Enemies.Amalgum;
import Game.Enemies.DummyEnemy;
import Game.Enemies.EvilCarrot;
import Game.Enemies.ScaryTriangle;
import Game.Enemies.SmallSnail;
import Game.Enemies.Snail;
import Game.Enemies.Spider;
import Game.Enemies.Turtle;

public class EnemyGenerator 
{
    private enum LevelOneEnemy
    {
        EVIL_CARROT,
        SMALL_SNAIL,
        SPIDER,
        TURTLE
    }
    private enum LevelTwoEnemy
    {
        AMALGUM,
        SCARY_TRIANGLE,
        SNAIL,
        EVIL_CARROT,
        SPIDER
    }
    
    private static HashMap<LevelOneEnemy, Double> levelOneWeights = new HashMap<>(
        Map.ofEntries(
                Map.entry(LevelOneEnemy.EVIL_CARROT, 0.25),
                Map.entry(LevelOneEnemy.SMALL_SNAIL, 0.25),
                Map.entry(LevelOneEnemy.SPIDER, 0.25),
                Map.entry(LevelOneEnemy.TURTLE, 0.25)
            )
        );
    
    private static HashMap<LevelTwoEnemy, Double> levelTwoWeights = new HashMap<>(
        Map.ofEntries(
                Map.entry(LevelTwoEnemy.AMALGUM, 0.1),
                Map.entry(LevelTwoEnemy.SCARY_TRIANGLE, 0.3),
                Map.entry(LevelTwoEnemy.SNAIL, 0.2),
                Map.entry(LevelTwoEnemy.EVIL_CARROT, 0.2),
                Map.entry(LevelTwoEnemy.SPIDER, 0.2)
            )
        );
    
    private EnemyGenerator(){}
    
    public static Enemy getEnemy(int level)
    {
        if (level == 1)
        {
            return getEnemyLevelOne();
        }
        else if (level == 2)
        {
            return getEnemyLevelTwo();
        }
        else //level == 3
        {
            return getBoss();
        }
    }
    private static Enemy getEnemyLevelOne()
    {
        double r = Math.random();
        double count = 0.0;
        
        LevelOneEnemy selectedEnemy = null;
        
        for (LevelOneEnemy enemy:LevelOneEnemy.values())
        {
            count += levelOneWeights.get(enemy);
            if (count >= r)
            {
                selectedEnemy = enemy;
                break;
            }
        }
        
        //create and return the enemy object
        switch (selectedEnemy)
        {
            case LevelOneEnemy.EVIL_CARROT:
                return new EvilCarrot(1);
            case LevelOneEnemy.SMALL_SNAIL:
                return new SmallSnail(1);
            case LevelOneEnemy.SPIDER:
                return new Spider(1);
            case LevelOneEnemy.TURTLE:
                return new Turtle(1);
            default:
                return new DummyEnemy(1);
        }
    }
    private static Enemy getEnemyLevelTwo()
    {
        double r = Math.random();
        double count = 0.0;
        
        LevelTwoEnemy currentEnemy = null;
        
        for(LevelTwoEnemy enemy:LevelTwoEnemy.values())
        {
            count += levelTwoWeights.get(enemy);
            if (count >= r)
            {
                currentEnemy = enemy;
                break;
            }
        }
        
        switch (currentEnemy)
        {
            case LevelTwoEnemy.AMALGUM:
                return new Amalgum(2);
            case LevelTwoEnemy.SCARY_TRIANGLE:
                return new ScaryTriangle(2);
            case LevelTwoEnemy.SNAIL:
                return new Snail(2);
            case LevelTwoEnemy.EVIL_CARROT:
                return new EvilCarrot(2);
            case LevelTwoEnemy.SPIDER:
                return new Spider(2);
            default:
                return new DummyEnemy(2);
        }
    }
    private static Enemy getBoss()
    {
        return new RoboBoss();
    }
}