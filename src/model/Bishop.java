/*

Exercise number      :    10

File Name            :    Bishop.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import model.ChessPiece.PieceColor;

/**
 * A factory for creating bishop ("ratz" in Hebrew) pieces that implement the
 * ChessPiece interface. All the public methods in this class are documented in
 * the ChessPiece interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class Bishop implements ChessPiece {
	private PieceColor color;

	/**
	 * Constructs a bishop with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public Bishop(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.BISHOP;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the white piece locations
		if (color == PieceColor.WHITE) {
			Location[] wBishopLocations = new Location[2];
			wBishopLocations[0] = new Location(7, 2);
			wBishopLocations[1] = new Location(7, 5);
			return wBishopLocations;
		// Creates a locations array & fills it with the black piece locations
		} else {
			Location[] bBishopLocations = new Location[2];
			bBishopLocations[0] = new Location(0, 2);
			bBishopLocations[1] = new Location(0, 5);
			return bBishopLocations;
		}
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		// Verifies for a diagonal move.
		return MoveVerificationUtility.verifyDiagonalMove(sourceLocation,
				targetLocation, false, board);
	}

	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return "  <>     ";
			case 1:
				return " (  )    ";
			case 2:
				return "  ||     ";
			case 3:
				return " _||_    ";
			case 4:
				return "(____)   ";
			case 5:
				return "         ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		// Returns a textual representation of the black variant of this piece
		} else {
			switch (lineToDraw) {
			case 0:
				return "  <>     ";
			case 1:
				return " (())    ";
			case 2:
				return "  ||     ";
			case 3:
				return " _||_    ";
			case 4:
				return "((()))   ";
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
		return getColor() == PieceColor.BLACK ? "B" : "b";

	}

	/**
	 * A textual representation of this Bishop, for debugging and testing
	 * purposes.
	 * 
	 * @return The string "B" or "b", for white or black Bishop, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}