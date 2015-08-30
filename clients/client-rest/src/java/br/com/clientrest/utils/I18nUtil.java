package br.com.clientrest.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18nUtil {

	/**
	 * Retorna o classLoader
	 * @param defaultObject
	 * @return
	 */
	protected static ClassLoader getCurrentClassLoader(Object defaultObject){

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}

		return loader;
	}
	
	/**
	 * Retorna o valor correspondente ao message resource
	 * @param bundleName
	 * @param key
	 * @param params
	 * @param locale
	 * @return
	 */
	public static String getMessageResourceString(
			String bundleName, 
			String key, 
			Object params[], 
			Locale locale){

		String text = null;

		ResourceBundle bundle = 
			ResourceBundle.getBundle(bundleName, locale, 
					getCurrentClassLoader(params));

		try{
			text = bundle.getString(key);
		} catch(MissingResourceException e){
			text = "?? key " + key + " not found ??";
		}

		if(params != null){
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}	
}
