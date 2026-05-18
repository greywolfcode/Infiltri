package Game;

import java.util.HashMap;
import java.util.Map;

public enum RoomType
{
    SHOP,
    REST,
    BATTLE,
    BOSS,
    EMPTY;
    
    private static RoomType[] vals = values();
    
    private static HashMap<RoomType, Double> weights = new HashMap<>(
        Map.ofEntries(
                Map.entry(REST, 0.15),
                Map.entry(SHOP, 0.15),
                Map.entry(BATTLE, 0.3),
                Map.entry(BOSS, 0.0), //never can be selected;
                Map.entry(EMPTY, 0.0) //can never be selected
            )    
        );
    
    public static RoomType randType()
    {
        //ensure BOSS and EMPTY (weight 0) cannot be selected
        // by offsetting by 0.1
        double r = Math.random() + 0.1;
        double count = 0.1; 
        
        for (RoomType type:vals)
        {
            r += weights.get(type);
            if (count >= r)
            {
                return type;
            }
        }
        return EMPTY;
    }
}