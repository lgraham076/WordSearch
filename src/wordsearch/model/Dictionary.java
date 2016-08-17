package wordsearch.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Dictionary")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dictionary {
	private List<DefinedWord> definedWords;
}
