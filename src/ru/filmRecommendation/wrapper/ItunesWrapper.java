package ru.filmRecommendation.wrapper;

import java.net.URL;
import java.io.*;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import ru.filmRecommendation.core.Wrapper;
import ru.filmRecommendation.exception.WebHarvestException;

public class ItunesWrapper extends Wrapper {
	private static Logger log = Logger.getLogger(ItunesWrapper.class);
	private String configPath = "resources/webharvest/downloader/category.xml";
	private String tempPath = "data";
	private String linksFilePath = tempPath + "/" + "allLinks.txt";
	private String workingDir = System.getProperty("user.dir");

	public ItunesWrapper() {
	}

	private void wrapLinks(int maxloops) throws WebHarvestException {
		try {
			ScraperConfiguration config =
					new ScraperConfiguration(configPath);
			Scraper scraper = new Scraper(config, workingDir);
			scraper.addVariableToContext("path", linksFilePath);
			scraper.addVariableToContext("maxloops", maxloops);
			log.info("Start. Wrapping links");
			scraper.execute();
			log.info("End.Wrapping links");
		} catch (Exception exp) {
			throw new WebHarvestException();
		}
	}

	private void downloadLinks() throws Exception {
		log.info("Start. Downloading links");
		int i = 1;
		Scanner sc = new Scanner(new File(linksFilePath));
		while (sc.hasNext()) {
			String url = sc.next();
			String filePath = tempPath + "/" + i + ".html";
			downloadOneLink(url, filePath );
			++i;
		}
		log.info("End. Downloading links");
	}

	private static void downloadOneLink(String urlStr, String filePath) throws Exception{
		log.debug("Downloading " + urlStr + " to " + filePath);
		URL url = new URL(urlStr);
		InputStream response = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(response));
		FileWriter fw = new FileWriter(new File(filePath));
		String line = reader.readLine();
		while (line != null) {
			fw.write(line);
			line = reader.readLine();
		}
		reader.close();
		fw.close();
	}

	public void wrap(){
		try {
			//wrapLinks(1);
			//downloadLinks();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		ItunesWrapper wrapper = new ItunesWrapper();
		wrapper.wrap();
	}
}
