package br.com.clientrest.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigProperties {

	private static Properties properties;
	
	static {		
		Resource resource = new ClassPathResource("config.properties");
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

	public static List<String> getProperty(String key, String separator) {
		String retorno = properties.getProperty(key);
		return ((retorno != null) && (separator != null && separator.length() > 0)) ? Arrays.asList(retorno.split(separator)) : new ArrayList<String>();
	}
	
	public static String getMensagem(String key, Locale loc) {
		return I18nUtil.getMessageResourceString(getProperty("app.resourceBundle"), key, null, loc);
	}
	
	public static String getMensagem(String key, Object params[], Locale loc) {
		return I18nUtil.getMessageResourceString(getProperty("app.resourceBundle"), key, params, loc);
	}
}
