/*This class is used to store horizontal walls data obtained from the input file*/
package edu.curtin.app;

import java.util.List;
@SuppressWarnings("PMD.CommentDefaultAccessModifier") //I got this error for all classes that impelemts MazeElements couldnt find a solution so i suppressed it
public class HorizontalWalls implements MazeElements
{
    int row;
    int col;
    String message;
    int color;
     
    @Override
    public String toString()
    {
        return "\u2500";
    }

    @Override
    public void saveMessage(String message) 
    {
        this.message = message;
    }

    @Override
    public void saveColor(int color){
        this.color = color;
    }

    @Override
    public String returnMessage() {
        return message;
    }

    @Override
    public int returnColor() {
        return color;
    }

    @Override
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public String getType() {
        return "HW";
    }

    @Override
    public String getColorCode() {
        return null;
    }

    @Override
    public void setKey(MazeElements key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<MazeElements> getKeyList() {
        // TODO Auto-generated method stub
        return null;
    }
}
