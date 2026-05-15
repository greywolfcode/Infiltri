package Game;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

import Graphics.Surface;

public class SpriteHandeler 
{
    private SpriteHandeler(){}
    
    private static HashMap<String, Surface> backgrounds = new HashMap<>();
    private static HashMap<String, Surface> enemies = new HashMap<>();
    private static HashMap<String, Surface> units = new HashMap<>();
    
    
    public static void init()
    {
        Path bgPath = Paths.get("Sprites", "backgrounds");
        File bgFolder = bgPath.toFile();
        File[] bgFiles = bgFolder.listFiles();
        
        loadSprites(bgFiles, backgrounds);
        
        Path enemiesPath = Paths.get("Sprites", "enemies");
        File enemiesFolder = enemiesPath.toFile();
        File[] enemiesFiles = enemiesFolder.listFiles();
        
        loadSprites(enemiesFiles, enemies);
        
        Path unitsPath = Paths.get("Sprites", "units");
        File unitsFolder = unitsPath.toFile();
        File[] unitsFiles = unitsFolder.listFiles();
        
        loadSprites(unitsFiles, units);
    }
    private static void loadSprites(File[] files, HashMap<String, Surface> storage)
    {
        for (File file:files)
        {
            String name = file.getName().split(".")[0];
            Surface sprite = new Surface(loadFile(file));
            
            storage.put(name, sprite);
        }
    }
    private static String[][] loadFile(File file)
    {
        ArrayList<String> lines = new ArrayList<>();
        int largestLine = 0;
        
        //load lines
        try (Scanner data = new Scanner(file))
        {
            while (data.hasNext())
            {
                String line = data.nextLine();
                
                if (line.length() > largestLine)
                {
                    largestLine = line.length();
                }
                
                lines.add(line);
            }
        }
        //want program to keep running if there are missing sprites
        catch (IOException e){}
        
        //create array to return
        String[][] output = new String[lines.size()][largestLine];
        
        //add lines to output 
        for (int row = 0; row < lines.size(); row++)
        {
            String line = lines.get(row);
            
            int count = 0; //want to use variable after loop
            for (; count < line.length(); count++)
            {
                output[row][count] = line.substring(count, count+1);
            }
            //add padding
            for (; count < largestLine; count++)
            {
                output[row][count] = " ";
            }
        }
        
        return output;
    }
}