package no.husby.article.tools.internal;

public class WordEntry {
	private String word;
	private int entries;
	private int quality;
	
	public WordEntry(String word) {
		this.word = word;
		this.entries = 1;
		this.quality = 0;
	}
	
	public WordEntry(String word, Integer entries) {
		this.word = word;
		this.entries = entries;
		this.quality = 0;
	}

	public int getEntries() {
		return entries;
	}

	public int getQuality() {
		return quality;
	}
	
	public String getWord() {
		return word;
	}
	
}
