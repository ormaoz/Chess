/*

Exercise number      :    10

File Name            :    King.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import model.ChessPiece.PieceColor;

/**
 * A factory for creating king pieces that implement the ChessPiece interface.
 * All the public methods in this class are documented in the ChessPiece
 * interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class King implements ChessPiece {
	private PieceColor color;

	/**
	 * Constructs a king with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public King(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		// Verifies one step diagonal or straight move.
		return MoveVerificationUtility.verifyDiagonalMove(sourceLocation,
				targetLocation, true, board)
				|| MoveVerificationUtility.verifyStraightLineMove(
						sourceLocation, targetLocation, true, board);
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the white piece location
		if (color == PieceColor.WHITE) {
			Location[] wKingLocation = new Location[1];
			wKingLocation[0] = new Location(7, 4);
			return wKingLocation;
		// Creates a locations array & fills it with the black piece location
		} else {
			Location[] bKingLocation = new Location[1];
			bKingLocation[0] = new Location(0, 4);
			return bKingLocation;
		}
	}

	@Override
	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return "  ::     ";
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
				return "  ::     ";
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
		return getColor() == PieceColor.BLACK ? "K" : "k";
	}

	/**
	 * A textual representation of this King, for debugging and testing
	 * purposes.
	 * 
	 * @return The string "K" or "k", for white or black King, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}
