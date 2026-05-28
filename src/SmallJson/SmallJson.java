package SmallJson;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.Path;

public class SmallJson 
{
    private SmallJson(){}
    
    public ArrayList<Node> read(String path)
    {
        return read(Path.of(path));
    }
    public ArrayList<Node> read(Path path)
    {
        try (String data = Files.readString(path))
        {
            Parser p = new Parser(Data);
            return p.parse();
        }
        catch (IOException e)
        {
            
        }
    }
}