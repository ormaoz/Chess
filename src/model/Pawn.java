/*

Exercise number      :    10

File Name            :    Pawn.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import javax.activity.InvalidActivityException;

/**
 * A factory for creating pawn ("soldier" in Hebrew) pieces that implement the
 * ChessPiece interface. All the public methods in this class are documented in
 * the ChessPiece interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class Pawn implements ChessPiece {

	// The color of this Pawn.
	private PieceColor color;

	/**
	 * Constructs a pawn with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public Pawn(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.PAWN;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		// Defying the delta between numbers of the columns in the starting
		// position and the target position.
		int deltaColumn = (sourceLocation.column - targetLocation.column);
		int deltaRow = (sourceLocation.row - targetLocation.row);
		// In case it's a white pawn:
		// a. Make sure it only moves one row up (since white pawns moving up).
		if (this.getColor() == PieceColor.WHITE	&& deltaRow == 1) {
			// b. Make sure it only moves straight up and detention is clear.
			if (deltaColumn == 0 && board.getCell(targetLocation).isEmpty()) {
				return true;
			// c. If not straight, check validity to diagonal move.
			} else if ((deltaColumn == 1 || deltaColumn == -1) && 
					!board.getCell(targetLocation).isEmpty()) {
				return true;
			} else {
				return false;
			}
		// In case it's a black pawn:
		// a. Make sure it only moves one row down (black pawns moving down).
		} else if (this.getColor() == PieceColor.BLACK && deltaRow == -1) {
			// b. Make sure it only moves down and detention is clear.
			if (deltaColumn == 0 && board.getCell(targetLocation).isEmpty()) {
				return true;
			// c. If not straight, check validity to diagonal move.
			} else if ((deltaColumn == 1 || deltaColumn == -1) && 
					!board.getCell(targetLocation).isEmpty()) {
				return true;
			} else {
				return false;
			}
		// If piece color doesn't match pawn's rules (moving up and down the 
		// board) return false.
		} else {
			return false;
		}
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the white piece locations
		if (color == PieceColor.WHITE) {
			Location[] wPawnLocations = new Location[8];
			for (int i = 0; i < 8; i++) {
				wPawnLocations[i] = new Location(6, i);
			}
			return wPawnLocations;
			// Creates a locations array & fills it with the black piece
			// locations
		} else {
			Location[] bPawnLocations = new Location[8];
			for (int i = 0; i < 8; i++) {
				bPawnLocations[i] = new Location(1, i);
			}
			return bPawnLocations;
		}
	}

	@Override
	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return "  __     ";
			case 1:
				return " (  )    ";
			case 2:
				return " _||_    ";
			case 3:
				return "(____)   ";
			case 4:
				return "         ";
			case 5:
				return "         ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
			// Returns a textual representation of the black variant of this
			// piece
		} else {
			switch (lineToDraw) {
			case 0:
				return "  __     ";
			case 1:
				return " (())    ";
			case 2:
				return " _||_    ";
			case 3:
				return "((()))   ";
			case 4:
				return "         ";
			case 5:
				return "         ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		}
	}

	@Override
	public String toStringSimple() {
		return getColor() == PieceColor.BLACK ? "P" : "p";
	}

	/**
	 * A textual representation of this Pawn, for debugging and testing
	 * purposes.
	 * 
	 * @return The character "P" or "p", for white or black Pawn, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}
