package SmallJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.PrintWriter;
import java.io.FileWriter;
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
    public void write(JsonBuilder builder, String path)
    {
        try (PrintWriter file = new PrintWriter(new FileWriter(path)))
        {
            writeJson(builder, 0, file)
            
            //remove trailing comman from previous line
            file.println("\b\b");
        }
        catch (IOException e)
        {
            
        }
    }
    private void writeJson(JsonBuilder builder, int indent, PrintWriter file)
    {
        String indent = "    ".repeat(indent); //4 space indentation
        
        file.println("{");
        
        for (Map.entry<String, Node> entry:builder)
        {
            file.print(indent + entry.getkey() + ": ")
            Node val = entry.getValue();
            
            if (val.isBuilder())
            {
                writeJson(Node.getAsBuilder(), indent + 1, file)
            }
            else if (val.isNull())
            {
                file.println("null,");
            }
            else if (val.isString)
            {
                file.println("\"" + val.getAsString() + "\",");
            }
            else if (val.isBoolean())
            {
                file.println(val.getAsBoolean() + ",")
            }
            else if (val.isDouble())
            {
                file.println(val.getAsDouble() + ",")
            }
        }
        //remove trailing comma, which is on previous line
        file.println("\b\b");
        file.println(indent + "},");
    }
}