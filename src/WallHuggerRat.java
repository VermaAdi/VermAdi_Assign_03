import java.util.Random;

/**
 * Name: Aditya Verma
 * Date: Mar 08, 2022
 * Description: Assign 03 "Rat Race" Submission for CS321.
 * <p>
 * WallHuggerRat Class, represents an animal that only moves right of its current position.
 */
public class WallHuggerRat implements Animal {

    private int startCol;
    private int startRow;
    private int currentCol;
    private int currentRow;
    private String name = "NoName";
    private int numMoves;

    String[] dir = {"U", "R", "D", "L"};
    int index = 0;
    String orientation = dir[index]; // orientation of the rat

    // constructor
    public WallHuggerRat() {
        this.startCol = 0;
        this.startRow = 0;
        this.currentCol = 0;
        this.currentRow = 0;
        this.numMoves = 0;
    }

    // returns current row animal is in
    public int getRow() {
        return currentRow;
    }

    // returns current column animal is in
    public int getColumn() {
        return currentCol;
    }

    // returns one letter to represent animal
    public char getLetter() {
        return name.charAt(0);
    }

    // returns animal's name
    public String getName() {
        return name;
    }

    // returns # moves animal has made in maze so far
    public int getNumMoves() {
        return numMoves;
    }

    // returns column where animal started in maze
    public int getStartColumn() {
        return startCol;
    }

    // returns row where animal started in maze
    public int getStartRow() {
        return startRow;
    }

    // asks animal to make a move in this maze. This is called by the Maze
    public void move(Maze maz) {
        boolean noMoveFound = true;

        while (noMoveFound) {
            if (lookRight(maz)) //RIGHT
            {
                turnRight();
                moveUp();
                noMoveFound = false;
            } else if (lookUp(maz)) // UP if not RIGHT
            {
                moveUp();
                noMoveFound = false;
            } else if (lookLeft(maz)) //LEFT if not UP
            {
                turnLeft();
                moveUp();
                noMoveFound = false;
            } else { //DOWN if not UP, LEFT and RIGHT
                turnRight();
                turnRight();
                moveUp();
                noMoveFound = false;
            }
        }
        numMoves++;
    }

    // turns the rat to the left
    private void turnLeft() {
        index = (index + 3) % 4;
        orientation = dir[index];
    }

    // turns the rat to the right
    private void turnRight() {
        index = (index + 1) % 4;
        orientation = dir[index];
    }

    //makes the rat move up depending on the rat's orientation
    public void moveUp() {
        if (orientation.equals("U")) {
            currentRow--;
        } else if (orientation.equals("R")) {
            currentCol++;
        } else if (orientation.equals("D")) {
            currentRow++;
        } else {
            currentCol--;
        }
    }

    // checks if rat can move up depending on its orientation
    public boolean lookUp(Maze maz) {
        if (orientation.equals("U")) {
            return maz.canMove(currentRow - 1, currentCol);
        } else if (orientation.equals("R")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else if (orientation.equals("D")) {
            return maz.canMove(currentRow + 1, currentCol);
        } else {
            return maz.canMove(currentRow, currentCol - 1);
        }
    }

    // checks if rat can move right depending on its orientation
    public boolean lookRight(Maze maz) {
        if (orientation.equals("U")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else if (orientation.equals("R")) {
            return maz.canMove(currentRow + 1, currentCol);
        } else if (orientation.equals("D")) {
            return maz.canMove(currentRow, currentCol - 1);
        } else {
            return maz.canMove(currentRow - 1, currentCol);
        }
    }

    // checks if rat can move left depending on its orientation
    public boolean lookLeft(Maze maz) {
        if (orientation.equals("U")) {
            return maz.canMove(currentRow, currentCol - 1);
        } else if (orientation.equals("R")) {
            return maz.canMove(currentRow - 1, currentCol);
        } else if (orientation.equals("D")) {
            return maz.canMove(currentRow, currentCol + 1);
        } else {
            return maz.canMove(currentRow + 1, currentCol);
        }
    }

    // moves animal back to starting row/column, wipes # moves to 0
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
    }

    // sets column where animal started in maze
    public void setStartColumn(int col) {
        startCol = col;
    }

    // sets row where animal started in maze
    public void setStartRow(int row) {
        startRow = row;
    }


}


