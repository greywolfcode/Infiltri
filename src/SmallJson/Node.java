package SmallJson;

import java.util.ArrayList;
import java.util.HashMap;

public class Node 
{
    private Class<?> type;
    private Object val;
    
    public Node(Class<T> pType, Object pVal)
    {
        type = pType;
        val = pVal;
    }
    public Class<T getType()
    {
        return type;
    }
    public Object getVal()
    {
        return val;
    }
    
    public boolean isNull()
    {
        if (val == null)
        {
            return true;
        }
        return false;
    }
    public boolean isDouble()
    {
        if (isNull())
        {
            return false;
        }
        
        return type == Double.class;
    }
    public boolean isString()
    {
        if (isNull())
        {
            return false;
        }
        
        return type == String.class;
    }
    public boolean isBoolean()
    {
        if (isNull())
        {
            return false;
        }
        
        return type == Boolean.class;
    }
    public boolean isObject()
    {
        if (isNull())
        {
            return false;
        }
        
        return type == HashMap.class;
    }
    public boolean isArray()
    {
        if (isNull())
        {
            return false;
        }
        
        return type == ArrayList.class;
    }
    public Double getAsDouble()
    {
        return Double(val);
    }
    public String getAsString()
    {
        return String(val);
    }
    public Boolean getAsBoolean()
    {
        return Boolean(val);
    }
    public HashMap<String, Node> getAsObject()
    {
        return HashMap<String, Node>(val);
    }
    public ArrayList<Node> getAsArray()
    {
        return ArrayList<Node>(val);
    }
    
}