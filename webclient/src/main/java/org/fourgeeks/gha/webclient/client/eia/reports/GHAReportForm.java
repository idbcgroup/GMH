package org.fourgeeks.gha.webclient.client.eia.reports;

import com.smartgwt.client.widgets.layout.VLayout;

public abstract class GHAReportForm extends VLayout {
	public abstract String getReportURI();

	public abstract void cleanItems();
}
