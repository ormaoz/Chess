/*

Exercise number      :    10

File Name            :    Board.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

import java.util.ArrayList;
import java.util.List;

import model.ChessPiece.PieceColor;
import model.ChessPiece.PieceType;

/**
 * Represents a board of a Chess game. The board consists of 8 rows, 8 columns,
 * and 64 cells. The index of the board's upper-left cell is (0,0) The index of
 * the board's bottom-right cell is (7,7)
 * 
 * @author Intro2CS - IDC 2012
 */
public class Board {
	// The number of rows in this board
	public static final int NUMBER_OF_ROWS = 8;
	// The number of columns in this board
	public static final int NUMBER_OF_COLUMNS = 8;
	// The board of a chess game
	private Cell[][] board;

	/**
	 * Constructs a board for a chess game, as follows. Fills the top two rows
	 * of the board with black pieces, located in their initial positions
	 * according to the rules of chess. Fills the bottom two rows of the board
	 * in the same way, with white pieces. Fills the remaining cells in the
	 * board with nothing (empty cells). In other words, the constructor creates
	 * all the cells of the board, and either fills them with chess pieces, or
	 * leaves them empty, as explained above.
	 */
	public Board() {
		this.board = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		// Setting all the empty cells to be empty.
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {
			for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
				this.board[row][column] = new Cell();
			}
		}
		// Setting all the black pawns cells.
		for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
			this.board[1][column].setPiece(new Pawn(PieceColor.BLACK));
		}
		// Setting all the white pawns cells.
		for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
			this.board[6][column].setPiece(new Pawn(PieceColor.WHITE));
		}
		// Setting the rest of the black cells.
		this.board[0][0].setPiece(new Rook(PieceColor.BLACK));
		this.board[0][7].setPiece(new Rook(PieceColor.BLACK));
		this.board[0][1].setPiece(new Knight(PieceColor.BLACK));
		this.board[0][6].setPiece(new Knight(PieceColor.BLACK));
		this.board[0][2].setPiece(new Bishop(PieceColor.BLACK));
		this.board[0][5].setPiece(new Bishop(PieceColor.BLACK));
		this.board[0][3].setPiece(new Queen(PieceColor.BLACK));
		this.board[0][4].setPiece(new King(PieceColor.BLACK));
		
		// Setting the rest of the white cells.
		this.board[7][0].setPiece(new Rook(PieceColor.WHITE));
		this.board[7][7].setPiece(new Rook(PieceColor.WHITE));
		this.board[7][1].setPiece(new Knight(PieceColor.WHITE));
		this.board[7][6].setPiece(new Knight(PieceColor.WHITE));
		this.board[7][2].setPiece(new Bishop(PieceColor.WHITE));
		this.board[7][5].setPiece(new Bishop(PieceColor.WHITE));
		this.board[7][3].setPiece(new Queen(PieceColor.WHITE));
		this.board[7][4].setPiece(new King(PieceColor.WHITE));
	}
	

	/**
	 * Returns the cell associated with the given location in this board. Allows
	 * accessing a cell in this board according to the cell's location.
	 * 
	 * @param location
	 *            a location in this board
	 * @return The cell in the location
	 */
	public Cell getCell(Location location) {
		return this.board[location.row][location.column];
	}

	/**
	 * Returns the cell associated with the given row and column of this board.
	 * Allows accessing a cell in this board according to the cell's
	 * (row,column) coordinates.
	 * 
	 * @param row
	 *            the given row
	 * @param column
	 *            the given column
	 * @return The cell in the (row,column) board location
	 */
	public Cell getCell(int row, int column) {
		return this.board[row][column];
	}

}
