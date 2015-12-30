/*

Exercise number      :    10

File Name            :    MoveVerificationUtility.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

/**
 * A static utility class for verifying the validity of various chess moves
 * 
 * @author Intro2CS - IDC 2012
 * 
 */
public class MoveVerificationUtility {

	/**
	 * Verifies that the move between the given source location and the given
	 * target location proceeds along a Straight line, and that it is legal
	 * according the rules of chess, given the current board situation. Also
	 * checks the length of the move, and compares the result to the given
	 * moveLengthMustBeOne restriction.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @param moveLengthMustBeOne
	 *            the caller of this method must specify if the move is
	 *            restricted in terms of length. For example, the move of a king
	 *            must have a length of 1, but the move of a queen can have a
	 *            length greater than 1. The method checks if the move implied
	 *            by the two given locations is compatible with this
	 *            restriction.
	 * @param board
	 *            the current state of the board
	 * @return True if the move is valid, false otherwise
	 */
	public static Boolean verifyStraightLineMove(Location sourceLocation,
			Location targetLocation, Boolean moveLengthMustBeOne, Board board) {
		// First checks if there is a move at all.
		if (noMoving(sourceLocation, targetLocation)) {
			return false;
		} else {
			// In case it's a "one move" situation:
			if (moveLengthMustBeOne) {
				return oneStraightMove(sourceLocation, targetLocation);
				// In case it's not a "one move" situation:
			} else {
				return clearAndValidStraightWay(board, sourceLocation,
						targetLocation);
			}
		}
	}

	/**
	 * Verifies that the move between the given source location and the given
	 * target location proceeds along a diagonal line, and that it is legal
	 * according the rules of chess, given the current board situation. Also
	 * checks the length of the move, and compares the result to the given
	 * moveLengthMustBeOne restriction.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @param moveLengthMustBeOne
	 *            the caller of this method must specify if the move is
	 *            restricted in terms of length. For example, the move of a king
	 *            must have a length of 1, but the move of a queen can have a
	 *            length greater than 1. The method checks if the move implied
	 *            by the two given locations is compatible with this
	 *            restriction.
	 * @param board
	 *            the current state of the board
	 * @return True if the move is valid, false otherwise
	 */
	public static Boolean verifyDiagonalMove(Location sourceLocation,
			Location targetLocation, Boolean moveLengthMustBeOne, Board board) {
		// First, check if there is a move at all.
		if (noMoving(sourceLocation, targetLocation)) {
			return false;
		// In case it's a "one move" situation:
		} else if (moveLengthMustBeOne) {
			return oneDiagonalMove(sourceLocation, targetLocation);
		// In case it's not a "one move" situation:
		} else {
			return (clearDiagonalWay(board, sourceLocation, targetLocation));
		}
	}

	/**
	 * A private method made for cases of more than one step move. The method
	 * checks if the way is between the starting point and the ending point is
	 * clear and if it's a straight move.
	 * 
	 * @param board
	 *            the current state of the board
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @return clearToGo if the way between starting point and ending point is
	 *         clear.
	 */
	private static Boolean clearAndValidStraightWay(Board board,
			Location sourceLocation, Location targetLocation) {
		Boolean clearWay = true;
		// Variables made to check clearance over the piece track.
		int deltaRow = Math.abs(sourceLocation.row - targetLocation.row);
		int maxRow = Math.max(sourceLocation.row, targetLocation.row);
		int minRow = Math.min(sourceLocation.row, targetLocation.row);
		int deltaColumn = Math.abs(sourceLocation.column - targetLocation.column);
		int maxColumn = Math.max(sourceLocation.column, targetLocation.column);
		int minColumn = Math.min(sourceLocation.column, targetLocation.column);
		int theActualDelta = Math.max(deltaRow, deltaColumn);
		// If the move is Straight:
		if (deltaRow * deltaColumn == 0) {
			// If the move is vertical.
			if (theActualDelta == deltaRow) {
				// Go over the piece track and insure the way is clear.
				for (int i = minRow + 1; i < maxRow; i++) {
					if (board.getCell(i, sourceLocation.column).isEmpty()) {
						clearWay = true;
					} else {
						System.out.println("Way to target is not clear");
						clearWay = false;
						break;
					}
				}
			// If the move is horizontal.	
			} else {
				// Go over the piece track and insure the way is clear.
				for (int i = minColumn + 1; i < maxColumn; i++) {
					if (board.getCell(sourceLocation.row, i).isEmpty()) {
						clearWay = true;
					} else {
						System.out.println("Way to target is not clear");
						clearWay = false;
						break;
					}
				}
			} 	
		// If the move is neither horizontal not vertical than it's not straight
		} else {
			System.out.println("Move is not according to the rules");
			clearWay = false;
		}
		return clearWay;
	}

