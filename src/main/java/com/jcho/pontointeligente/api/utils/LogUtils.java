package com.jcho.pontointeligente.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtils<T> {
	
	private T t;
	
	public void info(String msg) {
		Logger log = LoggerFactory.getLogger(t.getClass().getName());
		log.info(msg);
	}

}
