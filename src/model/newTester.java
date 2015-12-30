package model;

import java.util.Scanner;

import javax.activity.InvalidActivityException;

import controller.ChessControler;
import view.AsciiView;
import view.SimpleView;
import view.View;
import model.Board;
import model.ChessModel;

public class newTester {

	/**
	 * @param args
	 * @throws InvalidActivityException 
	 */
	public static void main(String[] args) throws InvalidActivityException {
		ChessModel model = new ChessModel();
		View view = new SimpleView();
		view.draw(model.getBoard());

		model.getBoard().getCell(2,2).setPiece(model.getBoard().getCell(0,1).getPiece());
		model.getBoard().getCell(0,1).setToEmpty();
		view.draw(model.getBoard());
		
	}

}
