package no.husby.article.tools.test;

import java.util.List;

import no.husby.article.tools.WordCounter;
import no.husby.article.tools.internal.WordEntry;

import org.junit.Assert;
import org.junit.Test;

public class WordCounterTest {

	@Test
	public void testSortedWordListIsReturned() {
		String input = "Word1 Word2 Word3 Word2 Word3 Word1 Word1 Word1";
		assertListIsCreated(input, 3);
	}

	@Test
	public void testSpecialCharsAreReplaced() {
		String input = "Wo.rd1 Word2 Wo§§rd3 Word2 Word3 Word1 Word1 Word1";
		assertListIsCreated(input, 3);
	}
	
	private void assertListIsCreated(String input, int expected) {
		List<WordEntry> wordList = new WordCounter(input, 4).getSortedWordEntryList();
		printList(wordList, input);
		Assert.assertTrue(expected == wordList.size());
	}
	
	private void printList(List<WordEntry> wordList, String text) {
		System.out.println("\n\nTesting input: " + text);
		for(int i=0; i<wordList.size(); i++) {
			System.out.println(wordList.get(i).getEntries() + ": " + wordList.get(i).getWord());
		}
	}

}
