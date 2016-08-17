package wordsearch.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DefinedWord implements Comparable<Object>{
	private String word;
	private String definition;
	
	public DefinedWord(String word,String definition) {
		this.word=word;
		this.definition=definition;
	}
	
	public String getWord() {
		return word;
	}
	
	public String definition() {
		return definition;
	}
	
	public int getWordLength() {
		return word.length();
	}
	
	public int getDefinitionLength() {
		return definition.length();
	}
	@Override
	public boolean equals(Object obj) {
		
		return this.getWord().equals(obj.toString());
		/*if(obj instanceof DefinedWord) {
			return this.getWord().equals(((DefinedWord)obj).getWord());
		}
		else if(obj instanceof String) {
			return this.getWord().equals(obj.toString());
		}
		else {
			return false;
		}*/
	}
	
	@Override
	public String toString() {
		return word;
	}

	@Override
	public int compareTo(Object obj) {
		return this.getWord().compareTo(obj.toString());
	}

}
