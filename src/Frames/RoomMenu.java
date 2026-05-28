package Frames;

import java.util.ArrayList;

import Game.Attack;
import Game.Damage;
import Game.Data;
import Game.Enemy;
import Game.Room;
import Game.Unit;

import Graphics.UI.RadioButton;

public class RoomMenu extends Frame
{
    private Room room;
    int roundNum;
    
    private RadioButton attackOptions;
    
    public RoomMenu()
    {
        room = Data.getCurrentRoom();
        roundNum = 0;
        
        attackOptions = new RadioButton();
        
        Attack[] attacks = Data.getParty().get(0).getAttacks();
        for (int i = 0; i < attacks.length; i++)
        {
            Attack attack = attacks[i];
            attackOptions.addButton("\u25ba", attack.toString(), 1, 36+i);
        }
    }
    public boolean eventHandeler(String event) 
    {
        if (room.getCleared())
        {
            cleared(event);
            return true;
        }
        
        Unit player = Data.getParty().get(0);
        Unit enemy = room.getEnemy();
        
        player.applyEffects(roundNum);
        enemy.applyEffects(roundNum);
        
        switch (event)
        {
            case "w":
                attackOptions.move(-1);
                break;
            case "s":
                attackOptions.move(1);
                break;
            case "victoryIsWithinGrasp":
                win();
                break;
            case "unavoidableDefeat":
                lose();
                break;
            case "hurryUpAndWinAlready":
                Data.pushEvent(new String[]{"switch", "GameWonMenu"});
            case "":
                int selected = attackOptions.getSelectedButton();
                Damage playerMove = player.getDamage(selected);
                Damage enemyMove = enemy.getRandDamage();
                
                enemy.damage(playerMove);
                
                if (enemy.getHealth() <= 0)
                {
                    win();
                    break;
                }
                
                player.damage(enemyMove);
                
                if (player.getHealth() <= 0)
                {
                    lose();
                    break;
                }
                
                //regen enemy every other turn
                if (roundNum % 2 == 0)
                {
                    player.regen();
                    enemy.regen();
                }
                
                break;
        }
        
        render();
        roundNum++;
        return true;
    }
    private void cleared(String event)
    {
        switch (event)
        {
            case "":
                Data.pushEvent(new String[]{"switch", "AreaMenu"});
                break;
        }
        
        //blit player sprite         
        ArrayList<Unit> units = Data.getParty();         
        Unit main = units.get(0);         
        Data.getWindow().blit(main.getSprite(), 5, 32-main.getSprite().getHeight());
        
        //draw selection text
        Data.getWindow().writeText("No one is here...", 34, 16, 255, 255, 255);
        Data.getWindow().writeText("\u25ba Continue", 36, 18, 255, 255, 255);
        
        //draw action bar
        Data.getWindow().writeText("Enter: select", 1, 35, 255, 255, 255);
    }
    private void win()
    {
        //handle boss
        if (room.getLevel() == 3)
        {
            Data.pushEvent(new String[]{"switch", "GameWonMenu"});
            return;
        }
        
        Data.getParty().get(0).regen();
        room.setCleared(true);
        Data.pushEvent(new String[]{"switch", "WonMenu"});
    }
    private void lose()
    {
        Data.pushEvent(new String[]{"switch", "LoseMenu"});
    }
    private void render()
    {
        Data.getWindow().blit(room.getBackground(), 0, 0);
        
        //blit player sprite
        ArrayList<Unit> units = Data.getParty();
        Unit main = units.get(0);
        Data.getWindow().blit(main.getSprite(), 5, 32-main.getSprite().getHeight());
        
        //blit enemy sprite
        Enemy enemy = room.getEnemy();
        Data.getWindow().blit(enemy.getSprite(), 64-1-enemy.getSprite().getWidth(), 32-enemy.getSprite().getHeight());
        
        //blit action bar
        Data.getWindow().writeText(main.getHealthPercent(), 1, 35, 255, 255, 255);
        Data.getWindow().writeText(enemy.getHealthPercent(), 64-1-enemy.getHealthPercent().length(), 35, 255, 255, 255);
        
        attackOptions.render(Data.getWindow(), 255, 255, 255);
    }
}