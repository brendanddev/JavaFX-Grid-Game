package gridGameV1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is the main class, and is where the user will interact with the game/grid. This class acts as the view
 * class by handling the GUI components and implementing them, but will also consist of some model class logic to help
 * the game function. It emphasizes the use of mouse events, exception handling, and alerts to allow the user to interact
 * with the grid game. The class consists of a graphical user interface, by incorporating the use of a canvas, graphics
 * context, labels, text fields, and buttons, each of which are declared as instance variables for the class. The other
 * instance variables are used for the grid that will appear on the interface, a circle type variable to determine if a
 * circle piece has been selected, and instances of the player class. In addition to initializing, relocating, and styling
 * each of the gui components, the class will also have helper methods and event handlers which allow the user to place,
 * remove, select, or move the circle pieces, and place, select, or remove the square pieces. Each of the helper methods
 * are responsible for specific parts of the game grid, like drawing the games grid, redrawing the games grid, placing a
 * circle piece, removing a circle piece, moving a circle piece, removing all the pieces on the games grid, changing the
 * context of the label showing which players turn, and placing a square piece. The circle pieces will need the use of
 * the primary mouse button to be interacted with, and the square pieces rely on the middle mouse button to be interacted
 * with, both of which rely on the use of mouse events to function. Each helper method that relies on the users input or
 * the use of the users mouse, will incorporate exception handling to ensure that each action is validated.
 *
 *
 * @author BRENDAN DILEO
 */
public class GridGameMain extends Application {

    // Instance variables for view components and model:
    /**
     * 'canvas' of type Canvas, represents the area where the grid will be drawn on.
     */
    private Canvas canvas;
    /**
     * 'gc' of type GraphicsContext, represents an object that allows for use of colors, and the drawing of shapes on
     * the canvas.
     */
    private GraphicsContext gc;
    /**
     * 'grid' of type Grid, represents the grid model that the users will place pieces on, and interact with.
     */
    private Grid grid;
    /**
     * 'infoLabel' of type Label, represents a label that will be displayed on the users screen providing insight on how
     * to use the game.
     */
    private Label infoLabel;
    /**
     * 'rowText' of type TextField, represents a text field that will take a specified row as user input.
     */
    private TextField rowText;
    /**
     * 'columnText' of type TextField, represents a text field that will take a specified column as user input.
     */
    private TextField columnText;
    /**
     * 'removeLabel' of type Label, represents a label that tells the user that the following components are used to
     * remove pieces.
     */
    private Label removeLabel;
    /**
     * 'removeButton' of type Button, represents a button that can be used to remove a piece on the game grid.
     */
    private Button removeButton;
    /**
     * 'removeAllLabel' of type Label, represents a type of label that tells the user that the following components are
     * used to remove all the pieces off of the game grid.
     */
    private Label removeAllLabel;
    /**
     * 'removeAllButton' of type Button, represents a button that can be used to remove all the game pieces off of the
     * game grid.
     */
    private Button removeAllButton;
    /**
     * 'menuLabel' of type Label, represents a label where all the components following this label will be a part of
     * the games 'menu'.
     */
    private Label menuLabel;
    /**
     * 'selectedCircle' of type Circle, represents a variable of type circle which will keep track of which circle has
     * been selected by the user. It is initialized to null until a circle piece has been selected.
     */
    private Circle selectedCircle = null;
    /**
     * 'playerOne' of type Player, represents the first player in the grid game.
     */
    private Player playerOne;
    /**
     * 'playerTwo' of type Player, represents the second player in the grid game.
     */
    private Player playerTwo;
    /**
     * 'currentPlayer' of type Player, represents an instance of the player class where the current player is the player
     * whose turn it is.
     */
    private Player currentPlayer;
    /**
     * 'upNextTurnLabel' of type Label, represents a label indicating that the label following this label indicates the
     * next users/players turn.
     */
    private Label upNextTurnLabel;
    /**
     * 'turnLabel' of type Label, represents a label indicating which players turn is next.
     */
    private Label turnLabel;

