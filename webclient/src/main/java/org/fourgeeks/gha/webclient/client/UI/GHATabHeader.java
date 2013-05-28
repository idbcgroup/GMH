package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.widgets.layout.HLayout;

public class GHATabHeader extends HLayout {

	public GHATabHeader(String title) {
		addChild(new HTML(title));
	}

}
