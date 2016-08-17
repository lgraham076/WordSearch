package wordsearch.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import wordsearch.controller.WordSearch;

public class SearchPanel extends JPanel{
	private GridPanel gridPanel;
	private WordListPanel wordPanel;
	JButton replayBtn;
	WordSearch searchGame;
	
	
	public SearchPanel() {		
		gridPanel=new GridPanel();
		wordPanel=new WordListPanel();
		
		replayBtn=new JButton("replay");
		replayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setup();
			}
		});
		replayBtn.setEnabled(false);
		
		setup();
		
		this.add(gridPanel);
		this.add(wordPanel);
		this.add(replayBtn);
	}
	
	public void setup() {
		searchGame=new WordSearch();
		try {
			searchGame.setUpGame();
		} catch(Exception try1) {
			//Try one more time, if it fails exit
			try {
				searchGame.setUpGame();
			} catch(Exception try2) {
				System.out.println("Could not setup game!");
				System.exit(1);
			}
		}
		
		gridPanel.newSetup(searchGame.getSearchGrid());
		gridPanel.addMouseMotionListener(new MouseActions());
		gridPanel.addMouseListener(new MouseActions());	
		wordPanel.newSetup(searchGame.getChosenWords());
		
		replayBtn.setEnabled(false);
	}
	
	private class MouseActions extends MouseAdapter{
		@Override
		public void mouseDragged(MouseEvent me) {
			Point mousePoint=me.getPoint();
			//Call drag to determine new points and update gridpanel gui
			gridPanel.drag(mousePoint.x,mousePoint.y);
			String word=gridPanel.getCurrentWord();
			if(wordPanel.isInWordList(word)) {
				gridPanel.wordFound();
				wordPanel.wordFound(word);
				replayBtn.setEnabled(true);
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			//Call release to determine new points and update gridpanel gui
			gridPanel.release();
		}
	}
}