    // Private event handlers and helper methods:
    /**
     * This method 'drawGrid' is responsible for drawing the grid, and its lines on the canvas. The method takes two
     * parameters, 'gc' representing the graphics context that will be drawing the lines, and 'grid', which represents
     * the gui 'model', tracking the number of rows, columns, and the number of pieces on the grid. It will store the
     * number of rows and columns the grid will have into two local variables 'rows' and 'columns', where the number of
     * rows and columns are accessed using the getter methods from the grid class. Once the number of rows and columns
     * have been retrieved and saved into the corresponding local variable, the method then determines the height of the
     * rows, and the width of the columns based on the height and width of the canvas itself, and the number of rows
     * and columns the grid will have. This is done because the assignment mentions that the grid should consist of
     * squares/cells, where each is drawn with the same size. The calculations for the height of the rows 'rowHeight',
     * and the width of the columns 'columnWidth', ensure that each of the rows and columns and the cells that make up
     * the grid are all drawn evenly and relative to one another. In order to actually draw the lines for the rows and
     * the columns of the grid, two for loops are used to iterate over each row and column the grid will have, and for
     * every row and column that has been found, a line is drawn to show the row or column.
     *
     * @param gc, the GraphicsContext that will be used to draw the lines of the grid.
     * @param grid, the grid object that contains all the information like the rows and columns of the grid.
     */
    private void drawGrid(GraphicsContext gc, Grid grid) {
        // Declares local variables and initializes them to the value retrieved by the corresponding getter method.
        int rows = grid.getRows();
        int columns = grid.getColumns();

        // By using the graphics context, the color of the strokes (lines) are set to black.
        gc.setStroke(Color.BLACK);
        // A new local variable 'rowHeight' is declared and initialized to the height of the rows which is determined by
        // the overall height of the canvas divided by the number of rows. This allows for each grid cell (grid square)
        // to be drawn evenly along the canvas.
        double rowHeight = canvas.getHeight() / rows;
        // A new local variable 'columnWidth' is declared and initialized to the width of the columns determined by the
        // overall width of the canvas, and the number of columns the grid will have. This allows for each grid cell to
        // be drawn evenly along the canvas.
        double columnWidth = canvas.getWidth() / columns;

        // This for loop is responsible for iterating through the number of rows the grid will have, and for each row,
        // a new line is drawn reflecting the row.
        for (int i = 0; i <= rows; i++) {
            // For every row, a line is drawn with the use of the graphics context, where the 'x' coordinates of the line
            // rely on the start, and end of the canvas, so the line goes across the entire canvas, and the 'y'
            // coordinates are determined by the current rows index 'i', and height of the rows 'rowHeight'.
            gc.strokeLine(0, i * rowHeight, canvas.getWidth(), i * rowHeight);
        }
        // This for loop is responsible for iterating through the number of columns the grid will have, and for each
        // column, a new vertical line is drawn reflecting each column.
        for (int i = 0; i <= columns; i++) {
            // For every column, a line is drawn using the graphics context 'gc', where the 'x' coordinates of the line
            // are determined by the current column index 'i', and the width of the columns, and the 'y' coordinates of
            // the columns rely on the start of the top of the canvas, and the end of the bottom of the canvas.
            gc.strokeLine(i * columnWidth, 0, i * columnWidth, canvas.getHeight());
        }
    }

    /**
     * This method is responsible for redrawing the grid on the canvas, along with any pieces that remain on the canvas.
     * The method will first clear the entire canvas/grid by using the graphics context 'clearRect' method, and then
     * redrawing the grid lines (rows and columns) by calling upon the 'drawGrid' method. To ensure that if the grid is
     * redrawn, that the pieces remain on the grid until they are removed, a for loop is used to iterate through the
     * array list of pieces, accessed by the 'getPieces' method from the grid class. For every piece that is found in the
     * list accessed using the 'get' method by the index 'i', meaning any pieces that have not been removed, they are
     * drawn back onto the canvas using the 'draw' method from the grid class.
     */
    private void redrawGrid() {
        // Clears the current canvas.
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Calls the 'redrawGrid' method to have the grid redrawn.
        drawGrid(gc, grid);
        // Redraws each piece that was on the grid by accessing the array list of pieces, and for each piece that was
        // found, it redraws the piece.
        for (int i = 0; i < grid.getPieces().size(); i++) {
            grid.getPieces().get(i).draw(gc);
        }
    }

