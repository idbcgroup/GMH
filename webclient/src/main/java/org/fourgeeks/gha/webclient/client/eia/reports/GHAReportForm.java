package org.fourgeeks.gha.webclient.client.eia.reports;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Abstract class to represent a Report Form
 * 
 * @author naramirez
 * 
 */
public abstract class GHAReportForm extends VLayout {

	/**
	 * @return the URL for the report
	 */
	public abstract String getReportURI();

	/**
	 * clean the item's values from the form
	 */
	public abstract void cleanItems();

	/**
	 * Append query param into a url
	 * 
	 * @param url
	 *            the URL String for what the query param is going be append
	 * @param param
	 *            the param of the query param
	 * @param value
	 *            the value of the query param
	 * @return the URL String with the appended query param
	 */
	protected String buildUrl(String url, String param, String value) {
		char lastchar = url.charAt(url.length() - 1);
		if (lastchar == '?')
			url += param + "=" + value;
		else
			url += "&" + param + "=" + value;

		return url;
	}

	/**
	 * Transform a String Array into a String of comma-separated values
	 * 
	 * @param valsParam
	 *            the String Array to transform
	 * @return The comma-separated String
	 */
	protected String toCommaRepresent(String[] valsParam) {
		String commaRepresentation = "";
		for (int i = 0; i < valsParam.length; i++) {
			commaRepresentation += valsParam[i];
			if (i < valsParam.length - 1)
				commaRepresentation += ",";
		}
		return commaRepresentation;
	}
}
