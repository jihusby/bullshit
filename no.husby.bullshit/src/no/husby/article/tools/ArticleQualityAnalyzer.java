package no.husby.article.tools;

public class ArticleQualityAnalyzer {

	private double badTVLevel = 0;
	private String[] badWords;
	
	public ArticleQualityAnalyzer(String inputText) {
		badWords = createBadWords();
		buildBadTVLevelFromText(inputText);
	}

	public double getBadTVLevel() {
		return badTVLevel;
	}

	private void buildBadTVLevelFromText(String inputText) {
		String[] words = inputText.split(" ");
		for(String word : words) {
			badTVLevel += getAdjustedLevel(word);
		}
	}

	private double getAdjustedLevel(String word) {
		double level = 0;
		if(isBadWord(word)) {
			level++;
		}
		return level;
	}

	private boolean isBadWord(String word) {
		return (word.indexOf("farmen") != -1) || (word.indexOf("paradisehotel") != -1);
	}

	private String[] createBadWords() {
		badWords = new String[3];
		badWords[0] = "farmen";
		badWords[1] = "paradisehotel";
		badWords[2] = "topmodel";
		return badWords;
	}

}
