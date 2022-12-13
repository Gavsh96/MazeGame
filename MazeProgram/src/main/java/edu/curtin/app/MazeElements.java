/*MazeElements interface used for stratergy pattern*/
package edu.curtin.app;

import java.util.List;

public interface MazeElements 
{
    @Override
    public String toString();

    public String getType();
    
    public void saveMessage(String message);

    public String returnMessage();

    public void saveColor(int color);

    public int returnColor();
    
    public void setPosition(int row, int col);

    public int getRow();

    public int getCol();

    public String getColorCode();

    public void setKey(MazeElements key);

    public List<MazeElements> getKeyList();

}
