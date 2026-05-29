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
    
    public static Node read(String path)
    {
        return read(Path.of(path));
    }
    public static Node read(Path path)
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
    public static void write(JsonBuilder builder, String path)
    {
        StringBuilder output = new StringBuilder();
        
        writeJson(builder, output, 1);
        
        output.append("\n}");
        
        try (PrintWriter file = new PrintWriter(new FileWriter(path)))
        {
            file.print(output);
        }
        catch (IOException e)
        {
            
        }
    }
    private static void writeJson(JsonBuilder builder, StringBuilder output, int indentAmount)
    {
        String indent = "    ".repeat(indentAmount); //4 space indentation
        
        output.append("{\n");
        
        for (Map.Entry<String, Node> entry:builder)
        {
            output.append(indent + entry.getKey() + ": ");
            Node val = entry.getValue();
            
            if (val.isBuilder())
            {
                writeJson(val.getAsBuilder(), output, indentAmount + 1);
                output.append("\n" + indent + "},\n");
            }
            else if (val.isNull())
            {
                output.append("null,\n");
            }
            else if (val.isString())
            {
                output.append("\"" + val.getAsString() + "\",\n");
            }
            else if (val.isBoolean())
            {
                output.append(val.getAsBoolean() + ",\n");
            }
            else if (val.isDouble())
            {
                output.append(val.getAsDouble() + ",\n");
            }
        }
        //remove trailing comma, which is on previous line
        output.setLength(output.length() - 2);
    }
}