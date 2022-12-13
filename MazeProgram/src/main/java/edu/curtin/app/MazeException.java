/*Custom exception class used to errors whenreading text file to get data*/
package edu.curtin.app;
@SuppressWarnings("PMD.CommentDefaultAccessModifier") //couldnt find a solution for this error so i suppressed it

public class MazeException extends Exception 
{
    MazeException(String displayMessage){
        super(displayMessage);
    }
}
