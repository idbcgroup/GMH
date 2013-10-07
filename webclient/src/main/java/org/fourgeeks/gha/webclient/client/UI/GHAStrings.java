package org.fourgeeks.gha.webclient.client.UI;

import java.util.MissingResourceException;

import com.google.gwt.i18n.client.Dictionary;

/**
 * @author alacret
 * 
 */
public class GHAStrings {
	private GHAStrings() {
		throw new UnsupportedOperationException("");
	}

	private static Dictionary theme = Dictionary.getDictionary("lang");

	/**
	 * @param key
	 * @return the string representation in the current lang
	 */
	public static String get(String key) {
		String string;
		try {
			string = theme.get(key);
		} catch (MissingResourceException e) {
			return "String not found in the current locale: " + key;
		}
		return string;
	}
}
