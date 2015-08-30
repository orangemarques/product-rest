package br.com.clientrest.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PresentationProperties {

	private static Properties properties;
	
	static {		
		Resource resource = new ClassPathResource("presentation.properties");
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
		}
		 catch (IOException e) {					
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}	
}
