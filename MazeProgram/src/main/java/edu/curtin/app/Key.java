/*This class is used to store key data obtained from the input file*/
package edu.curtin.app;

import java.util.Collections;
import java.util.List;
@SuppressWarnings("PMD.CommentDefaultAccessModifier") //I got this error for all classes that impelemts MazeElements couldnt find a solution so i suppressed it
public class Key implements MazeElements 
{
    int color;
    int row;
    int col;
    String message;

    @Override
    public String toString()
    {
        return "\u2555";
    }

    @Override
    public void saveMessage(String message) {
        this.message = message;
    }

    @Override
    public String returnMessage() {
        return message;
    }

    @Override
    public void saveColor(int color) {
        this.color = color;
    }

    @Override
    public int returnColor() 
    {
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
        return "Key";
    }

    @Override
    public String getColorCode() {
        return null;
    }


    @Override
    public void setKey(MazeElements key) {
        
    }

    @Override
    public List<MazeElements> getKeyList() {
        return Collections.emptyList();
    }

    
}