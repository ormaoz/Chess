/*

Exercise number      :    10

File Name            :    AsciiView.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package view;

import javax.activity.InvalidActivityException;

import model.Board;
import model.Cell;
import model.ChessPiece;
import model.ChessPiece.PieceColor;

/**
 * Represents the view (V) part of a chess game designed along the MVC pattern.
 * This particular view displays each chess piece using a grid of ASCII
 * characters.
 * 
 * @author Intro2CS - IDC 2012
 */
public class AsciiView implements View {

	/** Default constructor. */
	public AsciiView() {
	}

	/**
	 * Draws the given board. Each chess piece is displayed using a character
	 * grid consisting of 6 rows by 9 columns.
	 * 
	 * @param board
	 *            the board to draw
	 * @throws InvalidActivityException
	 *             - if an internal error occurred
	 */
	public void draw(Board board) throws InvalidActivityException {
		// The Main loop: goes over each of the 8 rows of the board.
		for (int rowOnBoard = 0; rowOnBoard < 8; rowOnBoard++) {
			// A sub loop to print the lines between each row. 
			for (int i = 1 ; i < 9; i ++) {
				char columnLetter = (char) (i + 96);
				System.out.print("-----" + columnLetter + "----");
			}
			// Ending the line between the board rows and goes a line down.
			System.out.println("-");
			// A secondary loop: goes over each row inside each cell.
			for (int rowInCell = 0; rowInCell < 6; rowInCell ++) {
				// The third loop: goes over the 8 columns of the board.
				for (int column = 0; column < 8; column++) {
					// If the cell is empty, draws an empty cell.
					if (board.getCell(rowOnBoard, column).isEmpty()) {
						// At the beginning of the third line inside each row
						// draw the row number.
						if (rowInCell == 2 && column == 0) {
							System.out.print(8 - rowOnBoard + "         ");
						} else {
							System.out.print("|         ");
						}
					// If the cell is not empty, draw right line of the piece
					} else {
						// Again, mark the third row with row number.
						if (rowInCell == 2 && column == 0) {
							System.out.print(8 - rowOnBoard + board.getCell
									(rowOnBoard, column).getPiece().
									toStringAscii(board.getCell
											(rowOnBoard, column).
											getPiece().getColor(), rowInCell));
						// (In case it's not the third row in the first column)
						} else {
							System.out.print("|" + board.getCell
									(rowOnBoard, column).getPiece().
									toStringAscii(board.getCell
											(rowOnBoard, column).getPiece().
											getColor(), rowInCell));
						}
					}
				}
				// At the end of each row in cell, close the row and go one down
				System.out.println("|");
			}	
		}
		// The sub loop again: finish the board with one last separating line.
		for (int i = 1 ; i < 9; i ++) {
			char columnLetter = (char) (i + 96);
			System.out.print("-----" + columnLetter + "----");
		}
		// Ending the line with the last "-" char.
		System.out.println("-");
	}
}
