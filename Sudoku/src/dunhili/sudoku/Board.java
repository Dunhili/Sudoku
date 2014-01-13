package dunhili.sudoku;

import dunhili.sudoku.Screen.Difficulty;

/**
 *  Contains the logic for determining how to place the cells and if the user
 *  has won the game.
 *
 *  @author Dunhili
 *  @version Jan 10, 2014
 */
public class Board
{
    // ~ Fields ---------------------------------------------------------------
    private int[][] cells;
    private int[] numberCount;

    // ~ Public methods -------------------------------------------------------
    /**
     * Default constructor, makes the board.
     *
     * @param difficulty determines how the board is generated
     */
    public Board(Difficulty difficulty)
    {
        cells = new int[9][9];
        numberCount = new int[9];
        generateBoard(difficulty);
    }

    /**
     * Sets the cell at coordinate (x, y) to the number.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param n number to set the cell to
     */
    public void setCell(int x, int y, int n)
    {
        if (n < 0 || n > 9)
        {
            throw new UnsupportedOperationException("Number must be between 1 and 9.");
        }
        else if (checkBounds(x, y))
        {
            cells[x][y] = n;
        }
    }

    /**
     * Returns the number stored in the cell at coordinate (x, y). If the
     * coordinate pair is invalid, returns -1. A value of 0 indicates a cell
     * that has no number in it.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return number stored in the cell
     */
    public int getCell(int x, int y)
    {
        return (checkBounds(x, y)) ? cells[x][y] : -1;
    }

    /**
     * Returns the character count at the given index. If the index is invalid,
     * returns -1.
     *
     * @param index index of the character count to return
     * @return character count at the given index
     */
    public int getCellCount(int index)
    {
        return (index >= 0 && index < 9) ? numberCount[index] : -1;
    }

    /**
     * Returns true as long as every cell is filled in and the numbers 1-9 only
     * appear once in every row and column (example: 192837465 would be ok, but
     * 199283746 would not), false otherwise.
     *
     * @return true if the game has been won, false otherwise.
     */
    public boolean hasWon()
    {
        // Checks that the user has filled the board with only 1 of every number
        for (int i = 0; i < 9; i++)
        {
            if (numberCount[i] != 9)
            {
                return false;
            }
        }

        return checkRows() && checkColumns();
    }

    /**
     * Checks the row and column that passes through the coordinate (x, y) to make
     * sure there are no duplicates in the row or column.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if there are any duplicates, false otherwise
     */
    public boolean hasIncorrectCell(int x, int y)
    {
        // checks the current row to make sure there isn't more than 1 of a
        // number
        int[] rowCount = new int[9];
        for (int i = 0; i < 9; i++)
        {
            if (getCell(x, i) != 0 && ++rowCount[getCell(x, i) - 1] > 1)
            {
                return true;
            }
        }

        // checks the current column to make sure there isn't more than 1 of a
        // number
        int[] colCount = new int[9];
        for (int j = 0; j < 9; j++)
        {
            if (getCell(j, y) != 0 && ++colCount[getCell(j, y) - 1] > 1)
            {
                return true;
            }
        }

        return false;
    }

    // ~ Private methods ------------------------------------------------------
    /**
     * Checks if the (x, y) coordinate pair is within the board.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    private boolean checkBounds(int x, int y)
    {
        return x >= 0 && x < 9 && y >= 0 && y < 9;
    }

    /**
     * Generates the Sudoku board.
     *
     * @param difficulty how difficult the game should be
     */
    private void generateBoard(Difficulty difficulty)
    {
        switch (difficulty)
        {
            case Easy:
                break;
            case Medium:
                break;
            case Hard:
                break;
            case Expert:
                break;
            default:
        }
    }

    /**
     * Checks the rows to make sure that there are no repeat numbers in any of
     * the rows. Returns true if all the rows have 1 of each number in them,
     * false otherwise.
     *
     * @return true if all the rows have 1 of each number, false otherwise.
     */
    private boolean checkRows()
    {
        for (int i = 0; i < 9; i++)
        {
            int[] currentRow = new int[9];  // initialized to all zeroes by default
            for (int j = 0; j < 9; j++)
            {
                // increments the cell corresponding the number, if there is more
                // than one number in that cell, return false
                if (getCell(i, j) != 0 && ++currentRow[getCell(i, j) - 1] > 1)
                {
                    return false;
                }
            }

            // iterates over the current counts in this row to make sure there's
            // exactly one of each
            for (int j = 0; j < 9; j++)
            {
                if (currentRow[j] != 1)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks the columns to make sure that there are no repeat numbers in any of
     * the columns. Returns true if all the rows have 1 of each number in them,
     * false otherwise.
     *
     * @return true if all the columns have 1 of each number, false otherwise.
     */
    private boolean checkColumns()
    {
        for (int j = 0; j < 9; j++)
        {
            int[] currentColumn = new int[9];  // initialized to all zeroes by default
            for (int i = 0; i < 9; i++)
            {
                // increments the cell corresponding the number, if there is more
                // than one number in that cell, return false
                if (getCell(i, j) != 0 && ++currentColumn[getCell(i, j) - 1] > 1)
                {
                    return false;
                }
            }

            // iterates over the current counts in this column to make sure there's
            // exactly one of each
            for (int i = 0; i < 9; i++)
            {
                if (currentColumn[i] != 1)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
