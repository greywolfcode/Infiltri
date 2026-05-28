package SmallJson;

import java.util.HashMap;

public class JsonBuilder 
{
    HashMap<String, Node> values;
    
    
    public JsonBuilder()
    {
        values = new HashMap<>();
    }
    public JsonBuilder(HashMap<String, Node> val)
    {
        values = val; 
    }
    
    public void addNull(String key)
    {
        values.add(key, null);
    }
    public void addNumber(String key, Double num)
    {
        values.add(key, new Node(num));
    }
    public void addBoolean(String key, Boolean bool)
    {
        values.add(key, new Node(bool));
    }
    public void addString(String key, String str)
    {
        values.add(key, new Node(str));
    }
    public JsonBuilder addObject(String key)
    {
        JsonBuilder obj = new JsonBuilder();
        values.add(key, new Node(obj));
        return obj;
    }
    public JsonBuilder addObject(String key, JsonBuilder obj)
    {
        values.add(key, new Node(obj));
        return obj;
    }
}