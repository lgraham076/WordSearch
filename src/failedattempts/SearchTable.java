package failedattempts;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchTable extends JTable implements MouseMotionListener{
	
	
	public SearchTable(SearchTableModel tblModel) {
		super(tblModel);
		this.setTableHeader(null);
		this.setShowGrid(false);
		this.setCellSelectionEnabled(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setFont(new Font("Serif",Font.PLAIN ,20));
		this.setRowHeight(25);
		
		this.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		int []rows=this.getSelectedRows();
		int []columns=this.getSelectedColumns();
		this.clearSelection();
		this.addRowSelectionInterval(rows[0], rows[rows.length-1]);
		this.addColumnSelectionInterval(columns[0], columns[columns.length-1]);
		//this.addRowSelectionInterval(rows[rows.length-1], rows[]);
		//this.addColumnSelectionInterval(columns[], columns[columns.length-1]);
		System.out.println("Start:"+columns[0]+","+rows[0]+"End:"+columns[columns.length-1]+","+rows[rows.length-1]);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
}
