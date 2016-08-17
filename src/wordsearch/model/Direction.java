/**********************************************************************************
 * Direction.java
 * Description: Holds constants for directional values
 * 
 * Langston Graham Created: 04/24/2014
 *********************************************************************************/

package wordsearch.model;

import java.awt.Point;

public enum Direction {
	NORTH(0,-1),
	SOUTH(0,1),
	EAST(1,0),
	WEST(-1,0),
	NORTHWEST(-1,-1),
	NORTHEAST(1,-1),
	SOUTHWEST(-1,1),
	SOUTHEAST(1,1);
	
	private final int xMove;
	private final int yMove;
	
	Direction(int xMove,int yMove){
		this.xMove=xMove;
		this.yMove=yMove;
	}
	
	public Point getMove() {
		return new Point(xMove,yMove);
	}
}
