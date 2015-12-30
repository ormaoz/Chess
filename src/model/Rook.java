/*

Exercise number      :    10

File Name            :    Rook.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import model.ChessPiece.PieceColor;

/**
 * A factory for creating rook ("tura", or "tzariach" in Hebrew) pieces that
 * implement the ChessPiece interface. All the public methods in this class are
 * documented in the ChessPiece interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class Rook implements ChessPiece {
	private PieceColor color;

	/**
	 * Constructs a rook with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public Rook(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		// Verifies for a straight move.
		return MoveVerificationUtility.verifyStraightLineMove(sourceLocation,
				targetLocation, false, board);
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the white piece locations
		if (color == PieceColor.WHITE) {
			Location[] wRookLocations = new Location[2];
			wRookLocations[0] = new Location(7, 0);
			wRookLocations[1] = new Location(7, 7);
			return wRookLocations;
			// Creates a locations array & fills it with the black piece
			// locations
		} else {
			Location[] bRookLocations = new Location[2];
			bRookLocations[0] = new Location(0, 0);
			bRookLocations[1] = new Location(0, 7);
			return bRookLocations;
		}
	}

	@Override
	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return "WWWWWW   ";
			case 1:
				return " |  |    ";
			case 2:
				return " (  )    ";
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
				return "WWWWWW   ";
			case 1:
				return " ||||    ";
			case 2:
				return " (())    ";
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
		return getColor() == PieceColor.BLACK ? "R" : "r";
	}

	/**
	 * A textual representation of this Rook, for debugging and testing
	 * purposes.
	 * 
	 * @return The character "R" or "r", for white or black Rook, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}
