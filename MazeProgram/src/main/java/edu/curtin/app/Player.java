/*This class is used to store player data obtained from the input file*/
package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("PMD.CommentDefaultAccessModifier") //I got this error for all classes that impelemts MazeElements couldnt find a solution so i suppressed it
public class Player implements MazeElements
{
    int row;
    int col;
    String message;
    int color;
    List<MazeElements> keyList = new ArrayList<>();


    @Override
    public String toString()
    {
        return "P";
    }

    @Override
    public void saveMessage(String message) 
    {
        this.message = message;
    }

    @Override
    public void saveColor(int color) 
    {
        this.color = color;
    }

    @Override
    public String returnMessage() {
        return null;
    }

    @Override
    public int returnColor() {
        return 0;
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
        return "Player";
    }

    @Override
    public String getColorCode() {
        return null;
    }

    @Override
    public void setKey(MazeElements key) 
    {
        keyList.add(key);  
    }

    @Override
    public List<MazeElements> getKeyList() {
        return keyList;
    }

}

