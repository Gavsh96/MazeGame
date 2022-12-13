package edu.curtin.app;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;

@SuppressWarnings("PMD") //logger call error &close scanner error i did close the scanner but it still shows error
public class MazeGame
{
    private static Scanner userInput = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(MazeGame.class.getName());
    private static List<String> data = new ArrayList<>();

    //reads and adds the input file data
    public static void main(String[] args)
    {
        readAndAdd();
    }

    private static void readAndAdd() 
    {
        String fileName;
        try 
        {
            System.out.println("Enter input file name"); //request name of the input file
            fileName = userInput.nextLine();

            File infile = new File(fileName);
            Scanner scanFile = new Scanner(infile);

            
            //data is added to an array list
            while (scanFile.hasNextLine())
            {
                data.add(scanFile.nextLine());
            }
            
            checkPlayer(data);
            checkEndPoint(data);
            checkDuplicates(data);
            checkRowCol(data);
            //Data in the array list is assingned to its correct MazeElements
            for (String dataEntry : data) 
            {
                String[] parts = dataEntry.split(" ", 4);
                if (!parts[0].equals("WV")&&!parts[0].equals("WH")&&!parts[0].equals("E")&&!parts[0].equals("M")&&!parts[0].equals("K")&&!parts[0].equals("DH")&&!parts[0].equals("DV")&&!parts[0].equals("S")) {
                    
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);
                    logger.info(String.format("Maze Rows=%d Cols=%d", row, col));
                    Maze maze = new Maze(row, col);
                    maze.assignArray(); 

                    for (String dataEntry2 : data) 
                    {
                        String[] parts2 = dataEntry2.split(" ", 4);

                        if (parts2[0].equals("WV")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            VerticalWalls vW = new VerticalWalls();
                            vW.setPosition(rowD, colD);
                            logger.info(String.format("Vertical Walls Row=%d Col=%d", rowD, colD));

                            maze.addindex(rowD, colD, vW);
                        }
                        if (parts2[0].equals("WH")) 
                        {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            HorizontalWalls hW = new HorizontalWalls();
                            hW.setPosition(rowD, colD);
                            logger.info(String.format("Horizontal Walls Row=%d Col=%d", rowD, colD));

                            maze.addindex(rowD, colD, hW);
                        }
                        if (parts2[0].equals("M"))
                        {

                            String message;
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            message = parts2[3];
                            logger.info(String.format("Message Row=%d Col=%d Message=%s", rowD, colD, message));
                            
                            Message m = new Message();
                            m.setPosition(rowD, colD);
                            m.saveMessage(message);        
                            maze.addindex(rowD, colD, m);
                        }
                        if (parts2[0].equals("K"))
                        {
        
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            int color = Integer.parseInt(parts2[3]);
                            logger.info(String.format("Key Row=%d Col=%d Color=%d", rowD, colD, color));

                            Key k = new Key();
                            MazeElements newKey;
                            k.setPosition(rowD, colD);
                            k.saveColor(color);
                            
                            if (color == 1) 
                            {
                                newKey = new RedColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey);
                            }
                            else if (color == 2)
                            {
                                newKey = new GreenColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey);
                            }
                            else if (color == 3) 
                            {
                                newKey = new YellowColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey);   
                            }
                            else if (color == 4) 
                            {
                                newKey = new BlueColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey);
                            }
                            else if (color == 5) 
                            {
                                newKey = new MagentaColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey);
                            }
                            else if (color == 6) 
                            {
                                newKey = new CyanColoringDecorator(k);
                                maze.addindex(rowD, colD, newKey); 
                            }
                            
                        }
                        if (parts2[0].equals("E"))
                        {
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            logger.info(String.format("End Point Row=%d Col=%d", rowD, colD));

                            Endpoint eP = new Endpoint();
                            eP.setPosition(rowD, colD);
                            maze.addindex(rowD, colD, eP);
                        }
                        if (parts2[0].equals("DH")) 
                        {
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            int color = Integer.parseInt(parts2[3]);
                            logger.info(String.format("Horizontal Door Row=%d Col=%d Color=%d", rowD, colD, color));

                            HorizontalDoors hD = new HorizontalDoors();
                            MazeElements newHD;
                            hD.setPosition(rowD, colD);
                            hD.saveColor(color);
                            if (color == 1) 
                            {
                                newHD = new RedColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD);
                            }
                            else if (color == 2)
                            {
                                newHD = new GreenColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD);
                            }
                            else if (color == 3) 
                            {
                                newHD = new YellowColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD);   
                            }
                            else if (color == 4) 
                            {
                                newHD = new BlueColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD);
                            }
                            else if (color == 5) 
                            {
                                newHD = new MagentaColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD);
                            }
                            else if (color == 6) 
                            {
                                newHD = new CyanColoringDecorator(hD);
                                maze.addindex(rowD, colD, newHD); 
                            }
                            
                        }
                        if (parts2[0].equals("DV")) 
                        {
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            int color = Integer.parseInt(parts2[3]);
                            logger.info(String.format("Vertical Door Row=%d Col=%d Color=%d", rowD, colD, color));

                            VerticalDoors vD = new VerticalDoors();
                            MazeElements newVD;
                            vD.setPosition(rowD, colD);
                            vD.saveColor(color);
                            if (color == 1) 
                            {
                                newVD = new RedColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD);
                            }
                            else if (color == 2)
                            {
                                newVD = new GreenColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD);
                            }
                            else if (color == 3) 
                            {
                                newVD = new YellowColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD);   
                            }
                            else if (color == 4) 
                            {
                                newVD = new BlueColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD);
                            }
                            else if (color == 5) 
                            {
                                newVD = new MagentaColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD);
                            }
                            else if (color == 6) 
                            {
                                newVD = new CyanColoringDecorator(vD);
                                maze.addindex(rowD, colD, newVD); 
                            }
                            
                        }
                        if (parts2[0].equals("S"))
                        {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);
                            logger.info(String.format("Player Row=%d Col=%d", rowD, colD));

                            Player p = new Player();
                            p.setPosition(rowD, colD);
                            maze.addindex(rowD, colD, p);

                        }

                
                    }
                    scanFile.close();
                    mazeFunctions(maze);
                    userInput.close();
                }
            }
        
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Input file entered is not available");
        }
        catch (MazeException e)
        {
            System.out.println("File Contains an issue "+e);
        }
    }
    //input is taken from the user to move the player through out the maze ans its printed 
    private static void mazeFunctions(Maze maze)
    {
        String direction;

        maze.assignMaze();
        System.out.println(maze);
        System.out.println("Enter n,s,e or w & click on 'enter' to navigate through the maze! \n(Please use lowercase)");
        maze.returnMessage();

        do 
            {
                direction = userInput.nextLine();

                if (direction.equals("n") || direction.equals("s") || direction.equals("e") || direction.equals("w")) 
                {
                    System.out.println("\033[2J");
                    System.out.println("\033[H");
                    maze.movement(direction);
                    maze.assignMaze();
                    System.out.println(maze);
                    System.out.println("Collected keys: "+maze.getprintAvailableKey());
                    System.out.println(maze.getErrorMessage()); 
                    maze.returnMessage();
                    
                }
                else
                {
                   System.out.println("\033[31mInvalid Direction\033[m");
                }
                
            } while (!maze.getContainsEp());
    }
    //ckecks if row and col is out of bpound and throws exception
    private static void checkRowCol(List<String> data)throws MazeException
    {
        for (String dataEntry : data) 
            {
                String[] parts = dataEntry.split(" ", 4);
                if (!parts[0].equals("WV")&&!parts[0].equals("WH")&&!parts[0].equals("E")&&!parts[0].equals("M")&&!parts[0].equals("K")&&!parts[0].equals("DH")&&!parts[0].equals("DV")&&!parts[0].equals("S")) 
                {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);

                    for (String dataEntry2 : data) 
                    {
                        String[] parts2 = dataEntry2.split(" ", 4);

                        if (parts2[0].equals("WV")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("WH")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("S")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("M")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("E")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("K")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("DV")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                        if (parts2[0].equals("DH")) {
                            
                            int rowD = Integer.parseInt(parts2[1]);
                            int colD = Integer.parseInt(parts2[2]);

                            if (row-1 < rowD || col-1 < colD) 
                            {
                                throw new MazeException("\nRow or Col value in text file is out of bounds");
                            }
                        }
                    }           
                }
            }        
                    
    }
    //checks if there are duplicate doors or walls and throw exception
    private static void checkDuplicates(List<String> data)throws MazeException
    {
        try {

            List<String> doorsAndWalls = new ArrayList<>();
            Set<String> dWSet = new HashSet<>();
            
            for (String dataEntry : data) 
            {
                String[] parts = dataEntry.split(" ", 2);

                if (parts[0].equals("WV")||parts[0].equals("WH")||parts[0].equals("DH")||parts[0].equals("DV")) 
                {
                    doorsAndWalls.add(dataEntry);
                }
            }

            for(String duplicates : doorsAndWalls) 
            {
                if(dWSet.add(duplicates) == false)
                {
                    throw new MazeException("\nDuplicate wall or door data is provided in the text file");
                }
                    
            }
        } catch (MazeException e) {
            System.out.println(e);
        }
        
    }
    //checks if player data is provieded and throws exception if not
    private static void checkPlayer(List<String> data)throws MazeException
    {
        try {

            List<String> player = new ArrayList<>();
            
            for (String dataEntry : data) 
            {
                String[] parts = dataEntry.split(" ", 4);

                if (parts[0].equals("S")) 
                {
                    player.add(dataEntry);
                    
                }
            }

            if (player.isEmpty()){
                throw new MazeException("\nNo Player details provided in the text file");
            }
        } catch (MazeException e) {
            System.out.println(e);
        }
        
    }
    //checks if endpoint data is provided and throws exception if not
    private static void checkEndPoint(List<String> data)throws MazeException
    {
        try {
            
            List<String> endPoint = new ArrayList<>();
            
            for (String dataEntry : data) 
            {
                String[] parts = dataEntry.split(" ", 4);

                if (parts[0].equals("E")) 
                {
                    endPoint.add(dataEntry);
                    
                }
            }

            if (endPoint.isEmpty()){
                throw new MazeException("\nNo Endpoint details provided in the text file");
            }
        } catch (MazeException e) {
            System.out.println(e);
        }
        
    }
}    