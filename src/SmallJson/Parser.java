package SmallJson

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
        currentPos = 0;
    }
    
    public ArrayList<Node> parse()
    {
        ArrayList<Node> output = new ArrayList<>();

        while (pos < data.length())
        {
            output.add(runParser)
        }
        
    }
    private String peek()
    {
        return data.substring(pos, pos+1);
    }
    private Node runParser()
    {
        String key;
        
        while (pos < data.length())
        {
            //check next token
            switch (peek())
            {
                case "{":
                    parseObject();
                    break;
            }
        }
    }
    private return Node parseObject()
    {
        HashMap<String, Object> vals;
        StringBuilder token = new StringBuilder();
        
        while (pos < data.length)
        {
            token.append(data.substring(pos, pos + 1));
            
            if (peek() == ":")
            {
                pos++; //skip over ":""
                
                String key = token.toString();
                Object
                
                
            }
        }
    }
}