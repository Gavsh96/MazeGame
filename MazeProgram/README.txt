README
======
* Type "./gradlew run" to run. 
* Type "./gradlew check" to verify PMD rules. (On Windows, drop the "./" from start of course; i.e., "gradlew run" or "gradlew check".)

This is a basic maze game made using java which takes a input text file which
contains the maze structure and prints the maze based on the text file.

The Main Class is the MazeGame class where the input file is read and all user input is taken.
An interface called MazeElements has been created where it is implemented in each class created for each element 
eg: Player class, Key class, etc.
A class called Maze is created where all the maze element data and key movements are assigned.  
Another class called MazeList is created which contains an array list to store the MazeElements.
Set of classes are created to color keys and doors using decorator pattern eg: Coloring class.

Array lists and a sets were used. the array lists were mainly used to store 
MazeElements the required elements were iterated and taken when needed from the array list.

A custom exception class is created to handle exceptions in the program eg: if a duplicate wall 
value is given in the text file the program will check it and throw an exception to alert the user.
Logging all the moves of the player throughout the maze is being logged using logging statements and all the 
data read and added from the input file is also being logged.

Strategy pattern is used for the MazeElements taken from the input file where each maze element 
has thier own class that implements the MazeElements interface.

A decorator pattern is used to color the keys and doors in the maze 
the color code is checked and the door/key is colored accordingly.







