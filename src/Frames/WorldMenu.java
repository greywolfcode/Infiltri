package Frames;

import Game.Data;

public class WorldMenu extends Frame
{
    private int selectedArea = 0;
    private int selectedLevel = 1;
    
    private int currentArea = 0;
    private int currentLevel = 1;
    
    //Look up tables for where area points are located
    
    private int[][] levelOneOffsets = {
        {-7, -7},
        {-10, 0},
        {-7, 7},
        {0, 10},
        {7, 7},
        {10, 0},
        {7, -7},
        {0, -10},
    };
    private int[][] levelTwoOffsets = {
        {0, 5},
        {5, 0},
        {0, -5},
        {-5, 0},
    };
    
    public WorldMenu()
    {
        currentLevel = Data.getCurrentAreaLevel();
        currentArea = Data.getCurrentAreaNum();
        
        selectedLevel = currentLevel;
        selectedArea = currentArea - 1;
        
        //make sure selectedArea is in bounds
        if (selectedArea < 0)
        {
            if (currentLevel == 1)
            {
                selectedArea = 7;
            }
            else
            {
                selectedArea = 3;
            }
        }
    }
    public boolean eventHandeler(String event)
    {
        //handle events not involving changing levels
        switch (event)
        {
            case "":
                Data.setCurrentAreaLevel(selectedLevel);
                Data.setCurrentAreaNum(selectedArea);
                Data.pushEvent(new String[]{"switch", "AreaMenu"});
                return true;
        }
        
        //handle changing levels
        boolean ret;
        if (currentLevel == 1)
        {
            ret = levelOneHandeler(event);
        }
        else
        {
            ret = levelTwoHandeler(event);
        }

        render();
        return ret;
    }
    private boolean levelOneHandeler(String event)
    {
        switch(event)
        {
            //Even numbered areas cannot move up a level
            case "w":
                if (currentArea % 2 == 0)
                {
                    if (selectedArea == currentArea - 1 || (selectedArea == 7 && currentArea == 0))
                    {
                        selectedArea = currentArea + 1;
                    }
                    else 
                    {
                        selectedArea = currentArea - 1;
                    }
                }
                else
                {
                    if (selectedLevel == currentLevel + 1)
                    {
                        selectedLevel--;
                        selectedArea = currentArea - 1;
                    }
                    else if (selectedArea == currentArea - 1 || (selectedArea == 7 && currentArea == 0))
                    {
                        selectedArea = currentArea + 1;
                    }
                    else
                    {
                        selectedLevel++;
                        /* this is the line that goes through
                           (0, 7), (5, 1), (3, 2), (1, 3)
                           using level one as x and level two as y
                        */
                        selectedArea = (int)(-0.5*currentArea + 3.5);
                    }
                }
                break;
            case "s":
                if (currentArea % 2 == 0)
                {
                    if (selectedArea == currentArea + 1 || (selectedArea == 0 && currentArea == 7))
                    {
                        selectedArea = currentArea - 1;
                    }
                    else
                    {
                        selectedArea = currentArea + 1;
                    }
                }
                else
                {
                    if (selectedLevel == currentLevel + 1)
                    {
                        selectedLevel--;
                        selectedArea = currentArea + 1;
                    }
                    else if (selectedArea == currentArea + 1 || (selectedArea == 0 && currentArea == 7))
                    {
                        selectedArea = currentArea - 1;
                    }
                    else
                    {
                        selectedLevel++;
                        selectedArea = (int)(-0.5*currentArea + 3.5);
                    }
                }
                break;
        }
        //ensure selected area is in bounds
        if (selectedArea < 0)
        {
            selectedArea = 7;
        }
        else if (selectedArea > 7)
        {
            selectedArea = 0;
        }
        return true;
    }
    private boolean levelTwoHandeler(String event)
    {
        switch (event)
        {
            case "w":
            {
                if (selectedLevel == currentLevel + 1)
                {
                    selectedLevel--;
                    selectedArea = currentArea - 1;
                }
                else if (selectedArea == currentArea - 1 || (selectedArea == 3 && currentArea == 0))
                {
                    selectedArea = currentArea + 1;
                }
                else
                {
                    selectedLevel++;
                    selectedArea = 0;
                }
                break;
            }
            case "s":
            {
                if (selectedLevel == currentLevel + 1)
                {                         
                    selectedLevel--;
                    selectedArea = currentArea + 1;
                }
                else if (selectedArea == currentArea + 1 || (selectedArea == 0 && currentArea == 3))
                {                         
                    selectedArea = currentArea - 1;
                }
                else                     
                {                         
                    selectedLevel++;
                    selectedArea = 0;
                }
                break;
            }
        }
        //ensure selected area is in bounds
        if (selectedArea < 0)
        {
            selectedArea = 3;
        }
        else if (selectedArea > 3)
        {
            selectedArea = 0;
        }
        return true;
    }
    private void render()
    {
        //draw area bounds
        Data.getWindow().drawCircle(32, 16, 5, 255, 255, 255);
        Data.getWindow().drawCircle(32, 16, 10, 255, 255, 255);
        
        //draw final room point
        Data.getWindow().drawPoint(32, 16, 255, 0, 0);
        
        //draw level two points
        Data.getWindow().drawPoint(32, 16+5, 255, 165, 0);
        Data.getWindow().drawPoint(32, 16-5, 255, 165, 0);
        Data.getWindow().drawPoint(32+5, 16, 255, 165, 0);
        Data.getWindow().drawPoint(32-5, 16, 255, 165, 0);
        
        //draw level one points
        Data.getWindow().drawPoint(32, 16+10, 0, 255, 0);
        Data.getWindow().drawPoint(32+7, 16+7, 0, 255, 0);
        Data.getWindow().drawPoint(32+10, 16, 0, 255, 0);
        Data.getWindow().drawPoint(32+7, 16-7, 0, 255, 0);
        Data.getWindow().drawPoint(32, 16-10, 0, 255, 0);
        Data.getWindow().drawPoint(32-7, 16-7, 0, 255, 0);
        Data.getWindow().drawPoint(32-10, 16, 0, 255, 0);
        Data.getWindow().drawPoint(32-7, 16+7, 0, 255, 0);
        
        //render current player position
        renderPlayer(currentLevel, currentArea, 0, 0, 128);
        
        //render selected position to move to
        renderPlayer(selectedLevel, selectedArea, 0, 127, 255);
    }
    private void renderPlayer(int level, int area, int r, int g, int b)
    {
        if (level == 1)
        {
            Data.getWindow().drawPoint(32+levelOneOffsets[area][0], 16+levelOneOffsets[area][1], r, g, b);
        }
        else if (level == 2)
        {
            Data.getWindow().drawPoint(32+levelTwoOffsets[area][0], 16+levelTwoOffsets[area][1], r, g, b);
        }
        else
        {
            Data.getWindow().drawPoint(32, 16, r, g, b);
        }
        
    }
}