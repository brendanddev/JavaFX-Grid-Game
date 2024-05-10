package assignmentEight_000879513;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a circle piece, which is a type of piece in the grid game. This class is a subclass of the piece
 * class, as it will inherit the methods from the piece class (superclass). This class will implement methods and logic
 * that is specific to the circle piece. It will consist of an instance variable 'radius' for the circles radius, a
 * constructor which calls to the superclass constructor (piece class) to initialize the parameters, a 'getRadius' method
 * to retrieve the circles radius, and the implementation for the overridden abstract 'draw' method inherited from the
 * piece class which will draw the circle piece on the game grid.
 *
 * @author BRENDAN DILEO, 000879513.
 */

public class Circle extends Piece{
    // Instance variables:
    /**
     * 'radius' of type double, represents the circle pieces radius.
     */
    private double radius;

    /**
     * The circle class constructor is used to create an instance of the circle piece. The constructor will take six
     * parameters, each of which help to construct the circle piece. The first five parameters consist of 'row', 'column',
     * 'x', 'y', 'color' which are initialized through a call to the superclass constructor, and the last parameter
     * 'radius' is initialized to the value passed to the constructor with the use of the 'this' keyword.
     *
     * @param row, the row the circle piece will appear at on the game grid.
     * @param column, the column the circle piece will appear at on the game grid.
     * @param x, the circle pieces 'x' coordinate.
     * @param y, the circle pieces 'y' coordinate.
     * @param color, the color of the circle piece.
     * @param radius, the circle pieces radius.
     */
    public Circle(int row, int column, double x, double y, Color color, double radius) {
        // Calls the superclass constructor to initialize variables.
        super(row, column, x, y, color);
        this.radius = radius;
    }

    /**
     * This method is responsible for retrieving (getting) the circle piece's radius.
     *
     * @return radius, the radius of the circle piece.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This method is responsible for implementing the 'draw' methods logic, which contains the logic that is specific
     * to drawing the circle piece. First the method uses the inherited getter methods to save the values of 'x', 'y',
     * and 'color' into local variables to then draw the circle using the graphics context parameter. The method draws
     * the circle using the 'setFill' and 'fillOval' method, both of which take the local variables as parameters. The
     * method then checks if the circle is selected by the user using the 'isPieceSelected' method from the piece class,
     * and if it is, a stroke (outline) is added to the circle to reflect the fact that the user has selected it. Another
     * local variable 'lineWidth' was declared and initialized to the initial line width, and then set back to the initial
     * line width so the grid lines making up the grid game do not get changed with the use of the setting of the line's
     * width.
     *
     * @param gc, the GraphicsContext that allows the method to draw the circle piece.
     */
    @Override
    public void draw(GraphicsContext gc) {
        // Local variables for 'x', 'y', 'radius', and 'color' are declared and initialized to the value of the
        // corresponding getter method.
        double x = getX();
        double y = getY();
        double radius = getRadius();
        Color color = getColor();

        // Sets the color of the drawing to the variable 'color' which holds the circle's color.
        gc.setFill(color);
        // Draws the circle using the 'fillOval' method. Subtracts the 'radius' from both the 'x' and 'y' to ensure that
        // the circle piece is drawn in the center of the given grid row and column. The width and height of the circle
        // will equal the value of multiplying the radius by 2, so the circle is drawn evenly.
        gc.fillOval(x - radius, y - radius, 2 * radius,2 * radius);

        // Checks if the circle piece is selected by calling upon the 'isPieceSelected' method from the piece class.
        if (isPieceSelected()) {
            // Stores the initial line width into a local variable 'lineWidth' so it can be reaccessed after the selected
            // pieces line width is set.
            double lineWidth = gc.getLineWidth();
            // Sets the stroke (outline) of the selected piece to red.
            gc.setStroke(Color.RED);
            // Sets the width of the stroke (outline) to 2 pixels.
            gc.setLineWidth(2);
            // Draws the stroke around the oval, similarly to how the circle piece itself is drawn, but with a stroke
            // around it this time.
            gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
            // Restores the previous line width by setting the graphic contexts line width back to its initial value.
            gc.setLineWidth(lineWidth);
        }
    }
}
