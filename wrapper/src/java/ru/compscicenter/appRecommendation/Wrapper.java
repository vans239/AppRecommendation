package ru.compscicenter.appRecommendation;

import org.apache.log4j.Logger;

public abstract class Wrapper {
	protected final static Logger log = Logger.getLogger(Wrapper.class);
	abstract public void wrap();
}
