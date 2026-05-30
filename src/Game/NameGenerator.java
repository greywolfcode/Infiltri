package Game;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class NameGenerator 
{
    private static ArrayList<String> names;
    private static ArrayList<String> prefixes;
    private static ArrayList<String> postfixes;
    
    public static void init()
    {
        names = new ArrayList<>();
        prefixes = new ArrayList<>();
        postfixes = new ArrayList<>();
        
        loadFile("Data/names.txt", names);
        loadFile("Data/prefixes.txt", prefixes);
        loadFile("Data/postfixes.txt", postfixes);
    }
    
    public static String genName()
    {
        String output = getFirstName();
        
        //25% chance for prefix
        if (Math.random() > 0.75)
        {
            int prefixNum = (int)(Math.random() * prefixes.size());
            output = prefixes.get(prefixNum) + " " + output;
        }
        
        String postfix = "";
        //25% chance for postfix
        if (Math.random() > 0.75)
        {
            int postfixNum = (int)(Math.random() * postfixes.size());
            output += " " + postfixes.get(postfixNum);
        }
        
        return output;
    }
    public static String genBossName()
    {
        return "King " + getFirstName() + " the Destroyer";
    }
    
    private static String getFirstName()
    {
        int nameNum = (int)(Math.random() * names.size());
        return names.get(nameNum);
    }
    
    private static void loadFile(String path, ArrayList<String> storage)
    {
        try(Scanner file = new Scanner(new File(path)))
        {
            while (file.hasNext())
            {
                storage.add(file.nextLine());
            }
        }
        catch (IOException e)
        {
            
        }
    }
}