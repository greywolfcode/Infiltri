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
       System.out.println("\033[0m"); //reset all formating
       System.out.println("\033[2J"); //reset the screen
       Cursor.reset(); //move cursor to 1st row, 1st column
       System.out.flush(); //write the clear immediatly
    }
}