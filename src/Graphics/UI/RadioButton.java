package Graphics.UI;

import java.util.ArrayList;

import Graphics.Surface;

public class RadioButton 
{
    private ArrayList<Button> buttons;
    private int selectedButton;
    
    public RadioButton()
    {
        buttons = new ArrayList<>();
        selectedButton = 0;
    }
    
    public int getSelectedButton()
    {
        return selectedButton;
    }
    public void setSelectedButton(int index)
    {
        selectedButton = index;
        
        if (selectedButton >= buttons.size())
        {
            selectedButton = buttons.size() - 1;
        }
    }
    
    public void addButton(Button button)
    {
        buttons.add(button);
    }
    public void addButton(String symbol, String label, int x, int y)
    {
        buttons.add(new Button(symbol, label, x, y));
    }
    
    public void removeButton(int index) throws IndexOutOfBoundsException
    {
        if (buttons.size() <= index)
        {
            throw new IndexOutOfBoundsException("Index " + index + " does not exist RadioButton of length " + buttons.size());
        }
        
        buttons.remove(index);
        
        selectedButton--;
        if (selectedButton < 0)
        {
            selectedButton = 0;
        }
    }
    
    /**
     * Renders RadioButton to given surface
     * 
     * Pass surface to blit to and in colour to render buttons in.
     */
    public void render(Surface surface, int r, int g, int b)
    {
        for (int i = 0; i < buttons.size(); i++)
        {
            Button button = buttons.get(i);
            
            if (i == selectedButton)
            {
                surface.writeText(button.getSymbol(), button.getX(), button.getY(), r, g, b);
            }
            
            //write label at offset to symbol so the symbol will not cover the label
            surface.writeText(button.getLabel(), button.getX() + button.getSymbol().length()+1, button.getY(), r, g, b);
        }
    }
    
    /**
     * Changes the selected button
     * 
     * If direction is negative, it moves up,
     * if direction is positive, it moves down.
     */
    public void move(int direction)
    {
        if (direction < 0)
        {
            moveUp();
        }
        else if (direction > 0)
        {
            moveDown();
        }
    }   
    private void moveUp()
    {
        if (selectedButton <= 0)
        {
            selectedButton = buttons.size() - 1;
            return;
        }
        
        selectedButton--;
    }
    private void moveDown()
    {
        if (selectedButton == buttons.size() - 1)
        {
            selectedButton = 0;
            return;
        }
        
        selectedButton++;
    }
}