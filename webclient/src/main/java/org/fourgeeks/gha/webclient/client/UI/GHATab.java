package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.layout.VLayout;

public abstract class GHATab extends VLayout {
	public GHATab() {
		setWidth100();
	}

	public abstract void onClose();

	public abstract String getId();

	public abstract GHATabHeader getHeader();
}
