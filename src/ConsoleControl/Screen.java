package ConsoleControl;

/**
 * Methods for munipulating the entire
 * console screen
 */
public class Screen 
{
    private void Screen(){}
    
    /**
     * Clear the entire screen
     */
    public static void clear()
    {
       Cursor.reset(); //move cursor to 1st row, 1st column
       System.out.println("\033[2J");
       System.out.flush(); //write the clear immediatly
    }
}