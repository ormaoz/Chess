/*

Exercise number      :    10

File Name            :    SimpleView.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package view;

import javax.activity.InvalidActivityException;

import model.Board;
import model.Cell;
import model.ChessPiece;

/**
 * Represents the view (V) part of a chess game designed along the MVC pattern.
 * This particular view displays each chess piece using a single letter.
 * 
 * @author Intro2CS - IDC 2012
 */
public class SimpleView implements View {

	/** Default constructor. */
	public SimpleView() {
	}

	/**
	 * Draws the given board. Each chess piece is displayed using a single
	 * character. The white pieces and the black pieces are drawn using
	 * lower-case and upper-case letters, respectively. For example, the white
	 * king is drawn as "k", and the black king as "K".
	 * 
	 * @param board
	 *            the board to draw
	 * @throws InvalidActivityException
	 *             - if an internal error occurred
	 */
	@Override
	public void draw(Board board) throws InvalidActivityException {
		// Draws the top of the board.
		System.out.println("   a b c d e f g h ");
		System.out.println("  -----------------");
		// prints line number
		for (int i = 0; i < 8; i++) {
			System.out.print(8 - i + " ");
			// prints separation between cells
			for (int j = 0; j < 8; j++) {
				System.out.print("|");
				// prints what's inside the cell
				if (board.getCell(i, j).isEmpty()) {
					System.out.print(" ");
				} else {
					System.out.print(board.getCell(i, j).getPiece().
															toStringSimple());
				}
			}
			// end the row and print line between rows
			System.out.print("|");
			System.out.println();
			System.out.println("  -----------------");
		}
		// end board and prints bottom of board.
		System.out.println("   a b c d e f g h ");
	}
}
