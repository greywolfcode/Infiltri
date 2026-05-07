package Graphics;

import ConsoleControl.Colour;

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
     * Draws the surface to the screen
     */
    public void update()
    {
        for (String[] row:surface)
        {
            System.out.println(String.join("", row));
        }
    }
    
}