/*

Exercise number      :    10

File Name            :    Main.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
import controller.ChessControler;
import view.AsciiView;
import view.SimpleView;
import view.View;
import model.Board;
import model.ChessModel;

public class Main {

	/**
	 * Entry point to the chess program. creates the model, view and controller
	 * objects and plays the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			ChessModel model = new ChessModel();
			// There are two alternative ways to display the chess pieces: a
			// simple view, and an ASCII view. The currently active view is the
			// simple view. To activate the ASCII view, de-comment the next
			// command and comment the command after it.
			View view = new AsciiView();
			//View view = new SimpleView();
			ChessControler controller = new ChessControler(model, view);
			controller.playGame();
		} catch (Exception e) {
			System.out.println("An interanl error ocured");
		}

	}

}
