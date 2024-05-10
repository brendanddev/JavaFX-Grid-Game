package assignmentEight_000879513;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This abstract class represents a foundation for the pieces that will be a part of the grid based game board. It will
 * consist of instance variables 'row', 'column', 'x', 'y', 'color', and 'piecesSelected' each of which make up the
 * attributes of a game piece like position, functionality and placement of a given game piece. The class will have a
 * constructor, which will take each instance variable excluding 'pieceSelected' as parameters. The class will also
 * consist of several methods, one of which is an abstract method 'draw', for the purpose of being implemented into a
 * subclass containing the draw logic specific to that class/game piece. The class has other methods for retrieving and
 * changing the variables, like the 'getX', 'getY', 'setX', and 'setY' methods. On top of that, two boolean methods are
 * used to determine if a piece has been selected, or changing which piece has been selected 'isPieceSelected' and
 * 'setSelectedPiece'.
 *
 * @author BRENDAN DILEO, 000879513.
 */

public abstract class Piece {
    // Instance variables:
    /**
     * 'row' of type int, represents the row the piece will appear at.
     */
    private int row;
    /**
     * 'column' of type int, represents the column the piece will appear at.
     */
    private int column;
    /**
     * 'x' of type double, represents the pieces 'x' coordinate.
     */
    private double x;
    /**
     * 'y' of type double, represents the pieces 'y' coordinate.
     */
    private double y;
    /**
     * 'color' of type Color, represents the game pieces color.
     */
    private Color color;
    /**
     * 'pieceSelected' of type boolean, represents a boolean flag determining if a game piece has been selected.
     */
    private boolean pieceSelected;

    /**
     * This constructor is responsible for the instantiation of a game piece. It will take the row, column, x coordinate,
     * y coordinate, radius, and color as parameters, and create a instance of the piece depending on the values passed.
     *
     * @param row, the row the game piece will be in on the game grid.
     * @param column, the column the game piece will be in on the game grid.
     * @param x, the 'x' coordinate the game piece will appear at.
     * @param y, the 'y' coordinate the game piece will appear at.
     * @param color, the color of the game piece.
     */
    public Piece(int row, int column, double x, double y, Color color) {
        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * This abstract method is responsible for declaring the foundation of the 'draw' method that will be implemented in
     * the subclasses of this class (piece class). It will be implemented in order to actually draw the game piece based
     * upon the parameters passed to the instance of the object/class.
     *
     * @param gc, the graphics context the piece will be drawn on the canvas with.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * This method is responsible for retrieving (getting) the 'x' coordinate that the game piece will appear at.
     *
     * @return 'x', the game pieces 'x' coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * This method 'setX' is responsible for changing (setting) the game pieces 'x' coordinate.
     *
     * @param x, the 'x' coordinate the game piece will now appear at.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This method is responsible for retrieving (getting) the game pieces 'y' coordinate.
     *
     * @return 'y', the game pieces 'y' coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * This method 'setY' is responsible for changing (setting) the game pieces 'y' coordinate.
     *
     * @param y, the 'y' coordinate the game piece will now appear at.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method retrieves (gets) the color of the game piece.
     *
     * @return color, the game pieces color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method will retrieve (gets) the row that the game piece appears at in the game grid.
     *
     * @return row, the row that the game piece is at.
     */
    public int getRow() {
        return row;
    }

    /**
     * This method is responsible for retrieving (getting) the column that the game piece appears at on the game grid.
     *
     * @return column, the column that the game piece is at.
     */
    public int getColumn() {
        return column;
    }

    /**
     * This method is responsible for changing the position of the game piece. It takes two parameters, 'row' and
     * 'column' representing the new spot on the grid the game piece will now appear at.
     *
     * @param row, the game pieces new row on the game grid.
     * @param column, the game pieces new column on the game grid.
     */
    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * This method is used to determine whether or not a game piece has been selected by the user. It returns the value
     * of the instance variable 'pieceSelected', which will either hold true or false depending on whether the specified
     * piece has been selected.
     *
     * @return pieceSelected, a boolean flag determining whether or not a game piece has been selected.
     */
    public boolean isPieceSelected() {
        return pieceSelected;
    }

    /**
     * This method 'setSelectedPiece' is used to directly alter (change/set) the status of the boolean flag,
     * 'pieceSelected'. This will be used to change the status of the boolean flag, which allows for the manipulation of
     * whether or not the user has actually selected a flag. The method takes a single boolean parameter 'pieceSelected'
     * which represents the status the flag will be changed to. The method then changes the status of the boolean flag
     * to that value with the use of the 'this' keyword.
     *
     * @param pieceSelected, the boolean value the flag will now hold.
     */
    public void setSelectedPiece(boolean pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

}
