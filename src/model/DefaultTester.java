package model;

import static org.junit.Assert.*;

import javax.activity.InvalidActivityException;

import model.ChessPiece.PieceColor;
import model.ChessPiece.PieceType;

import org.junit.Before;
import org.junit.Test;

public class DefaultTester {

	@Before
	public void setUp() throws Exception {
		model = new ChessModel();
	}

	@Test
	public void testInitialState() {
		Board gameBoard = model.getBoard();
		isAtInitialState(gameBoard);
	}

	@Test
	public void testMoves() throws InvalidActivityException {
		assertFalse("This move should not succeed! There is no piece at (3,7)...",
			model.movePiece(new Location(3, 6), new Location(3, 7)));

		assertFalse("This move should not succeed! The cell (6,3) already has a piece in it.",
				model.movePiece(new Location(6,4), new Location(6,3)));

		assertFalse("This move should not succeed! It is not the black player's turn.",
				model.movePiece(new Location(6,4), new Location(6,3)));

		isAtInitialState(model.getBoard());

		assertFalse("This move should not succeed! A knight cannot move from (0,1) to (2,1).",
				model.movePiece(new Location(0, 1), new Location(2, 1)));

		assertFalse("It is not the black player's turn!",
				model.movePiece(new Location(0, 1), new Location(2, 2)));

		assertTrue("The knight should be able to move from (7,1) to (5,2).",
				model.movePiece(new Location(7, 1), new Location(5, 2)));

		assertTrue("The cell (7,1) should be empty now, and it isn't.",
				model.getBoard().getCell(7, 1).isEmpty());

		assertFalse("The cell (5,2) is empty, when it should contain the knight.",
				model.getBoard().getCell(5, 2).isEmpty());

		ChessPiece piece = model.getBoard().getCell(5,2).getPiece();
		assertEquals("The cell (5,2) should contain the knight. Instead, it contains a " + piece.getType(),
				PieceType.KNIGHT, piece.getType());
		assertEquals("The cell (5,2) should contain a black knight. Instead, it contains a " + piece.getColor() + " knight.",
				PieceColor.WHITE, piece.getColor());
	}

	/* --- Private Methods --- */

	private void isAtInitialState(Board board) {
		checkTopBottomCellsEmpty(board);
		checkMiddleCellsNotEmpty(board);
		try {
			checkColors(board);
			checkPiecesTypes(board);
		} catch (InvalidActivityException e) {
			assertTrue("An InvalidActivityException was thrown!", false);
		}
	}

	/**
	 * Checks if the top two rows and the bottom two rows are not empty.
	 * @param gameBoard The tested {@link GameBoard}.
	 * @return true iff these rows are not empty.
	 */
	private void checkTopBottomCellsEmpty(Board gameBoard) {
		int[] notEmptyRows = {0,1,6,7};
		for (int i = 0; i < notEmptyRows.length; i++) {
			for (int j = 0; j < Board.NUMBER_OF_ROWS; j++) {
				Cell currentCell = gameBoard.getCell(notEmptyRows[i], j);
				assertFalse("The cell (" + notEmptyRows[i] + "," + j + ") is empty, when it shouldn't.", currentCell.isEmpty());
			}
		}
	}

	/**
	 * Checks if rows 2,3,4,5 are empty.
	 * @param gameBoard The tested {@link GameBoard}.
	 * @return true iff these rows are empty.
	 */
	private void checkMiddleCellsNotEmpty(Board gameBoard) {
		for (int i = 2; i < 6 ; i++) {
			for (int j = 0; j < Board.NUMBER_OF_ROWS; j++) {
				Cell currentCell = gameBoard.getCell(i, j); 
				assertTrue("The cell (" + i + "," + j + ") is not empty, when it should be.", currentCell.isEmpty());
			}
		}
	}

	/**
	 * Checks if the colors of the pieces are correct: the top two rows are black, and the bottom two rows are white.
	 * @param gameBoard
	 * @return true iff the colors of the pieces are correct for the initial state.
	 * @throws InvalidActivityException
	 */
	private void checkColors(Board gameBoard) throws InvalidActivityException {
		for (int i = 0; i < 2 ; i++) {
			for (int j = 0; j < Board.NUMBER_OF_ROWS; j++) {
				Cell currentCell = gameBoard.getCell(i, j);
				assertTrue("The cell (" + i + "," + j + ") is white, when it should be black.", currentCell.getPiece().getColor() == PieceColor.BLACK);
			}
		}
		for (int i = Board.NUMBER_OF_COLUMNS-2; i < Board.NUMBER_OF_COLUMNS ; i++) {
			for (int j = 0; j < Board.NUMBER_OF_ROWS; j++) {
				Cell currentCell = gameBoard.getCell(i, j);
				assertTrue("The cell (" + i + "," + j + ") is black, when it should be white.", currentCell.getPiece().getColor() == PieceColor.WHITE);
			}
		}
	}

	/**
	 * Checks if the pieces types are correct for the initial state.
	 * @param gameBoard The tested {@link GameBoard}
	 * @return true iff the types are correct.
	 * @throws InvalidActivityException 
	 */
	private void checkPiecesTypes(Board gameBoard) throws InvalidActivityException {

		for (int i = 0 ; i != 7; i = 7) {
			String message = "A piece at row " + i + " is not in the correct type.";
			assertTrue(message, gameBoard.getCell(i, 0).getPiece().getType() == PieceType.ROOK);
			assertTrue(message, gameBoard.getCell(i, 1).getPiece().getType() == PieceType.KNIGHT);
			assertTrue(message, gameBoard.getCell(i, 2).getPiece().getType() == PieceType.BISHOP);
			assertTrue(message, gameBoard.getCell(i, 3).getPiece().getType() == PieceType.QUEEN);
			assertTrue(message, gameBoard.getCell(i, 4).getPiece().getType() == PieceType.KING);
			assertTrue(message, gameBoard.getCell(i, 5).getPiece().getType() == PieceType.BISHOP);
			assertTrue(message, gameBoard.getCell(i, 6).getPiece().getType() == PieceType.KNIGHT);
			assertTrue(message, gameBoard.getCell(i, 7).getPiece().getType() == PieceType.ROOK);

		}
		for (int i = 1 ; i != 6 ; i = 6) {
			for (int j = 0 ; j < Board.NUMBER_OF_COLUMNS ; j++) {
				assertTrue("A piece at (" + i + "," + j + ") should be a pawn and it's not.", gameBoard.getCell(i,j).getPiece().getType() == PieceType.PAWN);
			}
		}
	}

	/* --- Private Members --- */

	private ChessModel model;

}
