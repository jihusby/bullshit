package no.husby.article.tools.internal;

import java.util.List;

public class LanguageUtils {
	private List<WordEntry> sortedWordEntryList;

	public LanguageUtils() {
	}
	
	public List<WordEntry> getSortedWordEntryList() {
		return sortedWordEntryList;
	}

	public void setSortedWordEntryList(List<WordEntry> sortedWordEntryList) {
		this.sortedWordEntryList = sortedWordEntryList;
	}
	
	public static boolean isInvalid(String word, int minLength) {
		String[] references = getReferenceWords();
		for(String reference : references) {
			if(reference.equals(word)) return true;
			if(word.length()<minLength) return true;
		}
		return false;
	}
	
	private static String[] getReferenceWords() {
		String[] ref = {"å", "og", "eller", "men", "for", "les", "er", "ja", "nei", "i", "på", "de", "han", "hun", "det"};
		return ref;
	}
	
}