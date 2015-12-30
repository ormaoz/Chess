/*

Exercise number      :    10

File Name            :    ChessControler.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package controller;

import java.util.Scanner;
import view.View;
import model.Board;
import model.ChessPiece.PieceColor;
import model.ChessModel;
import model.Location;

/**
 * Represents the controller (C) part of a chess game designed along the MVC
 * pattern. The game consists of two players (white and black) who take turns
 * and enter chess moves. The controller uses the console to interact with the
 * players and execute their moves.
 * 
 * @author Intro2CS - IDC 2012
 */
public class ChessControler {

	private ChessModel model;
	private View view;
	private Boolean turn = false;
	private Location sourceLocation;
	private Location targetLocation;

	/**
	 * Creates a controller object.
	 * 
	 * @param model
	 *            the object that represents the logic of the game
	 * @param view
	 *            the object whose job is to display a certain view of the
	 *            game's output
	 */
	public ChessControler(ChessModel model, View view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Plays the game.
	 * 
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public void playGame() throws Exception {
		// Creates a board and draws it.
		Board board = model.getBoard();
		view.draw(board);

		// Displays preliminary information about the game,
		// Sets up the first player, and gets ready to play.
		System.out.println("Welcome to the chess game");
		
		// As long as the game is not over, gets moves from the current player,
		// and executes them.
		while (!model.isGameOver()) {
			// Displays a prompt
			// Gets the move's data from the player
			// Validates and parses the move's data
			// If the input is not valid, prints an error message.
			turn = !turn;
			System.out.print(turnPrint(turn));
			Scanner scn = new Scanner(System.in);
			String input = scn.nextLine();
			// Continue with the game only if the input is valid 
			while (!inputCheck(input)) {
				System.out.print(turnPrint(turn));
				input = scn.nextLine();
			}
			// If the move is valid, informs the model to move the piece.
			// If the move is not valid, loops to re-enter another move.
			while (!model.movePiece(sourceLocation(input), 
					targetLocation(input))) {
				System.out.println("Illeagal move!");
				System.out.print(turnPrint(turn));
				input = scn.nextLine();
			}
			// Draws the board after the move
			view.draw(board);
		}
		// Displays information about the end of the game, and terminates the
		// game.
		System.out.println("Game over. The winner is: " + 
												model.getWinner());
	}
	
	/**
	 * calculate user input into a source location
	 * @param input user's input
	 * @return the source location
	 */
	private Location sourceLocation (String input) {
		int sourceColumn = (int)input.charAt(0) - 97;
		int sourceRow = (int)8 - ((input.charAt(1) - 48));
		sourceLocation = new Location(sourceRow, sourceColumn);
		return sourceLocation;
	}
	
	/**
	 * calculate user input into a target location
	 * @param input user's input
	 * @return the target location
	 */
	private Location targetLocation (String input) {
		int targetColumn = (int)input.charAt(3) - 97;
		int targetRow = (int)8 - ((input.charAt(4) - 48));
		targetLocation = new Location(targetRow, targetColumn);
		return targetLocation;
	}
	
	/**
	 * Changes the output printed to user between BLACK and WHITE 
	 * @param turn
	 * @return "WHITE" or "BLACK" depends on whose turn is it.
	 */
	private String turnPrint (Boolean turn) {
		if (turn) {
			return "WHITE Player, make a move: ";
		} else {
			return "BLACK Player, make a move: ";
		}
	}
	
	/**
	 * A private method that checks if the location is inside the board
	 * @param sourceLocation
	 * @param targetLocation
	 * @return true is the location is inside the board.
	 */
	private Boolean inputCheck (String input) {
		// Checks input is in the right length.
		if (input.length() == 5) {
			// Convert string input into board coordinates.
			int sourceColumn = (int)input.charAt(0) - 97;
			int sourceRow = (int)8 -(input.charAt(1) - 48);
			int targetColumn = (int)input.charAt(3) - 97;
			int targetRow = (int)8 - (input.charAt(4) - 48);
			// Validates the input is on the board
			if (sourceRow > 7 || sourceRow < 0 || sourceColumn > 7 || 
					sourceColumn < 0 || targetRow > 7 || targetRow < 0 || 
					targetColumn > 7 || targetColumn < 0) {
				System.out.println("Location is out of the board");
				return false;
			}
			return true;
		}
		// In case of less or more than 5 characters:
		System.out.println("Invalid Input");
		return false;
	}
}