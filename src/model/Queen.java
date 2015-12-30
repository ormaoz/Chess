/*

Exercise number      :    10

File Name            :    Queen.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import model.ChessPiece.PieceColor;

/**
 * A factory for creating queen pieces that implement the ChessPiece interface.
 * All the public methods in this class are documented in the ChessPiece
 * interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class Queen implements ChessPiece {
	private PieceColor color;

	/**
	 * Constructs a queen with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public Queen(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.QUEEN;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		int deltaRow = Math.abs(sourceLocation.row - targetLocation.row);
		int deltaColumn = Math.abs(sourceLocation.column - 
														targetLocation.column);
		// Verifies straight move.
		if (deltaRow * deltaColumn == 0) {
			return MoveVerificationUtility.verifyStraightLineMove(
					sourceLocation, targetLocation, false, board);
		} else {
		// Verifies diagonal move.
		return MoveVerificationUtility.verifyDiagonalMove(sourceLocation,
				targetLocation, false, board);
		}
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the black piece location
		if (color == PieceColor.WHITE) {
			Location[] wQueenLocation = new Location[1];
			wQueenLocation[0] = new Location(7, 3);
			return wQueenLocation;
			// Creates a locations array & fills it with the black piece
			// location
		} else {
			Location[] bQueenLocation = new Location[1];
			bQueenLocation[0] = new Location(0, 3);
			return bQueenLocation;
		}
	}

	@Override
	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return "  ()     ";
			case 1:
				return "<~~~~>   ";
			case 2:
				return " \\__/    ";
			case 3:
				return " (  )    ";
			case 4:
				return " _||_    ";
			case 5:
				return "(____)   ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		// Returns a textual representation of the black variant of this piece
		} else {
			switch (lineToDraw) {
			case 0:
				return "  ()     ";
			case 1:
				return "<~~~~>   ";
			case 2:
				return " \\\\//    ";
			case 3:
				return " (())    ";
			case 4:
				return " _||_    ";
			case 5:
				return "((()))   ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		}
	}

	@Override
	public String toStringSimple() {
		return getColor() == PieceColor.BLACK ? "Q" : "q";
	}

	/**
	 * A textual representation of this Queen, for debugging and testing
	 * purposes.
	 * 
	 * @return The character "Q" or "q", for white or black Queen, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}