package assignmentEight_000879513;
import java.util.ArrayList;

/**
 * This class represents the grid part of the grid game. It is responsible for managing the state of the grid, and
 * implementing the logic that will be used to make the grid game function. The class has three instance variables
 * 'pieces' which represents an array list of piece objects, as this class will have an association relationship with
 * the piece class. 'rows' which represents the rows in the grid, and 'columns', which represents the columns in the grid.
 * The grid constructor is responsible for creating an instance of the grid, taking two parameters 'rows' and 'columns'
 * which determines the number of rows and columns the grid will have. Additionally, inside the body of the constructor,
 * the 'pieces' array is initialized storing instances of the piece class (Circles and Squares), and keeping track of the
 * game pieces. The class will also have getter methods for the number of rows, and columns, aswell as methods to manipulate
 * the 'pieces' array list by adding, removing, finding, or clearing pieces in the array list.
 *
 * @author BRENDAN DILEO, 000879513.
 */

public class Grid {
    // Instance variables:
    /**
     * 'pieces' of type ArrayList<>, represents the pieces on/in the game grid.
     */
    private ArrayList<Piece> pieces;
    /**
     * 'rows' of type int, represents the number of rows on the game grid.
     */
    private int rows;
    /**
     * 'columns' of type int, represents the number of columns on the game grid.
     */
    private int columns;

    /**
     * This constructor is responsible for creating an instance of the grid with a certain number of rows, and columns
     * which is specified by the parameters passed to the constructor 'rows' and 'columns'. Based on these parameters,
     * this will be the number of rows and columns that will appear on the game grid. Using the 'this' keyword, both
     * values are assigned to the class's instance variables. Inside the body of the constructor the 'pieces' array list
     * is initialized to an empty array list, as pieces will be added to the array list once they have appeared on the
     * game grid.
     *
     * @param rows, the number of rows the game grid will have.
     * @param columns, the number of columns the game grid will have.
     */
    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        // Initializes an empty array list 'pieces'.
        pieces = new ArrayList<>();
    }

    /**
     * This method 'getPieces' is responsible for retrieving (getting) the array list of pieces.
     *
     * @return pieces, the array list of pieces.
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * This method is responsible for retrieving (getting) the number of rows in the grid.
     *
     * @return rows, the number of rows on the game grid.
     */
    public int getRows() {
        return rows;
    }

    /**
     * This method is responsible for retrieving (getting) the number of columns in the grid.
     *
     * @return columns, the number of columns on the game grid.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * The purpose of the 'addPiece' method is to manipulate the 'pieces' array list by adding a piece to it. This allows
     * for additional pieces to be added to the array list, and allows for the array list to be populated.
     *
     * @param piece, the piece being added to the array list and game grid.
     */
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    /**
     * The purpose of this method 'removePiece' is to allow for the manipulation of the 'pieces' array list by removing
     * a piece from it. This allows for a piece to be removed from the array list when it is no longer on the game grid.
     *
     * @param piece, the piece being removed from the array list, and in turn the game grid.
     */
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * This method is responsible for retrieving a piece that is at a specified row and column on the grid. The method
     * will take two parameters 'row' and 'column' as the row and column that will be checked to see if a piece is there.
     * The method uses a for loop to iterate through the array list, and for each element at index 'i' of the array list,
     * this is saved into a local variable in each iteration. An inner if statement then checks if this piece at the
     * current row and column is the piece being specified in the parameters, and if it is, this piece is returned. If
     * not, meaning it is not the specified piece, the method will return null.
     *
     * @param row, the row on the grid being checked for the piece.
     * @param column, the column on the grid being checked for the piece.
     * @return currentPiece, if the specified piece was found at the row and column. null if the specified piece was not
     * found at the current row and column.
     */
    public Piece getPiece(int row, int column) {
        // Iterates through the array list from 0 to the size of the 'pieces' array list.
        for (int i = 0; i < pieces.size(); i++) {
            // For each iteration, a variable 'currentPiece' of super type piece is assigned to the piece at the current
            // index 'i' using the 'get()' index method.
            Piece currentPiece = pieces.get(i);
            // This if statement checks if the piece object saved into the 'currentPiece' variable is at the specified
            // row and column.
            if (currentPiece.getRow() == row && currentPiece.getColumn() == column) {
                // If the piece was found at this row and column, the piece object at the current iteration is returned.
                return currentPiece;
            }
        }
        // If no piece object was found at the specified row and column, the method will return null as no piece was
        // found.
        return null;
    }

    /**
     * This method is responsible for clearing all the pieces in the 'pieces' array list. This method will be used to
     * clear the pieces off of the grid, which in turn will also clear each piece from the array list, leaving it empty.
     * This method uses to 'clear' method to remove every piece in the array list.
     */
    public void clearPieces() {
        pieces.clear();
    }

}
