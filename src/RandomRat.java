import java.awt.*;
import java.util.Random;

/**
 * Name: Aditya Verma
 * Date: Mar 08, 2022
 * Description: Assign 03 "Rat Race" Submission for CS321.
 * <p>
 * RandomRat Class, represents animal that has memory of previous
 * position and moves randomly avoiding going to the immediate previous position.
 */
public class RandomRat implements Animal {

    static Random rnd = new Random();

    private int startCol;
    private int startRow;
    private int currentCol;
    private int currentRow;
    private String name = "NoName";
    private int numMoves;
    Point previous;
    Point temp;

    //orientation
    String[] direction = {"U", "R", "D", "L"};
    int index = 0;
    String orientation = direction[index]; // current direction

    // constructors
    public RandomRat() {
        this.startCol = 0;
        this.startRow = 0;
        this.currentCol = 0;
        this.currentRow = 0;
        this.numMoves = 0;
        previous = new Point();
        temp = new Point();
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
            int option = rnd.nextInt(4); //random move instruction
            switch (option) {
                case 0: //UP
                    if (lookUp(maz)) {
                        setLocation(maz);
                        maz.toString();
                        if (!previous.equals(temp)) { //checking if next square was the previous one
                            System.out.println("going up");
                            moveUp();                  // rat goes forward
                            previous.setLocation(currentCol, currentRow);
                            noMoveFound = false;
                        }
                    }
                    break;
                case 1: //RIGHT
                    if (lookRight(maz)) {
                        turnRight();
                        setLocation(maz);
                        if (!previous.equals(temp)) { //checking if next square was the previous one
                            previous.setLocation(currentRow, currentCol);
                            System.out.println("going right");
                            moveUp();                        //rat goes RIGHT
                            noMoveFound = false;
                        }
                    }
                    break;
                case 2: //LEFT
                    if (lookLeft(maz)) {
                        turnLeft();
                        setLocation(maz);
                        if (!previous.equals(temp)) { //checking if next square was the previous one
                            previous.setLocation(currentRow, currentCol);
                            System.out.println("going left");
                            moveUp();                     //rat goes LEFT
                            noMoveFound = false;
                        }
                    }
                    break;
                case 3: //DOWN
                    if (!lookLeft(maz) && !lookUp(maz) && !lookRight(maz)) {
                        //turn around
                        turnRight();
                        turnRight();
                        setLocation(maz);
                        if (previous.equals(temp)) { //checking if next square was the previous one
                            previous.setLocation(currentRow, currentCol);
                            moveUp(); //down
                            System.out.println("going back");
                            noMoveFound = false;
                        }
                    }
            }
        }
        numMoves++;
        System.out.println(numMoves);
        System.out.println("orientation" + orientation);
    }

    // functions similar to WallHugger
    private void turnLeft() {
        index = (index + 3) % 4;
        orientation = direction[index];
    }


    private void turnRight() {
        index = (index + 1) % 4;
        orientation = direction[index];
    }


    public void moveUp() {

        if (orientation.equals("U"))
            currentRow--;
        else if (orientation.equals("R"))
            currentCol++;
        else if (orientation.equals("D"))
            currentRow++;
        else
            currentCol--;
    }

    public boolean lookRight(Maze maz) {
        if (orientation.equals("U"))
            return maz.canMove(currentRow, currentCol + 1);
        else if (orientation.equals("R"))
            return maz.canMove(currentRow + 1, currentCol);
        else if (orientation.equals("D"))
            return maz.canMove(currentRow, currentCol - 1);
        else
            return maz.canMove(currentRow - 1, currentCol);
    }

    public boolean lookUp(Maze maz) {
        if (orientation.equals("U"))
            return maz.canMove(currentRow - 1, currentCol);
        else if (orientation.equals("R"))
            return maz.canMove(currentRow, currentCol + 1);
        else if (orientation.equals("D"))
            return maz.canMove(currentRow + 1, currentCol);
        else
            return maz.canMove(currentRow, currentCol - 1);
    }

    public boolean lookLeft(Maze maz) {
        if (orientation.equals("U"))
            return maz.canMove(currentRow, currentCol - 1);
        else if (orientation.equals("R"))
            return maz.canMove(currentRow - 1, currentCol);
        else if (orientation.equals("D"))
            return maz.canMove(currentRow, currentCol + 1);
        else
            return maz.canMove(currentRow + 1, currentCol);
    }

    public boolean lookDown(Maze maz) {
        if (orientation.equals("U"))
            return maz.canMove(currentRow + 1, currentCol);
        else if (orientation.equals("R"))
            return maz.canMove(currentRow, currentCol - 1);
        else if (orientation.equals("D"))
            return maz.canMove(currentRow - 1, currentCol);
        else
            return maz.canMove(currentRow, currentCol + 1);
    }

    // sets the temp Point based on orientation of the rat.
    // temp gets compared to previous in move()
    public void setLocation(Maze maz) {
        if (orientation.equals("U")) {
            temp.setLocation(currentRow - 1, currentCol);
        } else if (orientation.equals("R"))
            temp.setLocation(currentRow, currentCol + 1);
        else if (orientation.equals("D"))
            temp.setLocation(currentRow + 1, currentCol);
        else
            temp.setLocation(currentRow, currentCol - 1);
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

