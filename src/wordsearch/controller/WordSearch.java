/**********************************************************************************
 * WordSearch.java
 * Description: Holds and sets up word search game
 * 
 * Langston Graham Created: 04/24/2014
 *********************************************************************************/

package wordsearch.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wordsearch.model.Direction;
import wordsearch.model.DefinedWord;
import wordsearch.model.WordList;

public class WordSearch {
	private final int GRID_SIZE=16;
	private final int MAX_NUMBER_WORDS=24;

	private List<DefinedWord> chosenWords;
	private Character[][] searchGrid;
	
	public int getGridSize() {
		return GRID_SIZE;
	}
	
	//Sets up wordsearch game placing randomly chosen words at random points
	public void setUpGame() throws Exception {
		searchGrid=new Character[GRID_SIZE][GRID_SIZE];
		setWords();
		Random rand=new Random();
		int index=0;
		boolean setupComplete=false;
		while(!setupComplete) {
			DefinedWord definedWord=chosenWords.get(index);
			//Keep looping through word until found appropriate spot for word
			boolean wordPlaced=false;
			int attemptedPlaces=0;
			while(!wordPlaced) {
				attemptedPlaces++;
				if(attemptedPlaces > 50) {
					//Attempt manual placement
					wordPlaced=manuallyPlaceWord(definedWord);
					//If word still not placed thrown and error
					if(!wordPlaced) {
						System.out.println("Had trouble fitting "+definedWord+"!");
						printSearchGrid();
						throw new Exception();
					}
					
				}
				
				Point pt=new Point(rand.nextInt(GRID_SIZE),rand.nextInt(GRID_SIZE));
				wordPlaced=placeWord(pt,definedWord);
			}
			
			index++;		
			if(index==chosenWords.size()) {
				setupComplete=true;
			}
		}
		//Fill in remaining empty spaces with random letters
		for(int outer=0; outer < GRID_SIZE; outer++) {
			for(int inner=0; inner < GRID_SIZE; inner++) {
				if(searchGrid[inner][outer]==null) {
					final int NUMLETTERS=26;
					char letter=(char)(rand.nextInt(NUMLETTERS)+'a');
					searchGrid[inner][outer]=letter;
				}
			}
		}
	}
	//Figure out what directions are possible based on the amount of space available in all directions
	private List<Direction> getPossibleDirections(Point pt,int wordLength) {
		List<Direction> directions=new ArrayList<Direction>();
		
		//Check East
		if(GRID_SIZE-pt.x >= wordLength) {
			directions.add(Direction.EAST);
		}
		//Check West
		if(pt.x+1 >= wordLength) {
			directions.add(Direction.WEST);
		}
		//Check North
		if(pt.y+1 >= wordLength) {
			directions.add(Direction.NORTH);
			//If East or West has been chosen add NorthWest or NorthEast
			if(directions.contains(Direction.WEST)) {
				directions.add(0,Direction.NORTHWEST);
			}
			if(directions.contains(Direction.EAST)) {
				directions.add(0,Direction.NORTHEAST);
			}
		}
		//Check South
		if(GRID_SIZE-pt.y >= wordLength ) {
			directions.add(0,Direction.SOUTH);
			//If East or West has been chosen add SouthWest or SouthEast
			if(directions.contains(Direction.WEST)) {
				directions.add(0,Direction.SOUTHWEST);
			}
			if(directions.contains(Direction.EAST)) {
				directions.add(0,Direction.SOUTHEAST);
			}
		}
		
		return directions;
	}
	//Checks if word can be placed at current location
	private boolean canPlace(Point pt,DefinedWord definedWord, Direction direction) {
		Point currPoint=new Point(pt);
		int dirX=direction.getMove().x;
		int dirY=direction.getMove().y;
	
		for(int index=0; index < definedWord.getWordLength(); index++)
		{
			Character currSpace=searchGrid[currPoint.x][currPoint.y];
			//If the current space is neither empty or set to the same character within the word
			if(!(currSpace==null || currSpace.charValue()==definedWord.getWord().charAt(index))) {
				return false;
			}
			currPoint.x+=dirX;
			currPoint.y+=dirY;
		}
		
		return true;
	}
	//Places word at given location, returns true if word was placed, false otherwise
	private boolean placeWord(Point pt,DefinedWord definedWord) {
		List<Direction> possibleDirections=getPossibleDirections(pt,definedWord.getWordLength());
		Point currPoint=new Point(pt);
		//Search through all the given possible directions and attempt to place word
		for(Direction direction : possibleDirections) {
			int dirX=direction.getMove().x;
			int dirY=direction.getMove().y;
			if(canPlace(pt,definedWord,direction)) {
				//Place the word
				for(int wordIndex=0; wordIndex < definedWord.getWordLength(); wordIndex++) {
					searchGrid[currPoint.x][currPoint.y]=definedWord.getWord().charAt(wordIndex);
					//Add directional values and move in a direction
					currPoint.x+=dirX;
					currPoint.y+=dirY;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean manuallyPlaceWord(DefinedWord definedWord) {
		//Go through grid and attempt manual placement
		System.out.println("Attempting manual placement!");
		for(int y=0; y < GRID_SIZE; y++) {
			for(int x=0; x <GRID_SIZE; x++) {
				if(placeWord(new Point(x,y),definedWord)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	//Get randomly chosen list of words
	private void setWords() {
		chosenWords=new ArrayList<DefinedWord>();
		for(int index=0; index<MAX_NUMBER_WORDS; ) {
			DefinedWord chosenWord=null;
			final int NUMWORDLIST=3;
			final int MAXWORDSFROMLIST=MAX_NUMBER_WORDS/NUMWORDLIST;
			
			//Evenly distribute randomly chosen word from wordlists
			switch(index/MAXWORDSFROMLIST) {
				case 0:
					chosenWord=WordList.getRandomLarge();
					break;
				case 1:
					chosenWord=WordList.getRandomMedium();
					break;
				case 2:
					chosenWord=WordList.getRandomSmall();
					break;
			}
			//Check for duplicates
			if(!chosenWords.contains(chosenWord)) {
				chosenWords.add(chosenWord);
				index++;
			}
		}
	}
	//Get the randomly chosen list of words
	public List<DefinedWord> getChosenWords() {
		return chosenWords;
	}
	//Get the grid the words have been placed in
	public Character[][] getSearchGrid() {
		return searchGrid;
	}
	//Output the contents of the search grid
	public void printSearchGrid() {
		for(int outer=0; outer < GRID_SIZE; outer++) {
			for(int inner=0; inner < GRID_SIZE; inner++) {
				if(searchGrid[inner][outer]==null) {
					System.out.print("[ ]");
				}
				else {
					System.out.print("["+ searchGrid[inner][outer] +"]");
				}
			}
			System.out.println();
		}
	}
}
