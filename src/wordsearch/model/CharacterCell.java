/**********************************************************************************
 * CharacterCell.java
 * Description: Cell used in wordsearch grid to represent a given letter and it's
 * attributes
 * 
 * Langston Graham Created: 04/29/2014
 *********************************************************************************/

package wordsearch.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class CharacterCell {
	public static final Color FOUNDCOLORS[]={Color.YELLOW,Color.PINK,Color.ORANGE,Color.CYAN,Color.GREEN};
	private Color foundColor;
	public static final Color SELECTEDCOLOR=Color.LIGHT_GRAY;
	
	private Character letter;
	private boolean isSelected;
	private boolean isFound; //Character is part of word already found by user	
	private int x;
	private int y;

	//Initialize CharacterCell
	public CharacterCell(Character letter, int x, int y) {
		this.letter=letter;
		this.x=x;
		this.y=y;
		this.isSelected=false;
		this.isFound=false;
	}
	//Obtain the letter contained within cell
	public Character getLetter() {
		return letter;
	}
	//Switch cell to found
	public void setToFound(Color foundColor) {
		isFound=true;
		this.foundColor=foundColor;
	}
	//Determine if cell is part of a chosen word
	public boolean isFound() {
		return isFound;
	}
	//Switch whether cell is selected
	public void toggleSelected() {
		isSelected=!isSelected;
	}
	//Determine if cell is currently selected
	public boolean isSelected() {
		return isSelected;
	}
	//Obtain the x value of the cell
	public int getX() {
		return x;
	}
	//Obtain the y value of the cell
	public int getY() {
		return y;
	}
	//Obtain the point of the current cell
	public Point getCellPoint() {
		return new Point(x,y);
	}
	//Returns a random color from the array of FOUNDCOLORS
	public static Color getNewFoundColor() {
		Random rand=new Random();
		return FOUNDCOLORS[rand.nextInt(FOUNDCOLORS.length)];
	}
	//Obtain the current found color
	public Color getFoundColor() {
		return foundColor;
	}
	//Changes the found color for the cell
	public void setFoundColor(Color color) {
		foundColor=color;
	}
	//Determine the direction the new cell is from the original cell
	public static Direction getDirection(CharacterCell cell,CharacterCell otherCell) {
		if(diagonal(cell,otherCell)) {
			//WEST
			if(cell.getX()-otherCell.getX() > 0)
			{
				//SOUTH
				if((cell.getY()-otherCell.getY()) > 0) {
					return Direction.NORTHWEST;
				}
				//NORTH
				else {return Direction.SOUTHWEST;}
			}
			//EAST
			else
			{
				//SOUTH
				if((cell.getY()-otherCell.getY()) > 0) {
					return Direction.NORTHEAST;
				}//NORTH
				else {return Direction.SOUTHEAST;}
			}
		}
		//Return whether cell is EAST or WEST of other cell
		else if(horizontal(cell,otherCell)) {
			return ((cell.getX()-otherCell.getX()) > 0 ? Direction.WEST : Direction.EAST);
		}
		//Return whether cell is NORTH or SOUTH of other cell
		else if(vertical(cell,otherCell)) {
			return ((cell.getY()-otherCell.getY()) > 0 ? Direction.NORTH  : Direction.SOUTH);
		}
		else {
			return null;
		}
	}
	//Determines if are in line with each other
	public static boolean cellsInLine(CharacterCell newCell,CharacterCell olderCell) {
		return (horizontal(newCell,olderCell) || vertical(newCell,olderCell) || diagonal(newCell,olderCell));		
	}
	//Determines if points are on the same horizontal plane, in other words the same y value
	public static boolean horizontal(CharacterCell newCell,CharacterCell olderCell) {
		return newCell.getY()==olderCell.getY();
	}
	//Determines if points are on the same vertical plane, in other words the same x value
	public static boolean vertical(CharacterCell newCell,CharacterCell olderCell) {
		return newCell.getX()==olderCell.getX();
	}
	//Determines if the two points line up diagonally
	public static boolean diagonal(CharacterCell newCell,CharacterCell olderCell) {
		return (Math.abs(newCell.getX()-olderCell.getX())==Math.abs(newCell.getY()-olderCell.getY()));
	}
	
	@Override
	//Determine if two cells are equal
	public boolean equals(Object obj) {
		//Two cells are equal if they exist on the same row and column
		if(obj instanceof CharacterCell) {
			CharacterCell otherCell=(CharacterCell)obj;
			return (this.y==otherCell.y && this.x==otherCell.x) ;
		} else {
			return false;
		}
	}
}
