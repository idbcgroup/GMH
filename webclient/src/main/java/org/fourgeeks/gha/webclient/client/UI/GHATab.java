package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.tab.Tab;

public abstract class GHATab extends Tab {

	public abstract void onClose();

	public abstract String getId();

	public abstract GHATabHeader getHeader();
}
