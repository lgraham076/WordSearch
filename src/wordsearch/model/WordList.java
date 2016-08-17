/**********************************************************************************
 * WordList.java
 * Description: Holds list of words read from file
 * 
 * Langston Graham Created: 04/24/2014
 *********************************************************************************/

package wordsearch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {
	private static final int MAX_SMALL_WORD=5;
	private static final int MAX_MEDIUM_WORD=10;
	private static final int MAX_LARGE_WORD=15;
	private static List<DefinedWord> smallWords=new ArrayList<DefinedWord>();
	private static List<DefinedWord> mediumWords=new ArrayList<DefinedWord>();
	private static List<DefinedWord> largeWords=new ArrayList<DefinedWord>();
	
	//Add new word to one of the word lists based on it's size
	public static void addWord(DefinedWord definedWord) {
		
		int wordLength=definedWord.getWordLength();
		if(wordLength <= MAX_SMALL_WORD) {
			smallWords.add(definedWord);
		}
		else if(wordLength <= MAX_MEDIUM_WORD) {
			mediumWords.add(definedWord);
		}
		else if(wordLength <= MAX_LARGE_WORD) {
			largeWords.add(definedWord);
		}
	}
	//Gets a random word from the small list
	public static DefinedWord getRandomSmall() {
		if(smallWords.size() > 0) {
			Random rand=new Random();
			return smallWords.get(rand.nextInt(smallWords.size()));
		}
		else { return null;}
	}
	//Gets a random word from the medium list
	public static DefinedWord getRandomMedium() {
		if(mediumWords.size() > 0) {
			Random rand=new Random();
			return mediumWords.get(rand.nextInt(mediumWords.size()));
		}
		else { return null;}
	}
	//Gets a random word from the large list
	public static DefinedWord getRandomLarge() {
		if(largeWords.size() > 0) {
			Random rand=new Random();
			return largeWords.get(rand.nextInt(largeWords.size()));
		}
		else { return null;}
	}

}
