/*

Exercise number      :    10

File Name            :    ChessPiece.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import javax.activity.InvalidActivityException;

/**
 * Represents a piece in a chess game.
 * Provides some basic information about the piece,
 * checks if the piece can make a given move,
 * helps to initialize instances of the piece at the beginning of a game,
 * and provides a textual representation of the piece.  
 * 
 * @author Intro2CS - IDC 2012
 */
public interface ChessPiece {
	
	/**
	 * Contains the names of the the six typical chess pieces
	 */
	public enum PieceType {
		KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
	}

	/**
	 * Contains the two typical chess colors
	 */
	public enum PieceColor {
		BLACK, WHITE
	}

	/**
	 * Returns the type of this chess piece 
	 * 
	 * @return The type of this chess piece 
	 */
	PieceType getType();

	/**
	 * Returns the color of this chess piece 
	 * 
	 * @return The color of this chess piece 
	 */
	PieceColor getColor();

	/**
	 * Determines if this chess piece can make the given move.
	 * This is done using the chess movement rules of this particular chess piece,
	 * and the current board situation.
	 * 
	 * @param sourceLocation the location from which we are moving
	 * @param targetLocation the location into which we are moving
	 * @param board the current board situation 
	 * @return True if the move is valid in this board situation, false otherwise
	 */
	Boolean verifyMove (Location sourceLocation, Location targetLocation, Board board);
	
	/**
	 * Returns the initial board locations of all the instances of this chess piece according its color.
	 * For example, a Pawn has 8 initial locations for a each color; a Bishop has 2 initial locations for each color,
	 * a king has 1 initial location for each color, etc.
	 * 
	 * @return The initial locations of all the instances of this chess piece according its color
	 */
	Location[] InitialBoardLocations ();
	
	/**
	 * A textual representation of this chess piece using an ASCII view mode, which is 
	 * drawn one line at a time. An example of this representation for all the chess pieces 
	 * is given in the "Exercise 10, Part I" document.
	 * 
	 * @throws  InvalidParameterException - if the given line to draw is not between 0 to 5
	 * @return The ASCII representation of this chess piece, one line at a time.
	 */
	String toStringAscii (PieceColor color, int lineToDraw);	
	
	/**
	 * A textual representation of this chess piece, using a simple view mode.
	 * An example of this representation for all the chess pieces is given in the 
	 * "Exercise 10, Part I" document.
	 * 
	 * @return The simple textual representation of this chess piece (a single letter).
	 */
	public String toStringSimple ();
	
}