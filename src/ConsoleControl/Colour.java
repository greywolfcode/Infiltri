package ConsoleControl;
/**
 * Class creates strings for colour output in console using ANSI escape codes
 * 
 * Formatting is reset to normal after String is formatted
 */
public class Colour 
{
    private Colour(){}
    
    /**
     * Creates and returns Strings for RGB colour
     * Takes String line and RGB colour values as input
     */
    public static String rgb(String line, int r, int g, int b)
    {
        return "\033[38;2;" + r + ";" + g + ";" + b + "m" + line + "\033[0m";
    }
    /**
     * Creates and returns String for RGB background colour
     * Takes string and RGB colour values as input
     */
    public static String bgRGB(String line, int r, int g, int b)
    {
        return "\033[48;2;" + r + ";" + g + ";" + b + "m" + line + "\033[0m";
    }
    /**
     * Creates and returns Strings with colour inverted
     * Takes String line as input
     */
    public static String invert(String line)
    {
        return "\033[7m" + line + "\033[0m";
    }
}