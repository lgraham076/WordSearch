/**********************************************************************************
 * CellGrid.java
 * Description: Grid used to hold and manipulate a group of CharacterCells
 * 
 * Langston Graham Created: 04/29/2014
 *********************************************************************************/

package wordsearch.model;

import java.awt.Color;
import java.awt.Point;

public class CellGrid {
	private CharacterCell[][] cells;
	
	//Initialize the CellGrid
	public CellGrid(Character[][] letters) {
		setupCells(letters);
	}
	//Returns the size of the grid
	public int getSize() {
		return cells.length;
	}
	
	//Convert character array to a more functional CharacterCell array
	public void setupCells(Character[][] letters) {
		cells=new CharacterCell[letters.length][letters.length];
		for(int y=0; y < letters.length; y++) {
			for(int x=0; x < letters.length; x++) {
				cells[x][y]=new CharacterCell(letters[x][y],x,y);
			}
		}
	}
	//Get the cell at the given row and column
	public CharacterCell getCell(int x,int y) {
		return cells[x][y];
	}
	//Determine if cell has been selected
	public boolean isCellSelected(int x, int y) {
		return cells[x][y].isSelected();
	}
	//Change the selection status of the cell at the given point
	public void toggleSelectedCell(Point pt) {
		toggleSelectedCell(pt.x,pt.y);
	}
	//Change the selection status of the cell at the given row and column
	public void toggleSelectedCell(int x,int y) {
		cells[x][y].toggleSelected();
	}
	//Set the cell at the given point to found
	public void setCellToFound(Point pt, Color foundColor) {
		setCellToFound(pt.x,pt.y,foundColor);
	}
	//Set the cell at the given row and column to found
	public void setCellToFound(int x, int y, Color foundColor) {
		cells[x][y].setToFound(foundColor);
	}
	//Determine if the cell at the given row and column has been found
	public boolean isCellFound(int x,int y) {
		return cells[x][y].isFound();
	}
}
