package SmallJson;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.HashMap;

public class Parser 
{
    private String data;
    private int pos;

    public Parser(String pData)
    {
        data = pData;
        
        //remove whitespace
        data.replace(" ", "");
        data.replace("\n", "");
        data.replace("\t", "");
        
        pos = 0;
    }
    
    public Node parse()
    {
        return runParser();
        
    }
    private String peek()
    {
        return data.substring(pos, pos+1);
    }
    private String get()
    {
        pos++;
        return data.substring(pos-1, pos);
    }
    private Node runParser()
    {
        String key;
        
        while (pos < data.length())
        {
            //check next token
            switch (get())
            {
                case "{":
                    return parseObject();
                case "[":
                    return parseArray();
                case "\"":
                    return parseString();
                default:
                    return parsePrimative();
            }
        }
        
        return new Node(null);
    }
    private Node parseObject()
    {
        HashMap<String, Object> vals = new HashMap<>();
        StringBuilder token = new StringBuilder();
        
        while (pos < data.length())
        {
            token.append(get());
            
            if (peek() == ":")
            {
                pos++; //skip over ":""
                
                String key = token.toString();
                Object val = runParser();
                
                vals.put(key, val);
            }
            else if (peek() == "}")
            {
                pos++;
                return new Node(vals);
            }
        }
        return new Node(vals);
    }
    private Node parseArray()
    {
        ArrayList<Object> vals = new ArrayList<>();

        while (pos < data.length())
        {
            vals.add(runParser());
            
            if (peek() == "]")
            {
                pos++;
                return new Node(vals);
            }
        }
        return new Node(vals);
    }
    private Node parseString()
    {
        StringBuilder token = new StringBuilder();
        
        if (peek() == "\"")
        {
            return new Node("");
        }
        
        while (pos < data.length())
        {
            token.append(data.substring(pos, pos+1));
            
            if (peek() == "\"")
            {
                pos++;
                return new Node(token.toString());
            }
        }
        return new Node(token.toString());
    }
    private Node parsePrimative()
    {
        StringBuilder token = new StringBuilder();
        
        while (pos < data.length())
        {
            token.append(get());
            
            switch (token.toString())
            {
                case "null":
                    return new Node(null);
                case "true":
                    return new Node (Boolean.TRUE);
                case "false":
                    return new Node(Boolean.FALSE);
                default:
                    if (isNum(token.toString()))
                    {
                        //next value is not a number- this number ends
                        if (!isNum(peek()))
                        {
                            return new Node(getNum(token.toString()));
                        }
                    }
            }
        }
        return new Node(null);
    }
    private boolean isNum(String val)
    {
        try
        {
            Double.parseDouble(val);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    private double  getNum(String val)
    {
        return Double.parseDouble(val);
    }
}