package SmallJson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonBuilder implements Iterable<Map.Entry<String, Node>>
{
    private HashMap<String, Node> values;
    
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
        values.put(key, null);
    }
    public void addNumber(String key, Double num)
    {
        values.put(key, new Node(num));
    }
    public void addBoolean(String key, Boolean bool)
    {
        values.put(key, new Node(bool));
    }
    public void addString(String key, String str)
    {
        values.put(key, new Node(str));
    }
    public JsonBuilder addObject(String key)
    {
        JsonBuilder obj = new JsonBuilder();
        values.put(key, new Node(obj));
        return obj;
    }
    public JsonBuilder addObject(String key, JsonBuilder obj)
    {
        values.put(key, new Node(obj));
        return obj;
    }
    
    @Override
    public Iterator<Map.Entry<String, Node>> iterator()
    {
        return values.entrySet().iterator();
    }
}