package ru.FilmRecommendation;

import java.util.*;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String argv[]) {
		log.info("Programm starts");
		ApplicationContext ctx =
				new FileSystemXmlApplicationContext("resources/spring.xml");

		Wrapper wrapper = (Wrapper) ctx.getBean("wrapper");

		log.info("Programm finished");
	}
}

