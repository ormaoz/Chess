/*

Exercise number      :    10

File Name            :    Location.java

Name (First Last)    :    Or Maoz

Student ID           :    029983111

Email                :    or.maoz1@post.idc.ac.il

 */
package model;

/**
 * Represents a row-column location on an 8x8 two-dimensional board.
 * The location consists of two integers: a row index (0 to 7)
 * and a column index (0 to 7).
 * The location of the upper-left cell of the board is (0,0), and the 
 * location of the bottom-right cell of the board is (7,7).
 * Location objects are widely used throughout the system.
 * In particular, they are used by many methods that receive or pass 
 * parameters that represent board locations.   
 * 
 * @author Intro2CS - IDC 2012
 */
public class Location {	
	// The row and column fields are package-private.
	int row;
	int column;
	
	/**
	 * Constructs a new location.
	 * 
	 * @param row the row index (0 to 7)
	 * @param column the column index (0 to 7)
	 */  

	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Checks if this location is the same as the other location.
	 * 
	 * @param other the other location
	 * @return True if the locations are the same, false otherwise.
	 */	
	@Override
	public boolean equals(Object obj) {
		// Validates that the object is indeed a location.
		if (obj instanceof Location) {
			// If it is, cast it to a location and compare 
			// row & column variables to this location.
			Location other = (Location) obj;
			return ((this.row == other.row) && (this.column == other.column));
		}
		return false;
	}
	
	/**
	 * Hash code of this location.
	 * 
	 * @return The hash code of this location.
	 */  
	@Override
	public int hashCode() {
		// Creates a unique hash code for each location using a prime number.
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}	
	
	/**
	 * A textual representation of this location, for debugging purposes.
	 * 
	 * @return A textual representation of this location.
	 */ 
	@Override
	public String toString() {
		return "Location [row=" + row + ", column=" + column + "]";
	}

}