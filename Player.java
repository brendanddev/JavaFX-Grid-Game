package assignmentEight_000879513;

import javafx.scene.paint.Color;

/**
 * This class represents a player who will be playing in the grid game. It will allow for each player in the game to be
 * assigned a specific number and color to differentiate between each player in the game. The class has two instance
 * variables, 'playerNum' which represents the number assigned to a player, and 'playerColor' which represents the color
 * that has been assigned to a player. The class constructor takes two parameters, the player specific number that will
 * be assigned to them, and the players specific color that each of their pieces on the game grid will have. Additionally,
 * there will be two getter methods for retrieving the players number, and the color of the player. This allows the game
 * to determine what player is doing what, and allows for the creation of multiple players.
 *
 * @author BRENDAN DILEO, 000879513.
 */

public class Player {
    // Instance variables:
    /**
     * 'playerNum' of type int, represents the number that will be assigned to the player.
     */
    private int playerNum;
    /**
     * 'playerColor' of type Color, represents the color that will be assigned to the player and each of their pieces.
     */
    private Color playerColor;

    /**
     * This constructor will be used to create an instance of a player, where each instance created will represent a
     * player that will be a part of the grid game. The constructor takes two parameters, 'playerNum', and 'playerColor'
     * which represent the number that will be assigned to the player, and the color that will be assigned to the player
     * and their pieces. Inside the body of the constructor these parameters are assigned to the class instance variables
     * with the use of the 'this' keyword.
     *
     * @param playerNum, the number that will be assigned to this instance of the player.
     * @param playerColor, the color that will be given to this instance of the player, and their pieces.
     */
    public Player(int playerNum, Color playerColor) {
        this.playerNum = playerNum;
        this.playerColor = playerColor;
    }

    /**
     * This method is responsible for retrieving (getting) the number that has been assigned to a specific player.
     *
     * @return playerNum, the number that has been assigned to the player.
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * This method 'getPlayerColor' is responsible for retrieving (getting) the specific color that has been assigned to
     * a player.
     *
     * @return playerColor, the color that has been assigned to the player.
     */
    public Color getPlayerColor() {
        return playerColor;
    }
}
