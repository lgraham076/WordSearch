package wordsearch.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import wordsearch.model.CellGrid;
import wordsearch.model.CharacterCell;
import wordsearch.model.Direction;

public class GridPanel extends JPanel {
	private static final Dimension preferredDimensions=new Dimension(450,450);
	
	private CellGrid grid;
	private CharacterCell anchor;
	private List<CharacterCell> cellsSelected=new ArrayList<CharacterCell>();
	private boolean inDragMode;
	private final Point START=new Point(25,25);
	private final int xSpacing=25;
	private final int ySpacing=25;
	
	
	public GridPanel() {
		this.setPreferredSize(preferredDimensions);
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Monospaced",Font.BOLD ,20));
		int y=START.y;
		for(int row=0; row < grid.getSize(); row++) {
			int x=START.x;
			for(int column=0; column < grid.getSize(); column++) {
				if(grid.isCellSelected(column, row)) {
					g.setColor(CharacterCell.SELECTEDCOLOR);
					g.fillRect(x, y, xSpacing, ySpacing);
				}
				else if(grid.isCellFound(column, row)) {
					g.setColor(grid.getCell(column, row).getFoundColor());
					g.fillRect(x, y,xSpacing, ySpacing);
				}

				g.setColor(Color.BLACK);
				g.drawRect(x, y, xSpacing, ySpacing);
				g.drawString(grid.getCell(column, row).getLetter().toString(), x+5, y+20);
							
				x+=xSpacing;
			}
			y+=ySpacing;
		}
	}
	
	public void newSetup(Character[][] letters) {
		this.inDragMode=false;
		grid=new CellGrid(letters);
		anchor=grid.getCell(0, 0);
		repaint();
	}
	
	private int getColumn(int coordX) {
		int column=(coordX-START.x)/xSpacing;
		if(column < 0 || column >= grid.getSize()) {
			return -1;
		}
		else {
			return column;
		}
	}
	
	private int getRow(int coordY) {
		int row=(coordY-START.y)/ySpacing;
		if(row <0 || row >= grid.getSize())
		{
			return -1;
		}
		else {
			return row;
		}
	}
	
	public String getCurrentWord() {
		StringBuffer word=new StringBuffer();
		for(CharacterCell cell: cellsSelected) {
				word.append(cell.getLetter());
		}
		return word.toString();
	}
	
	public void wordFound() {
		Color foundColor=CharacterCell.getNewFoundColor();
		for(CharacterCell cell : cellsSelected) {
			
			grid.setCellToFound(cell.getCellPoint(),foundColor);
		}
		unselectCells();
	}
	
	public void drag(int coordX, int coordY) {
		Point cellPoint=new Point(getColumn(coordX),getRow(coordY));
		if(cellPoint.x >=0  && cellPoint.y >= 0) {
			CharacterCell currCell=grid.getCell(cellPoint.x, cellPoint.y);
			if(!inDragMode) {
				inDragMode=true;
				anchor=currCell;
			}
			
			if(CharacterCell.cellsInLine(anchor,currCell)) {
				addSelectedCell(currCell);
			}
		}
	}
	
	public void release() {
		inDragMode=false;
		unselectCells();
	}
	
	private void addSelectedCell(CharacterCell addCell) {
		unselectCells();
		cellsSelected.add(anchor);
		grid.toggleSelectedCell(anchor.getCellPoint());
		if(!addCell.equals(anchor)) {
			//Add all cells in between
			Direction direction=CharacterCell.getDirection(anchor, addCell);
			int x=anchor.getX();
			int y=anchor.getY();
			
			x+=direction.getMove().x;
			y+=direction.getMove().y;
			
			while(!(y==addCell.getY() && x==addCell.getX())) {
				cellsSelected.add(grid.getCell(x, y));
				grid.toggleSelectedCell(x, y);
				
				x+=direction.getMove().x;
				y+=direction.getMove().y;
			}
			
			cellsSelected.add(addCell);
			grid.toggleSelectedCell(addCell.getCellPoint());
		}
		
		repaint();
	}
	
	private void unselectCells() {
		for(CharacterCell cell : cellsSelected) {
			grid.toggleSelectedCell(cell.getCellPoint());
		}
		cellsSelected.clear();
		repaint();
	}
}