	/**
	 * A simple private method meant to check if there is a move at all or
	 * perhaps, the given starting point and the given ending point is the same
	 * point.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @return true if there is no moving at all.
	 */
	private static Boolean noMoving(Location sourceLocation,
			Location targetLocation) {
		// In case the starting point and ending point are the same than there
		// is no move at all.
		if (sourceLocation == targetLocation) {
			System.out.println("You did not make a move");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A private method that checks if a one straight move is: valid and really
	 * one move.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @return true if the move is valid and it's a "one step" move.
	 */
	private static Boolean oneStraightMove(Location sourceLocation,
			Location targetLocation) {
		// Variables made to check validity of the move.
		int deltaRow = Math.abs(sourceLocation.row - targetLocation.row);
		int deltaColumn = Math.abs(sourceLocation.column - 
														targetLocation.column);
		int theActualDelta = Math.max(deltaRow, deltaColumn);
		if (deltaRow * deltaColumn == 0) {
			if (theActualDelta == 1) {
				return true;
			}
		}
		System.out.println("Move must be one");
		return false;
	}

	/**
	 * A private method made to make sure the way between the starting point and
	 * the ending point is clear from other pieces. the method
	 * validates diagonal move and that all the cells on the way is empty.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @param board
	 *            the current state of the board
	 * @return true if the way is clear.
	 * 
	 */
	private static Boolean clearDiagonalWay(Board board,
			Location sourceLocation, Location targetLocation) {
		Boolean isWayClear = true;
		// Variables made to check clearance over the piece track.
		int deltaRow = (sourceLocation.row - targetLocation.row);
		int maxRow = Math.max(sourceLocation.row, targetLocation.row);
		int minRow = Math.min(sourceLocation.row, targetLocation.row);
		int deltaColumn = (sourceLocation.column - targetLocation.column);
		int maxColumn = Math.max(sourceLocation.column, targetLocation.column);
		int minColumn = Math.min(sourceLocation.column, targetLocation.column);
		// If the move is diagonal.
		if (Math.abs(deltaRow) == Math.abs(deltaColumn)) {
			if (deltaRow * deltaColumn > 0) {
				// Go over the track of the piece and check if it's clear.
				while (minRow < (maxRow - 1) && minColumn < (maxColumn - 1)) {
					minRow++;
					minColumn++;
					if (!board.getCell(minRow, minColumn).isEmpty()) {
						System.out.println("Way to target not clear");
						isWayClear = false;
						break;
					}
					isWayClear = true;
				}
			} else if (deltaRow * deltaColumn < 0) {
				// Go over the track of the piece and check if it's clear.
				while (minRow < (maxRow - 1) && maxColumn > (minColumn + 1)) {
					minRow++;
					maxColumn--;
					if (!board.getCell(minRow, maxColumn).isEmpty()) {
						System.out.println("בעיה בעיה בעיהWay to target not clear");
						isWayClear = false;
						break;
					}
					isWayClear = true;
				}
			}
			
			// Go over the track of the piece and check if it's clear.
			while (minRow < (maxRow - 1) && minColumn < (maxColumn - 1)) {
				minRow++;
				minColumn++;
				if (!board.getCell(minRow, minColumn).isEmpty()) {
					System.out.println("בעיה בעיה בעיהWay to target not clear");
					isWayClear = false;
					break;
				}
				isWayClear = true;
			}
		// If the move is not diagonal.
		} else {
			System.out.println("Move is not according to the rules");
			isWayClear = false;
		}
		return isWayClear;
	}
			/**
		// Case 1: Moving bottom right:
		if ((sourceLocation.row < targetLocation.row)
				&& (sourceLocation.column < targetLocation.column)) {
			// Copying the variables so I'll be able to manipulate them.
			int startRow = sourceLocation.row;
			int endRow = targetLocation.row;
			int startColumn = sourceLocation.column;
			int endColumn = targetLocation.column;
			// Going over all the cells on the way and checks if they're empty.
			while (startRow < endRow && startColumn < endColumn) {
				startRow++;
				startColumn++;
				if (board.getCell(startRow, startColumn).isEmpty() == false) {
					isWayClear = false;
					break;
				}
			}
			// Case 2: Moving bottom left:
		} else if ((sourceLocation.row > targetLocation.row)
				&& (sourceLocation.column < targetLocation.column)) {
			// Copying the variables so I'll be able to manipulate them.
			int startRow = sourceLocation.row;
			int endRow = targetLocation.row;
			int startColumn = sourceLocation.column;
			int endColumn = targetLocation.column;
			// Going over all the cells on the way and checks if they're empty.
			while (startRow > endRow && startColumn < endColumn) {
				startRow--;
				startColumn++;
				if (board.getCell(startRow, startColumn).isEmpty() == false) {
					isWayClear = false;
					break;
				}
			}
			// Case 3: Moving top right:
		} else if ((sourceLocation.row < targetLocation.row)
				&& (sourceLocation.column > targetLocation.column)) {
			// Copying the variables so I'll be able to manipulate them.
			int startRow = sourceLocation.row;
			int endRow = targetLocation.row;
			int startColumn = sourceLocation.column;
			int endColumn = targetLocation.column;
			// Going over all the cells on the way and checks if they're empty.
			while (startRow < endRow && startColumn > endColumn) {
				startRow++;
				startColumn--;
				if (board.getCell(startRow, startColumn).isEmpty() == false) {
					isWayClear = false;
					break;
				}
			}
			// Case 4: Moving top left:
		} else if ((sourceLocation.row > targetLocation.row)
				&& (sourceLocation.column > targetLocation.column)) {
			// Copying the variables so I'll be able to manipulate them.
			int startRow = sourceLocation.row;
			int endRow = targetLocation.row;
			int startColumn = sourceLocation.column;
			int endColumn = targetLocation.column;
			// Going over all the cells on the way and checks if they're empty.
			while (startRow < endRow && startColumn < endColumn) {
				startRow--;
				startColumn--;
				if (board.getCell(startRow, startColumn).isEmpty() == false) {
					isWayClear = false;
					break;
				}
			}
		}
		return isWayClear;**/
	

	/**
	 * A private method that checks if a one diagonal move is: valid and really
	 * one move.
	 * 
	 * @param sourceLocation
	 *            the location where the move starts
	 * @param targetLocation
	 *            the location where the move ends
	 * @return true if the move is valid and it's a "one step" move.
	 */
	private static Boolean oneDiagonalMove(Location sourceLocation,
			Location targetLocation) {
		// Checks a one diagonal move validation.
		if ((Math.abs(sourceLocation.row - targetLocation.row)) == 1
			&& (Math.abs(sourceLocation.column - targetLocation.column)) == 1) {
			return true;
		} else {
			System.out.println("Move must be one");
			return false;
		}
	}
}
