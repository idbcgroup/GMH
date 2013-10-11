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
}
