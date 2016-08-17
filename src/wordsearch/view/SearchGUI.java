/**********************************************************************************
 * SearchGUI.java
 * Description: User Interface for wordsearch game
 * 
 * Langston Graham Created: 04/29/2014
 *********************************************************************************/

package wordsearch.view;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;

import wordsearch.model.DefinedWord;
import wordsearch.model.WordList;


public class SearchGUI {
	public static void main(String[] args) {
		//Path to wordlist file
		Path file=Paths.get("C:\\Users\\Langston.Graham\\Documents\\wordsearchlist.txt");
		//Read in file and add words
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file.toString()));
			String line=null;
			while((line=reader.readLine())!=null) {
				WordList.addWord(new DefinedWord(line,""));
			}
			reader.close();
		} catch(IOException ie) {ie.printStackTrace();System.exit(1);}
		//Create new wordsearch
		new SearchGUI();
	}
	
	//Create GUI
	public SearchGUI() {
		
		
		JFrame searchFrame=new JFrame();
		searchFrame.setTitle("WordSearch Extreme");
		searchFrame.setLayout(new GridLayout(0,1));
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		searchFrame.add(new SearchPanel());	
		searchFrame.pack();
		searchFrame.setLocationRelativeTo(null);
		searchFrame.setVisible(true);
	}

}
