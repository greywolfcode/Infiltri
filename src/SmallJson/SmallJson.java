package SmallJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

public class SmallJson 
{
    
    private SmallJson(){}
    
    public Node read(String path)
    {
        return read(Path.of(path));
    }
    public Node read(Path path)
    {
        try
        {
            String data = Files.readString(path); 
            Parser p = new Parser(data);
            return p.parse();
        }
        catch (IOException e)
        {
            return new Node(null);
        }
    }
    public void write(JsonBuilder builder, String path)
    {
        try (PrintWriter file = new PrintWriter(new FileWriter(path)))
        {
            writeJson(builder, 0, file);
            
            //remove trailing comman from previous line
            file.println("\b\b");
        }
        catch (IOException e)
        {
            
        }
    }
    private void writeJson(JsonBuilder builder, int indentAmount, PrintWriter file)
    {
        String indent = "    ".repeat(indentAmount); //4 space indentation
        
        file.println("{");
        
        for (Map.Entry<String, Node> entry:builder)
        {
            file.print(indent + entry.getKey() + ": ");
            Node val = entry.getValue();
            
            if (val.isBuilder())
            {
                writeJson(val.getAsBuilder(), indentAmount + 1, file);
            }
            else if (val.isNull())
            {
                file.println("null,");
            }
            else if (val.isString())
            {
                file.println("\"" + val.getAsString() + "\",");
            }
            else if (val.isBoolean())
            {
                file.println(val.getAsBoolean() + ",");
            }
            else if (val.isDouble())
            {
                file.println(val.getAsDouble() + ",");
            }
        }
        //remove trailing comma, which is on previous line
        file.println("\b\b");
        file.println(indent + "},");
    }
}