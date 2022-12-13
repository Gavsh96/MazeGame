/*This class is used to implement the maze and carray out its tasks*/
package edu.curtin.app;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("PMD") //Had a switch statement error where it askes me to remove breaks but if i do the program wont function properly & also had a logger call error
public class Maze
{
    private Object[][] arr;

    private String[][] mazeP;

    private int mazeRow;

    private int mazeCol;

    private int playerRow;

    private int playerCol;
    
    private Iterator<MazeElements>  it;
    
    private String printAvailableKey = " ";

    private boolean containsEp;

    private String errorMessage;

    private boolean keyAvailable;

    private static Logger logger = Logger.getLogger(Maze.class.getName());

    public Maze(int row, int col)
    {
        //2D array is created to store array lists
        mazeRow = row;
        mazeCol = col;
        arr = new Object[row][col];
    }

    public void assignArray() 
    {
        //for each index in the 2D array created above an arraylist that can contain MazeElements is added
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = new MazeList();
            }
        }  
    }

    public void addindex(int row, int col, MazeElements data) 
    {
        //The MazeElements are added too the array lists in 2D array 
        MazeList a = (MazeList) arr[row][col];

        a.addEntry(data);

    }

    public void assignMaze()
    {
       //new 2D array is created to print and show the maze MazeElements from the arrayslists in the first 2D array is taken and mapped in to mazeP array
        int printRow= (mazeRow*2)+1;
        int printCol= (mazeCol*3)+mazeCol+1;
        mazeP = new String[printRow][printCol];


        for (int i = 0; i < arr.length; i++) 
        {
            for (int j = 0; j < arr[i].length; j++) 
            {

                MazeList a = (MazeList) arr[i][j];
                List<MazeElements> elementData = a.getdata();

                int rowIncrement = i +1;
                int colIncrement = j +1;

                it = elementData.iterator();
                
                //arraylists are ittrated and mazeElements are mapped in to the 2D array
                while (it.hasNext())
                {
                    MazeElements type = it.next();
                    if(type.getType().equals("Player"))
                    {
                        mazeP[i+rowIncrement][(j+colIncrement)*2] = type.toString();
                        
                        playerRow = type.getRow();
                        playerCol = type.getCol();   
                    }
                    else if(type.getType().equals("Key"))
                    {
                        mazeP[i+rowIncrement][(j+colIncrement)*2] = type.toString();
                    }
                    else if(type.getType().equals("Ep"))
                    {
                        mazeP[i+rowIncrement][(j+colIncrement)*2] = type.toString();
                    }
                    else if(type.getType().equals("VD"))
                    {
                        mazeP[i+rowIncrement][((j+colIncrement)*2)-2] = type.toString();
                    }
                    else if(type.getType().equals("HD"))
                    {
                        mazeP[(i+rowIncrement)-1][(j+colIncrement)*2] = type.toString();
                        mazeP[(i+rowIncrement)-1][((j+colIncrement)*2)-1] = type.toString();
                        mazeP[(i+rowIncrement)-1][((j+colIncrement)*2)+1] = type.toString();
                    }
                    else if(type.getType().equals("VW"))
                    {
                        mazeP[i+rowIncrement][((j+colIncrement)*2)-2] = type.toString(); 
                    }
                    else if(type.getType().equals("HW"))
                    {
                        mazeP[(i+rowIncrement)-1][(j+colIncrement)*2] = type.toString();
                        mazeP[(i+rowIncrement)-1][((j+colIncrement)*2)-1] = type.toString();
                        mazeP[(i+rowIncrement)-1][((j+colIncrement)*2)+1] = type.toString(); 
                    }
                    
                }
                
            }
        }

        //borders are added to the maze
        for (int i = 0; i < printRow; i++)
        {
            for (int j = 0; j < printCol; j++)
            {
                mazeP[0][j]  = "\u2500";
                mazeP[i][printCol-1] = "\u2502";
                mazeP[printRow - 1][j]  = "\u2500";
                mazeP[i][0]  = "\u2502";    
                
                mazeP[0][0] = "\u250c";
                mazeP[0][printCol-1] = "\u2510";
                mazeP[printRow-1][0] = "\u2514";
                mazeP[printRow-1][printCol-1] = "\u2518";

                    
                if (mazeP[i][1] != null && i < printRow-1) 
                {
                   mazeP[i][0] = "\u251c";
                }
                
                if (mazeP[i][printCol-2] != null && i < printRow-1) 
                {
                    mazeP[i][printCol-1] = "\u2524";
                }

                if (mazeP[1][j] != null && j < printCol-1)  
                {
                    mazeP[0][j] = "\u252c";
                }

                if (mazeP[printRow-2][j] != null && j < printCol-1) 
                {
                    mazeP[printRow-1][j] = "\u2534";
                }
                
                
            }
        }
          
    }

    //movement of the player through out the maze 

    public void movement(String direction)
    {
        MazeList a = (MazeList) arr[playerRow][playerCol];
        List<MazeElements> elementData = a.getdata();
        List<MazeElements> keyList = getPlayerKeyList(elementData);
        keyAvailable = false;
        if (!keyList.isEmpty())
        {
            setprintAvailableKey(keyList.toString());
        }
        
        int rowIncrement = playerRow +1;
        int colIncrement = playerCol +1;

        int playerRowOnMaze = playerRow + rowIncrement;
        int playerColOnMaze = (playerCol + colIncrement)*2;

        errorMessage = " ";
        containsEp = false;        
        switch(direction)
        {
            case "n":
                String upLocation = mazeP[playerRowOnMaze-1][playerColOnMaze];
                if (upLocation == null) 
                {
                    removePlayer(elementData);
                    playerRow = playerRow -1;
                    MazeList arrData = (MazeList) arr[playerRow][playerCol];
                    List<MazeElements> movementData = arrData.getdata();
                    checkEndPoint(movementData);
                    MazeElements pLocation = new Player();
                    assignKey(keyList, pLocation);
                    assignKey(movementData, pLocation);
                    pLocation.setPosition(playerRow, playerCol);
                    arrData.addEntry(pLocation);
                    logger.info(String.format("Player Moves Up"));    
                }
                else if (upLocation.equals("\033[31m\u2592\033[m")||upLocation.equals("\033[32m\u2592\033[m")||upLocation.equals("\033[33m\u2592\033[m")||upLocation.equals("\033[34m\u2592\033[m")||upLocation.equals("\033[35m\u2592\033[m")||upLocation.equals("\033[36m\u2592\033[m"))
                {
                    List<MazeElements> kL = getPlayer(elementData).getKeyList();

                    checkKey(upLocation, kL);

                    if (keyAvailable) 
                    {
                        removePlayer(elementData);
                        playerRow = playerRow -1;
                        MazeList arrData = (MazeList) arr[playerRow][playerCol];
                        List<MazeElements> movementData = arrData.getdata();

                        checkEndPoint(movementData);
                        MazeElements pLocation = new Player();
                        assignKey(keyList, pLocation);
                        assignKey(movementData, pLocation);
                        pLocation.setPosition(playerRow, playerCol);
                        arrData.addEntry(pLocation);
                        logger.info(String.format("Player Moves Up Through A Door"));  
                    }
                    
                }
                else
                {
                    setErrorMessage("\033[31mPlayer cannot move there\033[m");
                }
                        
            break;    
            case "w":
                String leftLocation = mazeP[playerRowOnMaze][playerColOnMaze-2];
                if (leftLocation == null) 
                {
                    removePlayer(elementData);
                    playerCol = playerCol -1;
                    MazeList arrData = (MazeList) arr[playerRow][playerCol];
                    List<MazeElements> movementData = arrData.getdata();
                    checkEndPoint(movementData);
                    MazeElements pLocation = new Player();
                    assignKey(keyList, pLocation);
                    assignKey(movementData, pLocation);
                    pLocation.setPosition(playerRow, playerCol);
                    arrData.addEntry(pLocation);
                    logger.info(String.format("Player Moves Left"));    
                }
                else if (leftLocation.equals("\033[31m\u2592\033[m")||leftLocation.equals("\033[32m\u2592\033[m")||leftLocation.equals("\033[33m\u2592\033[m")||leftLocation.equals("\033[34m\u2592\033[m")||leftLocation.equals("\033[35m\u2592\033[m")||leftLocation.equals("\033[36m\u2592\033[m"))
                {
                    List<MazeElements> kL = getPlayer(elementData).getKeyList();

                    checkKey(leftLocation, kL);

                    if (keyAvailable) 
                    {
                        removePlayer(elementData);
                        playerCol = playerCol -1;
                        MazeList arrData = (MazeList) arr[playerRow][playerCol];
                        List<MazeElements> movementData = arrData.getdata();
                        checkEndPoint(movementData);
                        MazeElements pLocation = new Player();
                        assignKey(keyList, pLocation);
                        assignKey(movementData, pLocation);
                        pLocation.setPosition(playerRow, playerCol);
                        arrData.addEntry(pLocation);
                        logger.info(String.format("Player Moves Left Through A Door"));  
                    }
                }
                else
                {
                    setErrorMessage("\033[31mPlayer cannot move there\033[m");
                }
            break;    
            case "s":
                String downLocation = mazeP[playerRowOnMaze+1][playerColOnMaze]; 
                if (downLocation == null) 
                {
                    removePlayer(elementData);   
                    playerRow = playerRow +1;
                    MazeList arrData = (MazeList) arr[playerRow][playerCol];
                    List<MazeElements> movementData = arrData.getdata();
                    checkEndPoint(movementData);
                    MazeElements pLocation = new Player();
                    assignKey(keyList, pLocation);
                    assignKey(movementData, pLocation);
                    pLocation.setPosition(playerRow, playerCol);
                    arrData.addEntry(pLocation);
                    logger.info(String.format("Player Moves Down"));     
                }
                else if (downLocation.equals("\033[31m\u2592\033[m")||downLocation.equals("\033[32m\u2592\033[m")||downLocation.equals("\033[33m\u2592\033[m")||downLocation.equals("\033[34m\u2592\033[m")||downLocation.equals("\033[35m\u2592\033[m")||downLocation.equals("\033[36m\u2592\033[m"))
                {
                    List<MazeElements> kL = getPlayer(elementData).getKeyList();

                    checkKey(downLocation, kL);

                    if (keyAvailable) 
                    {
                        removePlayer(elementData);
                        playerRow = playerRow +1;
                        MazeList arrData = (MazeList) arr[playerRow][playerCol];
                        List<MazeElements> movementData = arrData.getdata();
                        checkEndPoint(movementData);
                        MazeElements pLocation = new Player();
                        assignKey(keyList, pLocation);
                        assignKey(movementData, pLocation);
                        pLocation.setPosition(playerRow, playerCol);
                        arrData.addEntry(pLocation);
                        logger.info(String.format("Player Moves Down Through A Door"));  
                    }
                }
                else
                {
                    setErrorMessage("\033[31mPlayer cannot move there\033[m");
                }
            break; 
            case "e":
                String rightLocation = mazeP[playerRowOnMaze][playerColOnMaze+2];
                if ( rightLocation == null) 
                {
                    removePlayer(elementData);
                    playerCol = playerCol +1;
                    MazeList arrData = (MazeList) arr[playerRow][playerCol];
                    List<MazeElements> movementData = arrData.getdata();
                    checkEndPoint(movementData);
                    MazeElements pLocation = new Player();
                    assignKey(keyList, pLocation);
                    assignKey(movementData, pLocation);
                    pLocation.setPosition(playerRow, playerCol);
                    arrData.addEntry(pLocation);
                    logger.info(String.format("Player Moves Right"));    
                }
                else if (rightLocation.equals("\033[31m\u2592\033[m")||rightLocation.equals("\033[32m\u2592\033[m")||rightLocation.equals("\033[33m\u2592\033[m")||rightLocation.equals("\033[34m\u2592\033[m")||rightLocation.equals("\033[35m\u2592\033[m")||rightLocation.equals("\033[36m\u2592\033[m"))
                {
                    List<MazeElements> kL = getPlayer(elementData).getKeyList();

                    checkKey(rightLocation, kL);

                    if (keyAvailable) 
                    {
                        removePlayer(elementData);
                        playerCol = playerCol +1;
                        MazeList arrData = (MazeList) arr[playerRow][playerCol];
                        List<MazeElements> movementData = arrData.getdata();

                        checkEndPoint(movementData);
                        MazeElements pLocation = new Player();
                        assignKey(keyList, pLocation);
                        assignKey(movementData, pLocation);
                        pLocation.setPosition(playerRow, playerCol);
                        arrData.addEntry(pLocation);
                        logger.info(String.format("Player Moves Right Through A Door"));  
                    }
                }
                else
                {
                    setErrorMessage("\033[31mPlayer cannot move there\033[m");
                }
            break;
            default:
                System.out.println("Invalid Direction");
                
        }
                
    }

    /*Methods used to implement the above functions*/

    public int getPlayerRow()
    {
        return playerRow;
    }

    public int getPlayerCol()
    {
        return playerCol;
    }

    //interates and removes player from list
    private void removePlayer(List<MazeElements> elementData)
    {
        it = elementData.iterator();
        while (it.hasNext())
        {
            MazeElements type = it.next();
            if(type.getType().equals("Player"))
            {
                it.remove();
            }   
        }
    }
    //interates and returns player from list
    private MazeElements getPlayer(List<MazeElements> elementData)
    {
        it = elementData.iterator();
        while (it.hasNext())
        {
            MazeElements type = it.next();
            if(type.getType().equals("Player"))
            {
                return type;
            }   
        }
        return null;
    }
    //interates and returns the keyList from player
    private List<MazeElements> getPlayerKeyList(List<MazeElements> elementData)
    {
        it = elementData.iterator();
        while (it.hasNext())
        {
            MazeElements type = it.next();
            if(type.getType().equals("Player"))
            {
                return type.getKeyList();
            }   
        }
        return Collections.emptyList();
    }
    //interates and returns and removes message from list
    public void returnMessage()
    {
        
        MazeList messageList = (MazeList) arr[playerRow][playerCol];
        List<MazeElements> elementData = messageList.getdata();

        it = elementData.iterator();

        while (it.hasNext())
        {
            MazeElements type = it.next();
            if(type.getType().equals("Message"))
            {
                System.out.println(type.returnMessage());
                it.remove();
            }   
        } 
   
    }
    //interates and checks if the players key list contains a key with the same color as the door
    private void checkKey(String location, List<MazeElements> kL)
    {
        if (location.equals("\033[31m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[31m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Red door opens"));   
                }   
            }
        }
        else if (location.equals("\033[32m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[32m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Green door opens")); 
                }   
            }
        }
        else if (location.equals("\033[33m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[33m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Yellow door opens")); 
                }   
            }
        }
        else if (location.equals("\033[34m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[34m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Blue door opens")); 
                }   
            }
        }
        else if (location.equals("\033[35m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[35m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Magenta door opens")); 
                }   
            }
        }
        else if (location.equals("\033[36m\u2592\033[m") ) 
        {
            it = kL.iterator();

            while (it.hasNext())
            {
                MazeElements key = it.next();
                if(key.getColorCode().equals("\033[36m\033[m"))
                {
                    keyAvailable = true;
                    logger.info(String.format("Cyan door opens")); 
                }   
            }
        }
    }
    //checks is player postion is a end point to end the program
    private void checkEndPoint(List<MazeElements> elementData)
    {
        it = elementData.iterator();

        while (it.hasNext())
        {
            MazeElements type = it.next();
            if(type.getType().equals("Ep"))
            {
                containsEp = true;
            }   
        }
    }

    public boolean getContainsEp()
    {
        return containsEp;
    }
    //interates and adds key to the keylist in player
    private void assignKey(List<MazeElements> elementData, MazeElements player)
    {
        it = elementData.iterator();

        while (it.hasNext())
        {
            MazeElements key = it.next();
            if(key.getType().equals("Key"))
            {
                player.setKey(key);
                it.remove();
            }   
        }
    }

    private void setprintAvailableKey(String keys)
    {
        printAvailableKey = keys;
    }

    public String getprintAvailableKey()
    {
        return printAvailableKey; 
    }

    private void setErrorMessage(String eMessage)
    {
        errorMessage = eMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage; 
    }
    //to string method used to print maze
    @Override
    public String toString() 
    {
        StringBuilder mazePrint = new StringBuilder();
        for (int i = 0; i < mazeP.length; i++) {
            for (int j = 0; j < mazeP[i].length; j++) {
                if (mazeP[i][j]==null) {
                    mazePrint.append(" ");
                }
                else {
                    mazePrint.append(mazeP[i][j]);
                }
            }
            mazePrint.append('\n');
        }
        return mazePrint.toString();
    }
}