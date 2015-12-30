/*

Exercise number      :    10

File Name            :    View.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package view;

import javax.activity.InvalidActivityException;

import model.Board;

/**
 * Represents the view (V) part of a chess game designed along the MVC pattern.
 * 
 * @author Intro2CS - IDC 2012
 */
public interface View {
	
	/**
	 * Draws the given board.
	 * 
	 * @param board the board to draw
	 * @throws InvalidActivityException - if an internal error occurred
	 */
	void draw (Board board) throws InvalidActivityException;
}
