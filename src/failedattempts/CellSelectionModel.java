package failedattempts;

import java.awt.Point;

public class CellSelectionModel 
{
	private boolean[][] cellsSelected;
	private final int NUMROWS;
	private final int NUMCOLS;
	private Point anchor;
	
	public CellSelectionModel(int numRows,int numCols) {
		NUMROWS=numRows;
		NUMCOLS=numCols;
		anchor=new Point(0,0);
		cellsSelected=new boolean[NUMROWS][NUMCOLS];
	}
	
	public void addSelection(int row,int col) {
		cellsSelected[row][col]=true;
	}
	
	public void removeSelection(int row,int col) {
		cellsSelected[row][col]=false;
	}
	
	public void toggleSelection(int row,int col) {
		cellsSelected[row][col]=!cellsSelected[row][col];
		setAnchor(row,col);
	}
	
	public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
		if(extend) {
			
		}
		else {
			if(toggle) {
				toggleSelection(rowIndex,columnIndex);
			}
			else {
				clearSelection();
			}
		}
	}
	
	public void clearSelection() {
		for(int row=0; row < NUMROWS; row++) {
			for(int col=0; col < NUMCOLS; col++) {
				cellsSelected[row][col]=false;
			}
		}
		
		setAnchor(NUMROWS-1,NUMCOLS-1);
	}
	
	public boolean isCellSelected(int row,int col) {
		return cellsSelected[row][col];
	}
	
	private void setAnchor(int row,int col) {
		anchor.x=col;
		anchor.y=row;
	}
}
