/*Class used to decorate keys and doors yellow*/
package edu.curtin.app;

public class YellowColoringDecorator extends Coloring
{
    public YellowColoringDecorator(MazeElements newMazeElements) 
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
        return "\033[33m"+tempMazeElements.toString()+"\033[m";
    }

    @Override
    public String getColorCode()
    {
        return "\033[33m\033[m";
    }

    @Override
    public void setKey(MazeElements key) {
        // TODO Auto-generated method stub
        
    }
    
}