    /**
     * This method is responsible for allowing the user to place a circle piece on the canvas using the click of the
     * primary mouse button. This method uses a try and catch block to ensure that the user is placing the circle in a
     * valid position, and does not either exceed the grid itself, or overlap with a current circle placement. Inside
     * the block, an if statement is used to check if the mouse event triggering this method was the primary mouse button,
     * as this method will need the click of the first mouse button to work. If it was the first mouse button, two local
     * variables 'columnWidth' and 'rowHeight' are declared as the width and height of each row and column and cell/square
     * in the grid, and the values are determined by the size of the canvas, divided by the number of columns or rows.
     * In order to determine where the user intends to place the circle, two new local variables 'row' and 'column' are
     * declared and initialized to the result of dividing the users click along the 'y' axis and the height of the grids
     * cells for the 'row', and the users click along the 'x' axis and the width of each cell for the 'column', both of
     * which are casted to integers. Once the row and column the user wants to place the circle in has been determined,
     * the actual position of the circle is calculated as 'circleXPos' and 'circleYPos' so once the circle is placed by
     * the user in a grid cell, it is placed in the middle of the cell, and not just placed in the bottom corner. The
     * nested if statement then checks if there is a piece at the row and column the user is trying to place the circle
     * piece at, and if there isnt, the color of the circle piece is determined by the players color, and a new piece is
     * created with declaration type piece, and actual type circle, and the piece is added to the array list. The grid is
     * then redrawn, and the piece is drawn on the grid/canvas. The next if statement is used to determine which players
     * turn it is, by checking the variable 'currentPlayer', and whether it is currently 'playerOne', or 'playerTwo', as
     * the turn label will change depending on which players turn it is. The catch block at the end of the method checks
     * for an illegal state exception, which will occur if the users circle placement is invalid, and if it is, a print
     * line will be output to the console, and the user will see a warning alert as the assignment required.
     *
     * @param me, the MouseEvent that will trigger this method/action.
     */
    private void placeCircle(MouseEvent me) {
        // Try block to ensure the users circle piece placement does not result in an exception.
        try {
            // If statement checks if the mouse button the user has pressed is the primary (first) mouse button.
            if (me.getButton().equals(MouseButton.PRIMARY)) {
                // The local variable 'rowHeight' is declared and initialized to the result of dividing the height of the
                // canvas by the number of rows. This allows for the height of the rows to be accessed.
                double rowHeight = canvas.getHeight() / grid.getRows();
                // The local variable 'columnWidth' is declared and initialized to the result of dividing the width of the
                // canvas by the number of columns. This allows for the width of the columns to be easily accessed.
                double columnWidth = canvas.getWidth() / grid.getColumns();

                // The local variable 'row' tracks which row the user has clicked by accessing the users click along the
                // 'y' axis, and the height of each row. The value is then casted to an integer, and saved into the 'row'
                // variable.
                int row = (int) (me.getY() / rowHeight);
                // The local variable 'column' tracks which column the user has clicked by accessing the users click
                // along the 'x' axis, and the width of the columns. The value is casted to an integer, and saved into
                // the 'column' variable.
                int column = (int) (me.getX() / columnWidth);

                // The variable 'circleXPos' determines where the circle piece will be placed inside the grid column
                // based upon where the user has clicked, so it appears in the middle.
                double circleXPos = column * columnWidth + columnWidth / 2;
                // The variable 'circleYPos' determines where the circle piece will be placed inside the grids row based
                // on where the user has clicked, so it appears in the middle.
                double circleYPos = row * rowHeight + rowHeight / 2;
                // The radius of the circle piece.
                double radius = 10;

                // This if statement checks if the row and column the user has clicked on the grid is empty (null).
                if (grid.getPiece(row, column) == null) {
                    // If it is empty, the color of the circle piece will be the color of the player, this is retrieved
                    // by using the getter method for the color from the player class.
                    Color pieceColor = currentPlayer.getPlayerColor();
                    // A new circle piece is created as 'circlePiece' with a declaration type Circle, and an actual type
                    // circle, and the circle constructor takes each local variable as parameters.
                    Circle circlePiece = new Circle(row, column, circleXPos, circleYPos, pieceColor, radius);
                    // The circle piece is added to the grid array list by using the 'addPiece' method from the grid
                    // class.
                    grid.addPiece(circlePiece);

                    // The grid is redrawn to reflect the changes.
                    redrawGrid();
                    // The circle piece that the user has placed is drawn on the grid.
                    circlePiece.draw(gc);

                    // This if statement checks which players turn it is.
                    if (currentPlayer == playerOne) {
                        // If the current player is the first player, the turn will switch and it will now be the second
                        // players turn.
                        currentPlayer = playerTwo;
                    } else {
                        // If it was the second players turn, it will now be the first players turn.
                        currentPlayer = playerOne;
                    }
                    // The 'changeTurnLabel' method is called upon so the turn label accurately reflects which players
                    // turn it is.
                    changeTurnLabel();
                // This else block will be executed if the row and column is not null, which means there is already a
                // circle at this position.
                } else {
                    // An alert will be shown, mentioning that the spot is taken, and a circle cannot be placed here.
                    new Alert(Alert.AlertType.ERROR, "Cannot place Circle! The spot is taken!").showAndWait();
                    // A new exception will be thrown as the circle cannot be placed at the selected spot.
                    throw new IllegalStateException("Cannot place the Circle piece here, the spot is taken!");
                }
            }
            // The catch block is waiting for an exception to occur, if the user has tried to place the circle at an
            // invalid spot, the catch block will be executed, printing a line to the console, and prompting the user
            // with a warning as specified in the assignment.
        } catch(IllegalStateException e) {
            // Console error message.
            System.out.println(e.getMessage());
            // Visual alert warning.
            new Alert(Alert.AlertType.ERROR, "Cannot place Circle here!");
        }
    }

    /**
     * This method is responsible for letting the user remove a circle piece from the canvas if a circle piece has been
     * placed using the text fields and the button. The assignment states that the user should be able to remove a piece
     * using a row text field, a column text field, and on the click of the button. The user will enter a row and column
     * into the corresponding text field, and press the remove button to remove the piece, if no piece is found at this
     * position, or the user has entered an invalid value into either of the text fields, they will be prompted with
     * a warning, and an exception will occur. The method saves the row and column the user would like to remove the
     * piece at into local variables, checks if there is actually a piece at the position, and if there is, the piece
     * is removed from the array list, and the grid is redrawn to simulate the fact that the piece has been removed.
     *
     * @param e, the ActionEvent that triggers this method.
     */
    private void removeCircle(ActionEvent e) {
        // Try block to ensure the circle piece the user is trying to remove does not result in an exception.
        try {
            // The user input into the text field for the row is retrieved and parsed to an integer.
            int row = Integer.parseInt(rowText.getText());
            // The user input into the column text field is retrieved and parsed to an integer.
            int column = Integer.parseInt(columnText.getText());

            // This if statement checks if the row and column the user has entered into the text fields are valid,
            // ensuring they are greater than or equal to 0, and less than or equal to the number of rows and columns
            // that the grid actually has.
            if (row >= 0 && row < grid.getRows() && column >= 0 && column < grid.getColumns()) {
                // If the row and column entered is accurate and within the grids rows and columns, this nested if
                // statement checks to see if the position isnt null, which means there is actually a piece here.
                if (grid.getPiece(row, column) != null) {
                    // If there is a piece here, the 'removePiece' method is called from the grid class, removing the
                    // piece from the position, and in turn the array list too.
                    grid.removePiece(grid.getPiece(row, column));
                    // The grid is redrawn to reflect the changes.
                    redrawGrid();
                // This else block will be executed if the user has entered a row or column that does not have a piece.
                } else {
                    // They will be prompted with an alert message mentioning there is no piece at this spot.
                    new Alert(Alert.AlertType.WARNING, "No piece was found at this position!").showAndWait();
                }
            // This else block will be executed if the row or column the user has entered is invalid.
            } else {
                // The user will be prompted with a warning message saying that the row or column they have entered is
                // invalid.
                new Alert(Alert.AlertType.WARNING, "Invalid row or column!").showAndWait();
            }
        // This catch block is waiting for an exception to occur, particularly if the user enters a value other than an
        // number. If the user does enter an invalid input, a message will be printed to the console, and an alert will
        // be prompted to the screen saying that the user needs to enter numbers.
        } catch (NumberFormatException ex) {
            // Print statement to the console.
            System.out.println("You must enter valid integers!" + " " + ex.getMessage());
            // Warning alert to the users screen.
            new Alert(Alert.AlertType.WARNING, "Please enter valid numbers.").showAndWait();
        }
    }

