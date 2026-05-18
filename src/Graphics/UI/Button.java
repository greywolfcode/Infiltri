package Graphics.UI;

public class Button 
{
    private String symbol;
    private String label;
    private int x;
    private int y;
    
    public Button(String pSymbol, String pLabel, int pX, int pY)
    {
        symbol = pSymbol;
        label = pLabel;
        x = pX;
        y = pY;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public String getLabel()
    {
        return label;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setSymbol(String pSymbol)
    {
        symbol = pSymbol;
    }
    public void setLabel(String pLabel)
    {
        label = pLabel;
    }
    public void setX(int pX)
    {
        x = pX;
    }
    public void setY(int pY)
    {
        y = pY;
    }
}