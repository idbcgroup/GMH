package org.fourgeeks.gha.ejb;

import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author alacret
 * 
 */
final public class RuntimeParameters {

	private RuntimeParameters() {
		throw new UnsupportedOperationException(
				"This class is not ment to be instantiated");
	}

	private static LanguageEnum lang = LanguageEnum.ES;

	/**
	 * @return the lang
	 */
	public static LanguageEnum getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public static void setLang(LanguageEnum lang) {
		RuntimeParameters.lang = lang;
	}
}
