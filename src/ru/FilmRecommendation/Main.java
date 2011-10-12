package ru.FilmRecommendation;


import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ru.FilmRecommendation.wrapper.ItunesWrapper;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String argv[]) {
		log.info("Programm starts");
		ApplicationContext ctx =
				new FileSystemXmlApplicationContext("resources/spring.xml");

		ItunesWrapper wrapper = (ItunesWrapper) ctx.getBean("wrapper");
		log.info("Programm finished");
	}
}


