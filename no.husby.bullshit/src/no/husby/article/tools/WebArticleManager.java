package no.husby.article.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import no.husby.article.tools.internal.WordEntry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebArticleManager {
	
	private String domain;
	private final String BASE_ADDRESS = "http://www.";
	private String domainArticle;
	private Elements domainLinks;
	private List<WordEntry> sortedWordEntries;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomainArticle() {
		return domainArticle;
	}

	public Elements getDomainLinks() {
		return domainLinks;
	}
	
	public List<WordEntry> getSortedWordEntries() {
		if(sortedWordEntries==null) {
			throw new RuntimeException("The list of sorted word entries is not defined");
		}
		return sortedWordEntries;
	}

	public WebArticleManager(String domain, int wordLength) {
		this.domain = domain;
		domainLinks = getAllDomainLinks();
		domainArticle = getArticleFromURL();
		sortedWordEntries = new WordCounter(domainArticle, wordLength).getSortedWordEntryList();
	}

	private Elements getAllDomainLinks() {
		Document doc = null;
		try {
			doc = Jsoup.parse(new URL(BASE_ADDRESS + domain), 2000);
		} catch (Exception e) {
			throw new RuntimeException("Error in parsing link");
		}
		return doc.select("div> a");
	}

	private String getArticleFromURL() {
	    String text = "";

		for (Element link : domainLinks) {
			String href = link.attr("href");
			if(href.indexOf(domain)>-1) {
				text += getArticle(href);
			}
		}
	    return text;
	}

	private String getArticle(String href) {
		String text = "";
		BufferedReader in = null;
	    try {
	    	in = new BufferedReader(new InputStreamReader(new URL(href).openStream()));
	    	text = parseText(in);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    } finally {
	    	if (in != null) {
	    		try {
	    			in.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    }
		return text;
	}

	public String parseText(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ( (line=br.readLine()) != null) {
			sb.append(line);
		}
		return Jsoup.parse(sb.toString()).text();
	}	
	
}
