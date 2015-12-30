/*

Exercise number      :    10

File Name            :    Knight.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.security.InvalidParameterException;

import model.ChessPiece.PieceColor;

/**
 * A factory for creating knight ("sus" in Hebrew) pieces that implement the
 * ChessPiece interface. All the public methods in this class are documented in
 * the ChessPiece interface.
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class Knight implements ChessPiece {
	private PieceColor color;

	/**
	 * Constructs a knight with the given color.
	 * 
	 * @param color
	 *            the given color.
	 */
	public Knight(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceType getType() {
		return PieceType.KNIGHT;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Boolean verifyMove(Location sourceLocation, Location targetLocation,
			Board board) {
		// Calculating the delta between starting point and destination.
		int deltaRow = Math.abs(sourceLocation.row - targetLocation.row);
		int deltaColumn = Math.abs(sourceLocation.column 
								   - targetLocation.column);
		try {
			// First, checks if destination is empty or full with enemy piece.
			if (board.getCell(targetLocation).isEmpty()
					|| board.getCell(sourceLocation).getPiece().getColor() != 
					   board.getCell(targetLocation).getPiece().getColor()) {
				// Second, checks if the deltas sum is 3 (a valid knight move).
				if (deltaRow + deltaColumn == 3) {
					return true;
				}
			}
		// Catch exception if cell is empty.
		} catch (Exception e) {
			return false;
		}
			return false;
	}

	@Override
	public Location[] InitialBoardLocations() {
		// Creates a locations array & fills it with the white piece locations
		if (color == PieceColor.WHITE) {
			Location[] wKnightLocations = new Location[2];
			wKnightLocations[0] = new Location(7, 1);
			wKnightLocations[1] = new Location(7, 6);
			return wKnightLocations;
		// Creates a locations array & fills it with the black piece locations
		} else {
			Location[] bKnightLocations = new Location[2];
			bKnightLocations[0] = new Location(0, 1);
			bKnightLocations[1] = new Location(0, 6);
			return bKnightLocations;
		}
	}

	@Override
	public String toStringAscii(PieceColor color, int lineToDraw) {
		// Returns a textual representation of the white variant of this piece
		if (color == PieceColor.WHITE) {
			switch (lineToDraw) {
			case 0:
				return " __/\"\"\"\\ ";
			case 1:
				return "]___ 0  }";
			case 2:
				return "    /   }";
			case 3:
				return "  /~    }";
			case 4:
				return "  \\____/ ";
			case 5:
				return "  (____) ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		// Returns a textual representation of the black variant of this piece
		} else {
			switch (lineToDraw) {
			case 0:
				return " __/////\\";
			case 1:
				return "]___ 0  }";
			case 2:
				return "   /////}";
			case 3:
				return "  /~~~~~}";
			case 4:
				return "  \\\\\\/// ";
			case 5:
				return "  ((())) ";
			default:
				throw new InvalidParameterException(
						"This view only supports 6-line cells!");
			}
		}
	}

	@Override
	public String toStringSimple() {
		return getColor() == PieceColor.BLACK ? "N" : "n";
	}

	/**
	 * A textual representation of this Knight, for debugging and testing
	 * purposes.
	 * 
	 * @return The character "N" or "n", for white or black Knight, respectively
	 */
	public String toString() {
		return toStringSimple();
	}

}
