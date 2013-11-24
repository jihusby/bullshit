package no.husby.article;

import java.util.List;

import no.husby.article.tools.WebArticleManager;
import no.husby.article.tools.internal.WordEntry;

public class Application {


	public static void main(String[] args) {

		final int WORD_LENGTH = 5;
		final int MIN_NUM_OF_OCCURENCES = 10;
		final String DOMAIN = "kjendis.no";
		
		WebArticleManager manager = new WebArticleManager(DOMAIN, WORD_LENGTH);
		List<WordEntry> words = manager.getSortedWordEntries();
		
		printWordList(MIN_NUM_OF_OCCURENCES, words);
	}

	private static void printWordList(final int MIN_NUM_OF_OCCURENCES, List<WordEntry> words) {
		int index = 0;
		for(WordEntry e : words) { 
			if(e.getEntries() >= MIN_NUM_OF_OCCURENCES) {
				System.out.println(++index + ". " + e.getWord() + " (" + e.getEntries() + " entries)");
			}
		}
	}


}
