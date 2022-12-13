/*Abstract class created to implement decorator pattern*/
package edu.curtin.app;

import java.util.List;

abstract class Coloring implements MazeElements
{
    protected MazeElements tempMazeElements;

    public Coloring(MazeElements newMazeElements)
    {
        tempMazeElements = newMazeElements;
    }

    @Override
    public String toString()
    {
        return tempMazeElements.toString();
    }

    @Override
    public String returnMessage() {
        return tempMazeElements.returnMessage();
    }

    @Override
    public int returnColor() 
    {
        return tempMazeElements.returnColor();
    }

    @Override
    public int getRow() {
        return tempMazeElements.getRow();
    }

    @Override
    public int getCol() {
        return tempMazeElements.getCol();
    }


    @Override
    public String getType() {
        return tempMazeElements.getType();
    }

    @Override
    public String getColorCode()
    {
        return tempMazeElements.getColorCode();
    }

    @Override
    public List<MazeElements> getKeyList()
    {
        return tempMazeElements.getKeyList();
    }

} 

    
    


    