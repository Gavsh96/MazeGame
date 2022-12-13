/*List that contains MazeElements */
package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;

public class MazeList
{
    private List<MazeElements> mazeData;

    public MazeList()
    {
        mazeData = new ArrayList<>();
    }

    public void addEntry(MazeElements data)
    {
        mazeData.add(data);
    }

    public List<MazeElements> getdata()
    {   
        
        return mazeData;
        
    }

}