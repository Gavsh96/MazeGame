/*Class used to decorate keys and doors Cyan*/
package edu.curtin.app;

public class CyanColoringDecorator extends Coloring
{
    public CyanColoringDecorator(MazeElements newMazeElements) 
    {
        super(newMazeElements);
    }

    @Override
    public void saveMessage(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saveColor(int color) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPosition(int row, int col) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString()
    {
        return "\033[36m"+tempMazeElements.toString()+"\033[m";
    }

    @Override
    public String getColorCode()
    {
        return "\033[36m\033[m";
    }

    @Override
    public void setKey(MazeElements key) {
        // TODO Auto-generated method stub
        
    }
    
}