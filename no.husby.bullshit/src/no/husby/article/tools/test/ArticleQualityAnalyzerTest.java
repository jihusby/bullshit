package no.husby.article.tools.test;

import no.husby.article.tools.ArticleQualityAnalyzer;

import org.junit.Assert;
import org.junit.Test;

public class ArticleQualityAnalyzerTest {

	private String inputText;

	@Test
	public void testDetectorReturnsZero() {
		inputText = "Dette er en bra setning";
		ArticleQualityAnalyzer detector = new ArticleQualityAnalyzer(inputText);
		Assert.assertTrue(0.0 == detector.getBadTVLevel());
	}

	@Test
	public void testDetectorReturnsOne() {
		inputText = "Dette er en setning som inneholder ord som farmen";
		ArticleQualityAnalyzer detector = new ArticleQualityAnalyzer(inputText);
		Assert.assertEquals(1, detector.getBadTVLevel(), 0);
	}

	@Test
	public void testDetectorReturnsTwo() {
		inputText = "Dette er en setning som inneholder både paradisehotel og farmen";
		ArticleQualityAnalyzer detector = new ArticleQualityAnalyzer(inputText);
		Assert.assertEquals(2, detector.getBadTVLevel(), 0);
	}

	@Test
	public void testDetectorReturnsThree() {
		inputText = "Dette er en setning som inneholder både paradisehotel, farmen og topmodel";
		ArticleQualityAnalyzer detector = new ArticleQualityAnalyzer(inputText);
		Assert.assertEquals(2, detector.getBadTVLevel(), 0);
	}
	
}
