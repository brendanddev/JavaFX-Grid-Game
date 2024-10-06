package gridGameV1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a square piece, which is another type of piece in the grid game. As a square is a type of piece,
 * this class will be a subclass of the piece class, inheriting the methods from the piece class (superclass). This class
 * will implement methods and logic that are specific to the square piece. The class has an instance variable 'sideLength'
 * which represents the squares side lengths, a constructor to initialize the parameters by making a call to the
 * superclass constructor, and create an instance of the class/object. The class also has methods to retrieve the value
 * of the side length, and a 'draw' method, which is implemented and overridden from the piece class abstract 'draw'
 * method, which consists of logic that will be specific to drawing the square on the game grid.
 *
 * @author BRENDAN DILEO.
 */

public class Square extends Piece{
    // Instance variables:
    /**
     * 'sideLength' of type double, represents the square pieces side lengths.
     */
    private double sideLength;

    /**
     * This constructor is responsible for creating an instance of the square piece. It takes six parameters, 'row',
     * 'column', 'x', 'y', and 'color' which are initialized by making a call to the superclass constructor, and
     * 'sideLength' which is initialized to the value passed to the constructor with the use of the 'this' keyword.
     *
     * @param row, the row the square piece will appear at on the game grid.
     * @param column, the column the square piece will appear at on the game grid.
     * @param x, the square pieces 'x' coordinate.
     * @param y, the square pieces 'y' coordinate.
     * @param color, the color of the square piece.
     * @param sideLength, the length of each side of the square piece.
     */
    public Square(int row, int column, double x, double y, Color color, double sideLength) {
        // Calls the superclass constructor to initialize variables.
        super(row, column, x, y, color);
        this.sideLength = sideLength;
    }

    /**
     * The purpose of this method is to retrieve (get) the value of the square pieces side length 'sideLength'.
     *
     * @return 'sideLength, the value of the square pieces side length.
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * This method consists of the implementation to draw the square piece on the game grid. It overrides and implements
     * the abstract 'draw' method which is inherited from the piece class, and consists of logic specific to drawing the
     * square piece. The method takes a single parameter 'gc', which is the graphics context that allows for the drawing
     * of the square piece. Inside the method, local variables 'x', 'y', 'sideLength', and 'color' are declared and
     * initialized to the value retrieved from the corresponding getter methods, which are inherited from the piece class.
     * The method then uses the graphics context parameter to set the color of the square using the 'setFill' method,
     * where the color is determined by the variable 'color' of the circle. The square is then actually drawn using the
     * graphics context's 'fillRect' method, where the 'x' value is determined by subtracting the value of the local
     * variable 'x', by the 'sideLength' so the square will appear in the center of its row and column, and the same is
     * done for the 'y' value. The width and height values of the square is determined by the value when multiplying the
     * 'sideLength' by 2, this is done so the square is actually drawn evenly, and in the middle of the row and column.
     *
     * @param gc, the GraphicsContext which allows for the drawing of the square piece.
     */
    @Override
    public void draw(GraphicsContext gc) {
        // Local variables for 'x', 'y', 'sideLength', and 'color' are declared and initialized by calling upon the
        // corresponding getter methods.
        double x = getX();
        double y = getY();
        double sideLength = getSideLength();
        Color color = getColor();

        // Sets the color of the square piece to the color saved into the 'color' variable.
        gc.setFill(color);
        // Draws the 'square' using the 'fillRect' method. The values passed to the 'fillRect' method ensure the square
        // is drawn in the center of the row and column, and that the square is even.
        gc.fillRect(x - sideLength, y - sideLength, 2 * sideLength, 2 * sideLength);
    }
}
