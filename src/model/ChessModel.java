/*

Exercise number      :    10

File Name            :    ChessModel.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import javax.activity.InvalidActivityException;

import model.ChessPiece.PieceColor;
import model.ChessPiece.PieceType;

/**
 * Represents a model for a chess game. The model includes a board, and
 * information about the game: the game status (is the game over, or are we
 * still playing?), the color that won the game (when the game is over), and the
 * color of the player who makes the current move. The most important service in
 * the model is a "move" method, designed to move a piece from one board
 * location to another. If the move is valid according to the rules of chess,
 * the method moves the piece. If the move is invalid, the method prints
 * information that explains why its invalid, and rejects the move.
 * 
 * This class will be implemented in Part II of this exercise.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class ChessModel {

	// A chess board
	private Board board;

	// Indicates whether the game is over or not
	private Boolean isGameOver = false;

	// If the game is over, holds the color that won the game
	private PieceColor winner;

	// Holds the color of the player who makes the current move
	private PieceColor currentColorTurn;

	/**
	 * Constructs a ChessModel object. In particular, constructs a new board and
	 * sets the player who makes the first move to white.
	 */
	public ChessModel() {
		board = new Board();
		currentColorTurn = PieceColor.WHITE;
	}

	/**
	 * Returns the current board.
	 * 
	 * @return The current board.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Moves the piece located in the given source location to the given target
	 * location. In particular, performs the following actions. First, the
	 * method verifies that the move is valid according to the rules of the
	 * game. If the move is invalid, the method prints specific information why
	 * the move is invalid, and returns false. If the move is valid, the method
	 * checks if the move wins the game. The method then puts the piece in the
	 * target location. If the piece is a pawn and it reached the last row, the
	 * method transforms the pawn into a queen. Finally, the method updates the
	 * color of the next player (if the current player is white, the next player
	 * will be black, and vice versa). If the move was valid, the method returns
	 * true.
	 * 
	 * @param sourceLocation
	 *            the location from which we are moving
	 * @param targetLocation
	 *            the location to which we are moving
	 * @return True if the move is valid according to the game rules and false
	 *         otherwise
	 * @throws InvalidActivityException
	 *             if an internal error occurred
	 */
	public Boolean movePiece(Location sourceLocation, Location targetLocation)
			throws InvalidActivityException {
		// Creating two booleans to track and determine the validation of the
		// locations.
		Boolean isLocationsValidates = false;
		Boolean isMoveVerified = false;
		if (isGameOver) {
			return false;
		}
		// Check validations for locations.
		if (isInBoard(sourceLocation, targetLocation)) {
			if (validLocations(sourceLocation, targetLocation)) {
				isLocationsValidates = true;
				// Verify move according to the verify move method of the piece.
				if (board.getCell(sourceLocation).getPiece().
						verifyMove(sourceLocation, targetLocation, board)) {
					isMoveVerified = true;
				}
			}
		}
		
		// In case locations are valid and move is verified:
		if (isLocationsValidates && isMoveVerified) {
			// a. Check if the game is over: if it is, determine winner, if it
			// isn't, change the turn to the other player.
			if (!board.getCell(targetLocation).isEmpty()
					&& board.getCell(targetLocation).getPiece().getType() == 
															PieceType.KING) {
				winner = currentColorTurn;
				isGameOver = true;
			// Turn changer:
			} else {
				if (currentColorTurn == PieceColor.WHITE) {
					currentColorTurn = PieceColor.BLACK;
				} else {
					currentColorTurn = PieceColor.WHITE;
				}
			}
			// b. Set the piece from the source location in the target location.
			board.getCell(targetLocation).setPiece(
					board.getCell(sourceLocation).getPiece());
			// c. Clear to source location from its piece.
			board.getCell(sourceLocation).setToEmpty();
			// d. In case of "pawn in last row" situation:
			pawnToQueen(sourceLocation, targetLocation);
		}
		// Returns the validation of the move.
		return (isLocationsValidates && isMoveVerified);
	}

	/**
	 * Returns true if the game is over and false otherwise
	 * 
	 * @return True if the game is over and false otherwise
	 */
	public Boolean isGameOver() {
		return isGameOver;
	}
	
	/**
	 * Change a pawn in the last row to a queen
	 * @param sourceLocation
	 * @param targetLocation
	 * @throws InvalidActivityException
	 */
	private void pawnToQueen (Location sourceLocation, Location targetLocation) 
			throws InvalidActivityException {
		// If a white pawn got to the last row, convert it to a queen.
		if ((targetLocation.row == 0)
				&& (board.getCell(targetLocation).getPiece().getType() == 
				PieceType.PAWN)) {
			board.getCell(targetLocation).setPiece(
					new Queen(PieceColor.WHITE));
		}
		// If a black pawn got to the last row, convert it to a queen.
		if ((targetLocation.row == 7)
				&& (board.getCell(targetLocation).getPiece().getType() == 
				PieceType.PAWN)) {
			board.getCell(targetLocation).setPiece(
					new Queen(PieceColor.BLACK));
		}
	}
	
	
	
	/**
	 * A private method that checks if the location is inside the board
	 * @param sourceLocation
	 * @param targetLocation
	 * @return true is the location is inside the board.
	 */
	private Boolean isInBoard (Location sourceLocation, Location targetLocation) {
		if (sourceLocation.row > 7 || sourceLocation.row < 0 || 
				sourceLocation.column > 7 || sourceLocation.column < 0 || 
				targetLocation.row > 7 || targetLocation.row < 0 || 
				targetLocation.column > 7 || targetLocation.column < 0) {
			System.out.println("Location is out of the board");
			return false;
		}
		return true;
	}
	
	/**
	 * A private method the validates the following:
	 * 1. does the source location is empty?
	 * 2. does the target location contains a piece from the same color?
	 * 3. is the player trying to play with his own piece?
	 * @param sourceLocation
	 * @param targetLocation
	 * @return true if all the above conditions are true.
	 * @throws InvalidActivityException
	 */
	private Boolean validLocations (Location sourceLocation, Location 
			targetLocation) throws InvalidActivityException {
		// Does the source location contains a piece?
		if (board.getCell(sourceLocation).isEmpty()) {
			System.out.println("Source location is empty");
			return false;
		// Does the target location contains a piece from the same color?
		} else if (!board.getCell(targetLocation).isEmpty() && 
				board.getCell(sourceLocation).getPiece().getColor() == 
				board.getCell(targetLocation).getPiece().getColor()) {
				System.out.println("Target location is occupied by your own piece");
				return false;
		// Does the source location contains the right color piece?
		} else if (board.getCell(sourceLocation).getPiece().getColor() != 
															currentColorTurn) {
			System.out.println("Source location contains opponets piece");
			return false;
		} 
		return true;
	}

	/**
	 * Returns the color of the player that won the game
	 * 
	 * @return The winning color
	 * @throws InvalidActivityException
	 *             - if the game is not over yet
	 */
	public PieceColor getWinner() throws InvalidActivityException {
		if (isGameOver) {
			return winner;
		} else {
			throw new InvalidActivityException("No winner, game isn't over");
		}
	}
}