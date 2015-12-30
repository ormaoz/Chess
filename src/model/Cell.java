/*

Exercise number      :    10

File Name            :    Cell.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import javax.activity.InvalidActivityException;

/**
 * Represents a cell in a board of chess. cell may contain a chess piece, or not
 * 
 * @author Intro2CS - IDC 2012
 */
public class Cell {
	
	private ChessPiece piece;
	private Boolean isEmpty;

	/**
	 * Constructs an empty cell.
	 */
	public Cell () {	
		piece = null;
		isEmpty = true;
	}

	/**
	 * Constructs a cell with the given piece in it.
	 * 
	 * @param piece the piece to put in the cell
	 */
	public Cell (ChessPiece piece) {
		this.piece = piece;
		isEmpty = false;
	}

	/**
	 * Returns true if this cell is empty and false otherwise
	 * 	
	 * @return True if this cell is empty and false otherwise
	 */
	public Boolean isEmpty () {
		return isEmpty;
	}
	
	/**
	 * Returns the piece in this cell
	 * 
	 * @return The piece in this cell
	 * @throws InvalidActivityException - if this cell is empty
	 */
	public ChessPiece getPiece () throws InvalidActivityException {
		if (isEmpty) {
			throw new InvalidActivityException("The cell is empty");
		} else {
			return this.piece;
		}
	}

	/**
	 * Sets this cell to hold the given piece.
	 * 
	 * @param piece the piece to put in this cell.
	 */
	public void setPiece (ChessPiece piece) {
		this.piece = piece;
		isEmpty = false;
	}

	/**
	 * Sets this cell to be empty
	 */
	public void setToEmpty() {
		isEmpty = true;
	}

}