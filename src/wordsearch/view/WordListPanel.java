package wordsearch.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import wordsearch.model.DefinedWord;

public class WordListPanel  extends JPanel{
	private static final Dimension preferredDimensions=new Dimension(120,450);
	private JList<String> displayList;
	private DefaultListModel <String> displayModel;
	private List<DefinedWord> availableWordList;
	private List<DefinedWord> foundWordList;
	private final Font STRIKETHROUGH;
	
	public WordListPanel() {
		displayList=new JList<String>();
		displayModel=new DefaultListModel<String>();

		//Setup STRIKETHROUGH font
		Font font = new Font("helvetica", Font.PLAIN, 12);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
		STRIKETHROUGH = new Font(attributes);
		
		displayList.setModel(displayModel);
		
		displayList.setPreferredSize(preferredDimensions);
		this.add(displayList);
	}
	
	public void newSetup(List<DefinedWord> words) {
		availableWordList=words;
		foundWordList=new ArrayList<DefinedWord>();
		updateList();
	}
	
	public void updateList() {
		Collections.sort(availableWordList);
		Collections.sort(foundWordList);
		displayModel.clear();
		displayList.setCellRenderer(new StrikeThroughRenderer());
		for(DefinedWord definedWord : availableWordList) {
			displayModel.addElement(definedWord.getWord());
		}
		for(DefinedWord definedWord : foundWordList) {
			displayModel.addElement(definedWord.getWord());
		}
	}
	
	//Determine if word is within the list of chosen words
	public boolean isInWordList(String word) {
		return availableWordList.contains(new DefinedWord(word,""));
	}
	
	public void wordFound(String word) {
		DefinedWord definedWord=availableWordList.get(availableWordList.indexOf(new DefinedWord(word,"")));
		availableWordList.remove(definedWord);
		foundWordList.add(definedWord);
		updateList();
	}
	
	public boolean isFinished() {
		return availableWordList.isEmpty();
	}
	
	private class StrikeThroughRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, 
				boolean isSelected, boolean cellHasFocus) {
			Component comp=super.getListCellRendererComponent(list, value,index, isSelected, cellHasFocus);
			//Start of foundWordList, use STRIKTHROUGH font
			if(index >= availableWordList.size())
			{
				comp.setFont(STRIKETHROUGH);
			}
			return comp;
		}
	}
}