    /**
     * This method 'selectCircle' is responsible for handling the logic when the user selects a circle piece on the grid.
     * It will calculate the height of the rows, and the width of the columns, and which row and column the user has
     * selected the piece in. A variable 'clickedPiece' is declared and initialized to the circle piece that was clicked
     * at the specified row and column which is determined by the users click on the grid. An if statement then checks if
     * the piece that was clicked is an instance of the circle class, and if it is, the variable is casted to type circle,
     * and then a nested if statement is used to check if there is already a circle selected, and that it is not the
     * current circle the user is trying to select, it will set the selection of the circle that is currently selected
     * to false using the 'setSelectedPiece' method from the circle class , to reflect that a new piece is going to be
     * selected. Outside the if statement, the 'setSelectedPiece' method from the circle class sets the 'clickedCircle'
     * variables selection to true, as it is not currently selected. The 'selectedCircle' variable is assigned to the
     * circle clicked by the user, and then the grid is redrawn to reflect the changes. The catch block is waiting for
     * an exception to occur from an invalid action from the user.
     *
     * @param me, the MouseEvent that will trigger the method.
     */
    private void selectCircle(MouseEvent me) {
        // This try block is used to ensure that in case of exception the user is prompted with the reason causing the
        // exception.
        try {
            // The local variable 'rowHeight' is declared and initialized to the result of dividing the height of the
            // canvas by the number of rows. This allows for the height of the rows to be accessed.
            double rowHeight = canvas.getHeight() / grid.getRows();
            // The local variable 'columnWidth' is declared and initialized to the result of dividing the width of the
            // canvas by the number of columns. This allows for the width of the columns to be easily accessed.
            double columnWidth = canvas.getWidth() / grid.getColumns();

            // The local variable 'row' tracks which row the user has clicked by accessing the users click along the
            // 'y' axis, and the height of each row. The value is then casted to an integer, and saved into the 'row'
            // variable.
            int row = (int) (me.getY() / rowHeight);
            // The local variable 'column' tracks which column the user has clicked by accessing the users click
            // along the 'x' axis, and the width of the columns. The value is casted to an integer, and saved into
            // the 'column' variable.
            int column = (int) (me.getX() / columnWidth);

            // This if statement uses the 'getPiece' method from the grid class to check if the piece that was selected
            // by the user is an instance of the circle class using the 'instanceof' keyword.
            if (grid.getPiece(row, column) instanceof Circle) {
                // If the selected piece was an instance of a circle, the piece is casted to type circle, and saved into
                // a local variable 'clickedPiece'.
                Circle clickedCircle = (Circle) grid.getPiece(row, column);
                // This nested if statement checks if the variable holding the current selected circle 'selectedCircle'
                // is not empty (null), and if the variable does not currently equal the 'clickedCircle' variable,
                // meaning that the current circle the user is trying to select is not the current circle selected.
                if (selectedCircle != null && selectedCircle != clickedCircle) {
                    // The current circle piece that is selected is set to false using the 'setSelectedPiece' method to
                    // reflect that this piece is no longer selected, and the user is selecting a new piece.
                    selectedCircle.setSelectedPiece(false);
                }
                // The circle pieces the user will be selecting status is set to the negation of whether or not the circle
                // is currently selected using the 'setSelectedPiece' and 'isPieceSelected' methods from the circle
                // class, this will allow the user to select, and then deselect the circle.
                clickedCircle.setSelectedPiece(!clickedCircle.isPieceSelected());
                // The 'selectedCircle' variable holding the circle piece that is currently selected is then set to the
                // circle the user has currently clicked, this reflects the fact that the circle is now selected.
                selectedCircle = clickedCircle;
                // The grid is then redrawn to reflect the changes.
                redrawGrid();
            }
        // The catch block is waiting for an exception, where the type of exception is 'Exception', as it is not waiting
        // for a specific exception, but any that could occur as a result of the user incorrectly selecting a piece.
        } catch (Exception e) {
            // The reason for the exception is printed to the console so the user can see why it has occurred.
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is responsible for moving a circle piece along the grid on the canvas. It will first make sure that
     * a circle piece has been selected by the user, as the circle cannot be moved without first being selected. If a
     * circle piece has been selected, the height of the rows, and width of the columns on the grid are calculated, and
     * the users row and column the user has clicked is saved into the local variables 'row' and 'column'. An if
     * statement then will check if the position the user is trying to move the selected circle to is empty, as it must
     * be empty to move the piece there. If it is empty (null), the current selected circle piece is removed from the
     * grid, and in turn the array list to reflect the fact the circle is no longer at that position. The variable
     * 'selectedCircle' containing the current circle piece that is selected by the user, is moved to the row and column
     * the user is moving the circle piece to using the 'setPosition' method from the circle class, and the 'x' and 'y'
     * coordinates of the circle piece is changed using the 'setX' and 'setY' methods to further reflect the fact that
     * the circle piece has been moved. The circle piece that is selected, and has now been moved by the user is re added
     * back into the array list at its new position, and is no longer selected by the user as its selection status is
     * set to false. The 'selectedCircle' variable is set to null as the circle is no longer selected by the user, and
     * the grid is redrawn to reflect the changes made. The catch block is waiting for an exception to occur, and will
     * p[prompt the user with a warning in the console if an exception does occur.
     *
     * @param me, the MouseEvent that triggers this method.
     */
    private void moveCircle(MouseEvent me) {
        // The try block will 'try' the code in the body of the block, and if an exception occurs will execute the code
        // in the catch block that is waiting for an exception to occur.
        try {
            // This if statement is checking to see if a circle piece is currently selected by the user, as the user
            // will need to select a circle piece to move it.
            if (selectedCircle != null) {
                // The local variable 'rowHeight' is declared and initialized to the result of dividing the height of the
                // canvas by the number of rows. This allows for the height of the rows to be accessed.
                double rowHeight = canvas.getHeight() / grid.getRows();
                // The local variable 'columnWidth' is declared and initialized to the result of dividing the width of the
                // canvas by the number of columns. This allows for the width of the columns to be easily accessed.
                double columnWidth = canvas.getWidth() / grid.getColumns();

                // The local variable 'row' is assigned to the row the user has clicked by dividing the users click
                // position along the 'y' axis by the height of the rows.
                int row = (int) (me.getY() / rowHeight);
                // The local variable 'column' is assigned to the column that the user has clicked which is determined
                // by dividing the position where the user has clicked along the 'x' axis, by the width of columns on
                // the grid.
                int column = (int) (me.getX() / columnWidth);

                // The if statement checks if the position on the grid that the user has chosen to move the circle piece
                // to is empty, as it must be empty inm order for the user to move the circle piece there.
                if (grid.getPiece(row, column) == null) {
                    // If the position (grid cell) is empty, the circle piece the user is moving, which will be the
                    // selected circle, is removed from the grid and the array list by calling upon the 'removePiece'
                    // method from the grid class.
                    grid.removePiece(selectedCircle);

                    // The position of the circle piece the user has selected to move is changed to the new row and
                    // column the user will be moving the circle to.
                    selectedCircle.setPosition(row, column);
                    // To make sure the circle has actually moved, the 'x' axis of the circle is changed to the new
                    // column the circle piece will now be appearing at.
                    selectedCircle.setX(column * columnWidth + columnWidth / 2);
                    // Similarly, the 'y' axis of the circle piece is changed to the new row the circle piece will now
                    // be appearing at.
                    selectedCircle.setY(row * rowHeight + rowHeight / 2);
                    // The circle piece being moved, which is also the circle piece selected 'selectedCircle' is re added
                    // to the array list and the grid too.
                    grid.addPiece(selectedCircle);

                    // The circle piece the user has selected to move is de selected after moving it using the
                    // 'setSelectedPiece' method from the circle class so the circle piece is no longer selected after
                    // being moved by the user.
                    selectedCircle.setSelectedPiece(false);
                    // The variable holding the circle piece selected on the grid is set to null as after the user has
                    // moved a circle, no circle piece is selected.
                    selectedCircle = null;
                    // The grid is redrawn to reflect each change.
                    redrawGrid();
                }
            }
        // This catch block will be executed if the movement from the user results in any exception. The 'Exception' type
        // of exception will execute if any exception occurs, and if one does occur, a message will be printed to the
        // console letting the user know what caused it.
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is responsible for clearing all pieces off of the grid/removing all the pieces oin the grid. This
     * method uses an ActionEvent 'e' as a parameter, and will be invoked on the click of a button. Inside the method
     * is a try and catch block, to ensure if an exception occurs, the user will get an alert. Inside the try block,
     * is a call to the 'clearPieces' method in the grid class, which will clear all the pieces off of the grid/canvas,
     * and will also clear the array list which contains all the pieces. After the pieces have been cleared, the grid
     * is redrawn to reflect that all the pieces have been taken off of the board. If the user tries to clear and empty
     * grid/canvas, meaning they try to clear the grid when there are no pieces on the grid, the catch block will be
     * executed, and the user method will print a line to the console, and an alert prompt will be shown so the user
     * knows they have encountered an error/exception.
     *
     * @param e, the ActionEvent that will trigger this method.
     */
    private void removeAllPieces(ActionEvent e) {
        // The code inside of the try block will be executed unless an exception has occurred. If one does occur, the
        // code inside of the catch block is executed.
        try {
            // This if statement checks if the grid has no pieces on it / the 'pieces' array list is already empty when
            // the user attempts to clear it.
            if (grid.getPieces().isEmpty()) {
                // If it is already empty, an exception will be thrown, and the catch block will be executed.
                throw new IllegalArgumentException("The board is already empty!");
            // If the grid is not already empty, then the else block will be executed.
            } else {
                // The pieces that are on the grid will all be removed from the array list by calling upon the 'clearPieces'
                // method from the grid class, which in turn will remove all the pieces on the grid.
                grid.clearPieces();
                // The grid is redrawn so the grid is showing with no pieces to reflect the changes.
                redrawGrid();
            }
        // If an exception occurs, meaning the user has tried to clear an empty board, the catch block will be executed.
        } catch (IllegalArgumentException ex) {
            // A print line will be output to the console telling the user that the board they are trying to clear is
            // already empty.
            System.out.println("The board is already empty!");
            // An alert warning is prompted to the users screen as mentioned in the assignment.
            new Alert(Alert.AlertType.ERROR, "The board is already empty!").showAndWait();
        }
    }

    /**
     * This method is responsible for changing the contents of the label which indicates what players turn it is. The
     * method checks the current players 'currentPlayer' number by calling the 'getPlayerNum' method from the player
     * class, and checks if it is equal to 1 as this means it is the first player. If it is the first players turn,
     * the text of the 'turnLabel' label is changed to the player ones number, and the background of the label is set to
     * green as the first players color is green. If the current players number is not 1, this means it is the second
     * players turn, so the text will be changed to the second players number, and the label will be changed to blue to
     * reflect the second players turn and color.
     */
    private void changeTurnLabel() {
        // This if statement checks if the current player 'currentPlayer''s number is 1 by using the 'getPlayerNum'
        // method from the player class.
        if (currentPlayer.getPlayerNum() == 1) {
            // If the current players number is 1, meaning the current player is player one, the text of the turn label
            // is changed to reflect that it is the first players turn, and the background color is changed to green as
            // the first players color is green.
            turnLabel.setText("Player " + currentPlayer.getPlayerNum() + "'s turn");
            turnLabel.setStyle("-fx-background-color: green");
        // If the current player is nit player one, this means the current player is player two, then the else block will
        // be executed.
        } else {
            // The text in the turn label is changed to the number of the second player (2), and the background color of
            // the label is changed to blue as the second players color is blue. The color of the text in the label is
            // changed to white aswell as the black default text color was nto showing well.
            turnLabel.setText("Player " + currentPlayer.getPlayerNum() + "'s turn");
            turnLabel.setStyle("-fx-background-color: blue; -fx-text-fill: white");
        }
    }

    /**
     * The purpose of this method is to allow the user to place a square piece on the grid/canvas. It will consist of
     * the similar logic that is implemented in order to allow the user to place a circle piece, however in this case it
     * will be placing a square piece. The square piece will not use a radius, but instead will use a 'sideLength' to
     * determine the size of the square. The method will use a try and catch blow to ensure that  the user is placing the
     * square in a valid position, and does not either exceed the grid itself, or overlap with a current square or circle
     * placement. The method takes a single parameter 'me' of type MouseEvent, which means that this method will be invoked
     * with the click of a mouse. In order to place a square on the grid/canvas, the user must press the middle mouse
     * button, which is why the if statement first checks if the button the user has pressed is the middle button. Two
     * local variables are declared and initialized to the height of the rows 'rowHeight', and the width of the columns
     * 'columnWidth'. Four additional local variables are declared 'row' and 'column' which are initialized to the row
     * and column the user has chosen to place the square at, which is determined by where they have clicked. 'squareXPos'
     * and 'squareYPos' which are initialized to the middle of the column and row where the square will be appearing in.
     * The following if statement checks if the row and column the user has selected is empty (null), and if it is, the
     * players color is retrieved using the 'getPlayerColor' method from the player class, and saved into a variable
     * 'pieceColor' of type color, as the color of the piece determined by the players color. A new square piece is
     * created 'squarePiece', with declaration type piece, and actual type square, and added to the array list 'pieces'
     * using the 'addPiece' method from the grid class, to reflect the square is now on the grid. The grid is then redrawn,
     * and the square piece itself is drawn on the grid, to reflect the fact that the square piece has been placed on the
     * grid. The nested if statement then checks which players turn it is, by comparing the variable 'currentPlayer',
     * and the first player, as if it is the first player, it will then be the second players turn, and if it is the
     * second players turn, it will then be the first players turn. The catch block will be checking for an illegal state
     * exception, which will occur if the users square placement is invalid, and if it is, a print line will be output
     * to the console, and the user will see a warning alert as the assignment required.
     *
     * @param me, the MouseEvent that will trigger the method.
     */
    private void placeSquare(MouseEvent me) {
        // This try block will contain code that will be 'tried', and if any exceptions occur then the catch block will
        // be executed.
        try {
            // This if statement checks if the mouse button the user has pressed is the middle mouse button.
            if (me.getButton().equals(MouseButton.MIDDLE)) {
                // The variable 'rowHeight' is declared and initialized to the height of each row.
                double rowHeight = canvas.getHeight() / grid.getRows();
                // The variable 'columnWidth' is declared and initialized to the width of each column.
                double columnWidth = canvas.getWidth() / grid.getColumns();

                // The variable 'row' is declared and initialized to the result of the users click along the 'y' axis
                // divided by the height of the row. It is casted to an int to avoid any inconsistencies in terms of
                // decimal places. This will determine which row the user has attempted to place the square at.
                int row = (int) (me.getY() / rowHeight);

                // The local variable 'column' is declared and initialized to the result of the users click along the
                // 'x' axis divided by the width of each column. It is then casted to an integer to remove any decimal
                // places and avoid any inconsistencies in terms of placement. Essentially this will track which column
                // the user has clicked and attempted to place a square at.
                int column = (int) (me.getX() / columnWidth);

                // The variable 'squareXPos' of type double represents the squares 'x' position within the column the
                // user has clicked. The variable 'column' is multiplied by the 'columnWidth' and then re added to the
                // 'columnWidth' variable and divided by 2 to ensure that the square appears in the center of the column.
                double squareXPos = column * columnWidth + columnWidth / 2;

                // The variable 'circleYPos' determines where the circle piece will be placed inside the grids row based
                // on where the user has clicked, so it appears in the middle.

                // The variable 'squareYPos' of type double is used to determine the squares 'y' position within the
                // row that the user has clicked on. The 'row' variable is multiplied by the height of the row 'rowHeight'
                // and is then added to the height of the row and divided by 2 so the square appears centered in the row.
                double squareYPos = row * rowHeight + rowHeight / 2;

                // This will be the default value of the squares side lengths.
                double sideLength = 10;

                // This if statement checks if the row and column that the user has selected to place the square at is
                // empty (null), as a square cannot be placed at a spot that is taken.
                if (grid.getPiece(row, column) == null) {

                    // If the row and column is empty, meaning the square can be placed there, the color of the current
                    // square is determined by the current players turn. The variable 'pieceColor' of type Color is
                    // assigned to the current players color by accessing the current player object 'currentPlayer' and
                    // retrieving the color by calling upon the 'getPlayerColor' method from the player class.
                    Color pieceColor = currentPlayer.getPlayerColor();

                    // A new square piece 'squarePiece' of type square is then created with a declaration type square,
                    // and actual type square, representing the square that is being placed by the player. This instance
                    // of the square object is passed the parameters for the row, column, x and y positions, the color,
                    // and the size of the squares side lengths.
                    Square squarePiece = new Square(row, column, squareXPos, squareYPos, pieceColor, sideLength);

                    // The square piece is then added to the grid array list by calling upon the 'addPiece' method from
                    // the grid class.
                    grid.addPiece(squarePiece);

                    // The grid is then redrawn.
                    redrawGrid();

                    // The square the user has placed is then drawn on the grid.
                    squarePiece.draw(gc);

                    // This if statement checks which players turn it is.
                    if (currentPlayer == playerOne) {
                        // If the current player is the first player, the turn will switch, and it will now be the second
                        // players turn.
                        currentPlayer = playerTwo;

                    // The else block will be executed if it was not the first players turn yet, meaning it will now be
                    // the first players turn.
                    } else {
                        // The 'currentPlayer' is now set to 'playerOne' to reflect it is now the first players turn.
                        currentPlayer = playerOne;
                    }
                // This else block will be executed if the row and column the user has tried to place the square at is
                // taken, as the assignment mentions that an error must occur if the user tries to do so.
                } else {
                    // An alert is shown mentioning that the spot is taken and the square cannot be placed.
                    new Alert(Alert.AlertType.ERROR, "Cannot place Square! The spot is taken!").showAndWait();
                    // An 'IllegalStateException' will also be thrown to reflect the fact that the user cannot place the
                    // square at this position.
                    throw new IllegalStateException("Cannot place the Square here, the spot is taken!");
                }
            }
            // The catch block is waiting for an exception to occur, if the user has tried to place the square at an
            // invalid spot, the catch block will be executed, printing a line to the console, and prompting the user
            // with a warning as specified in the assignment.
        } catch(IllegalStateException e) {
            // Console error message.
            System.out.println(e.getMessage());
            // Visual alert warning.
            new Alert(Alert.AlertType.ERROR, "Cannot place Circle here!");
        }
    }

    /**
     * Components created and event handlers added.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        // Sets the size of the window.
        Scene scene = new Scene(root, 600, 400);
        // Sets the name of the window to 'Grid Game'.
        stage.setTitle("Grid Game");
        stage.setScene(scene);
        // Adds a custom image as the logo that is shown in the top left corner of the window.
        stage.getIcons().add(new Image("images/bLetter.png"));

        // CREATING THE MODEL

        // The 'grid' object is initialized  with 8 rows and 8 columns.
        grid = new Grid(8, 8);

        // CREATING THE GUI COMPONENTS

        // The canvas is initialized with a width of 400 and a height of 400.
        canvas = new Canvas(400, 400);
        // This line initializes the graphics context object to the canvas's 2D graphics context.
        gc = canvas.getGraphicsContext2D();
        // The label 'infoLabel' is initialized and given text to provide the user with some insight on how to play the
        // game.
        infoLabel = new Label("Each player will get one turn, where \neach turn consists of a placement, or " +
                "\nmovement. Right click on an empty \nspace to place a circle, left click to \nselect a circle," +
                "or left click and drag \na circle to move it. Press the middle \nmouse button, to place a square!");
        // The text field 'rowText' is initialized and given text that prompts the user to enter a row.
        rowText = new TextField("Enter the row: ");
        // The text field 'columnText' is initialized and given text that prompts the user to enter a column.
        columnText = new TextField("Enter the column: ");
        // The button 'removeButton' is initialized and given the text "Remove" as this button will be used to remove a
        // piece off of the grid.
        removeButton = new Button("Remove");
        // Another button 'removeAllButton' is initialized and given the text "Remove All" because this button will be
        // responsible for removing all pieces off of the grid and clearing it.
        removeAllButton = new Button("Remove All");
        // The label 'removeAllLabel' is responsible for letting the user know that the button underneath this label will
        // be responsible for removing all the pieces off of the grid and not just one piece.
        removeAllLabel = new Label("Clear the board");
        // The label 'menuLabel' is initialized and given the text "Menu" as this label will serve as a header like label
        // and underneath it will be all the buttons and additional labels.
        menuLabel = new Label("Menu");
        // The label 'removeLabel' is responsible for letting the user know that the button underneath this label is
        // responsible for removing a singular piece. The text inside the label will be 'Remove a piece:" which will
        // let the user know that this will remove one piece.
        removeLabel = new Label("Remove a piece");
        // The variable 'playerOne' creates an instance of the player class, and passes the arguments '1' and
        // 'Color.GREEN' as this will represent the first player.
        playerOne = new Player(1, Color.GREEN);
        // The other variable 'playerTwo' will create another instance of the player class, passing the arguments '2'
        // and 'Color.BLUE' as the second players number, and the player specific color.
        playerTwo = new Player(2, Color.BLUE);
        // The 'currentPlayer' variable of type player is first assigned to the first player 'playerOne' as the first
        // player will be given the first turn.
        currentPlayer = playerOne;
        // The label 'upNextTurnLabel' is responsible for letting the user know whos turn is next. This will not be the
        // label that changes on each turn though.
        upNextTurnLabel = new Label("Up next: ");
        // The label 'turnLabel' will be the label that changes for every turn, and will display which players turn is
        // next. The current text is empty, as it will use a method to manipulate the text depending on which players
        // turn it is.
        turnLabel = new Label("");

        // ADDING COMPONENTS TO THE ROOT

        // This will add each of the nodes to the root.
        root.getChildren().addAll(canvas, infoLabel, rowText, columnText, removeButton, removeAllButton, removeAllLabel,
                removeLabel, menuLabel, upNextTurnLabel, turnLabel);

        // CONFIGURE THE COMPONENTS (colors, fonts, size, location)

        // The label 'menuLabel' is relocated, with a set width, an Arial font, and certain style properties which will
        // change the look of the label. The label is changed to a black background, white text, and centered text.
        menuLabel.relocate(400, 15);
        menuLabel.setPrefWidth(200);
        menuLabel.setFont(new Font("Arial", 20));
        menuLabel.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-alignment: center;");

        // The label 'infoLabel' is relocated, given an Arial font, and a style property changing the labels border to
        // a dark gray color.
        infoLabel.relocate(405, 40);
        infoLabel.setPrefWidth(190);
        infoLabel.setFont(new Font("Arial", 11));
        infoLabel.setStyle("-fx-border-color: darkgray;");

        // The label 'removeLabel' is relocated, with a set width, and style properties changing the background color to
        // light gray, and centering the text.
        removeLabel.relocate(400, 160);
        removeLabel.setPrefWidth(200);
        removeLabel.setStyle("-fx-background-color: lightgray; -fx-alignment: center;");

        // The text field 'rowText' is relocated, and given a set width of 100, with additional styles which will change
        // the background color to light gray, and the border color of the text field to black.
        rowText.relocate(450, 180);
        rowText.setPrefWidth(100);
        rowText.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");

        // The text field 'columnText' is relocated, and given a set width of 100, and will have additional styles
        // changing the background color of the text field to light gray, and changing the text fields border color to
        // black.
        columnText.relocate(450, 210);
        columnText.setPrefWidth(100);
        columnText.setStyle("-fx-background-color: lightgray; -fx-border-color: black");

        // The button 'removeButton' is relocated, and will have additional styles which change the background of the
        // button to a red color, and the buttons border color to black.
        removeButton.relocate(470, 240);
        removeButton.setStyle("-fx-background-color: red; -fx-border-color: black");

        // The button 'removeAllButton' is relocated, and will have additional styles that change the buttons background
        // color to red, and the buttons border color to black.
        removeAllButton.relocate(462, 310);
        removeAllButton.setStyle("-fx-background-color: red; -fx-border-color: black");

        // The label 'removeAllLabel' is relocated, and given a set width of 200. The label will also have styles that
        // change the labels background color to light gray, and aligns the text in the center of the label.
        removeAllLabel.relocate(400, 290);
        removeAllLabel.setPrefWidth(200);
        removeAllLabel.setStyle("-fx-background-color: lightgray; -fx-alignment: center;");

        // The 'upNextTurnLabel' label is relocated, and given a set width of 200. The label will also have styles that
        // change the background color of the label to light gray, and aligns the text inside the label in the center of
        // the label.
        upNextTurnLabel.relocate(400, 350);
        upNextTurnLabel.setPrefWidth(200);
        upNextTurnLabel.setStyle("-fx-background-color: lightgray; -fx-alignment: center;");

        // The 'turnLabel' label is relocated, and will have a Arial font.
        turnLabel.relocate(440, 370);
        turnLabel.setFont(new Font("Arial", 20));

        // ADDING EVENTS FOR FINAL SETUP

        // This event handler is responsible for executing the 'placeCircle' method when the mouse is clicked. This will
        // rely on the primary mouse button to be executed.
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::placeCircle);
        // This action event will execute the 'removeCircle' method when the 'removeButton' is pressed.
        removeButton.setOnAction(this::removeCircle);
        // This event handler will execute the 'selectCircle' method when the primary mouse button is clicked.
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::selectCircle);
        // This event handler will be executed the 'moveCircle' method when the primary mouse button is clicked and
        // dragged. This will simulate the moving of the circle.
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::moveCircle);
        // This event handler is responsible for executing the 'placeSquare' method which will place a square when the
        // middle mouse button is pressed (clicked).
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::placeSquare);
        // This action event will execute the 'removeAllPieces' method when the 'removeAllButton' button is pressed.
        removeAllButton.setOnAction(this::removeAllPieces);
        // This draws the grid initially.
        drawGrid(gc, grid);

        // SHOW THE STAGE
        stage.show();
    }

    /**
     * No changes were made here. Main method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
