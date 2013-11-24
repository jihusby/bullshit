package no.husby.article.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.husby.article.tools.internal.LanguageUtils;
import no.husby.article.tools.internal.WordEntry;

public class WordCounter {

	private LanguageUtils languageUtils = new LanguageUtils();

	public WordCounter(String text, int minWordLength) {
		languageUtils.setSortedWordEntryList(getSortedWordEntryList(createWordMapFromText(text, minWordLength)));
	}
	
	public List<WordEntry> getSortedWordEntryList() {
		return languageUtils.getSortedWordEntryList();
	}

	private Map<String, Integer> createWordMapFromText(String string, int minLength) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] words = string.split(" ");
		for(String word : words) {
			String cleanWord = getCleanString(word);
			if(!LanguageUtils.isInvalid(cleanWord, minLength)) {
				if(map.containsKey(cleanWord)) {
					map.put(cleanWord, map.get(cleanWord)+1);
				}else{
					map.put(cleanWord, 1);
				}
			}
		}
		return map;
	}

	private String getCleanString(String input) {
		return input.replaceAll("[^a-zA-ZæøåÆØÅ0-9]+","").toLowerCase();
	}

	private List<WordEntry> getSortedWordEntryList(Map<String, Integer> map) {

	    // Convert map to list of <String,Integer> entries
	    List<Map.Entry<String, Integer>> list = 
	        new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

	    // Sort list by integer values
	    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	            // compare o2 to o1, instead of o1 to o2, to get descending freq. order
	            return (o2.getValue()).compareTo(o1.getValue());
	        }
	    });

	    // Populate the result into a list
	    List<WordEntry> result = new ArrayList<WordEntry>();
	    for (Map.Entry<String, Integer> entry : list) {
	        result.add(new WordEntry(entry.getKey(), entry.getValue()));
	    }
	    return result;
	}	
	
}
