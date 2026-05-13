package Graphics;

import ConsoleControl.Colour;

/**
 * Class for storing image data to render to the screen
 * 
 * (0, 0) is in the top left corner
 */
public class Surface 
{
    private int width;
    private int height;
    private String[][] surface;
    
    public Surface(int w, int h)
    {
        height = h;
        width = w;
        surface = new String[h][w];
        clear(); //set screen to be blank
    }
    public Surface(String[][] data)
    {
        surface = data;
        height = data.length;
        width = data[0].length;
    }
    // returns a surface that is a copy of the current surface
    public Surface copy()
    {
        return new Surface(surface);
    }
    /**
     * Fill the entire surface with a colour
     */
    public void fill(int r, int g, int b)
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                surface[i][j] = Colour.bgRGB(" ", r, g, b);
            }
        }
    }
    /**
     * Wipe the surface
     */
    public void clear()
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                surface[i][j] = " ";
            }
        }
    }
    /**
     * Set the colour of a single point
     */
    public void drawPoint(int x, int y, int r, int g, int b)
    {
        surface[y][x] = Colour.bgRGB(" ", r, g, b);
    }
    /**
     * Sets all charachters to be the specified colour
     */
    public void setCharColour(int r, int g, int b)
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                surface[y][x] = Colour.rgb(surface[y][x], r, g, b);
            }
        }
    }
    /**
     * Sets the value of a single charachter on the surface
     */
     public boolean setChar(String str, int x, int y)
     {
         //catch out of bounds
         if (isOutOfBounds(x, y))
         {
             return false;
         }
         
         //only allow one charachter
         surface[y][x] = blend(surface[y][x], str.substring(0, 1));
         return true;
     }
     /**
      * Sets the value of a single charachter on the surface, including colour
      */
     public boolean setChar(String str, int x, int y, int r, int g, int b)
     {
         if (isOutOfBounds(x, y))
         {
             return false;
         }
         
         surface[y][x] = blend(surface[y][x], Colour.rgb(str.substring(0, 1), r, g, b));
         return true;
     }
     /**
      * Writes text to the Surface
      */
     public void writeText(String str, int x, int y)
     {
         for (int i = 0; i< str.length(); i++)
         {
             setChar(str.substring(i, i+1), x+i, y);
         }
     }
     /**
      * Writes coloured text to the Surface
      */
     public void writeText(String str, int x, int y, int r, int g, int b)
     {
         for (int i = 0; i< str.length(); i++)
         {
             setChar(str.substring(i, i+1), x+i, y, r, g, b);
         }
     }
     /**
      * Draws a coloured line to the surface
      */
    public void drawLine(int startX, int startY, int endX, int endY, int r, int g, int b)
    {
        //draw one line if start is the same as the end
        if (startX == endX && startY == endY)
        {
            setChar("|", startX, startY, r, g, b);
            return;
        }
        
        double currentX = startX;
        double currentY = startY;
        
        double slope;
        if (startX == endX) //handle division by zero
        {
            if (startY > endY)
            {
                slope = Double.NEGATIVE_INFINITY;
            }
            else
            {
                slope = Double.POSITIVE_INFINITY;
            }
        }
        else
        {
            slope = (double)(endY - startY) / (endX - startX);
        }
        
        if (slope == Double.NEGATIVE_INFINITY)
        {
            while ((int)currentY != endY)
            {
                if (!setChar("|", (int)currentX, (int)currentY, r, g, b))
                {
                    //out of bounds
                    break;
                }
                currentY -= 1;
            }
        }
        else if (slope == Double.POSITIVE_INFINITY)
        {
            while ((int)currentY != endY)
            {
                if (!setChar("|", (int)currentX, (int)currentY, r, g, b))
                {
                    //out of bounds
                    break;
                }
                currentY += 1;
            }
        }
        else
        {
            //get rise and run of unit vector
            double root = Math.sqrt(1 + Math.pow(slope, 2));
            double rise = slope / root;
            double run = 1 / root;
            
            //variables to translate by
            double xTrans = run;
            double yTrans = rise;
            
            //convert translation for proper directions
            if (startY < endY)
            {
                if (yTrans < 0)
                {
                    yTrans *= -1;
                }
            }
            else
            {
                if (yTrans > 0)
                {
                    yTrans *= -1;
                }
            }
            if (startX < endX)
            {
                if (xTrans > 0)
                {
                    xTrans *= -1;
                }
            }
            else
            {
                if (xTrans < 0)
                {
                    xTrans *= -1;
                }
            }
            
            while (((int)currentY != endY) && ((int)currentX != endX))
            {
                //casting to get values as a whole number
                if (!setChar("-", (int)currentX, (int)currentY, r, g, b))
                {
                    //out of bounds
                    break;
                }
                currentX -= xTrans;
                currentY += yTrans;
            }
        }
    }
    /**
     * Draws the surface to the screen
     */
    public void update()
    {
        for (String[] row:surface)
        {
            System.out.println(String.join("", row));
        }
    }
    public void blit(Surface surf, int x, int y)
    {
        String[][] blitObj = surf.getSurfaceData();
        
        if (isOutOfBounds(x, y))
        {
            //not being blit on surface, just return
            return;
        }
        
        // make sure is not out of bounds for either surface
        for (int i = 0; i < blitObj.length && i + y < surface.length; i++)
        {
            for (int j = 0; j < blitObj[0].length && j + x < surface[0].length; j++)
            {
                //totally transparent, no action required
                if (blitObj[i][j].equals(" "))
                {
                    continue;
                }
                surface[y+i][x+j] = blend(surface[y+i][x+j], blitObj[i][j]);
            }
        }
    }
    public String[][] getSurfaceData()
    {
        return surface;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    /**
     * Blends the top and bottom characters from a surface
     * 
     * Transperancy is not supported.
     */
    private String blend(String bot, String top)
    {
        if (top.length() >= 6 && top.substring(0, 6).equals("\033[48;2"))
        {
            //background on top will cover the bottom
            return top;
        }
        
        StringBuilder newString = new StringBuilder();
        if (bot.length() >= 6 && bot.substring(0, 6).equals("\033[48;2"))
        {
            String[] parts = bot.split("m"); //m seperates the escape sequences
            newString.append(parts[0]);
            newString.append("m"); //m was stripped off
        }
        //top only has charachter data
        newString.append(top);
        newString.append("\033[0m"); //reset formating
        
        return newString.toString();
    }
    private boolean isOutOfBounds(int x, int y)
    {
         return (x >= width || x < 0 || y >= height || y < 0);
    }
}