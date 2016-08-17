package failedattempts;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel{
	
	private Character[][] wordSearchGrid;
	
	public SearchTableModel(Character[][] wordSearchGrid) {
		this.wordSearchGrid=wordSearchGrid;
	}
	
	@Override
	public int getColumnCount() {
		return wordSearchGrid.length;
	}

	@Override
	public int getRowCount() {
		return wordSearchGrid.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return wordSearchGrid[arg0][arg1];
	}

}
