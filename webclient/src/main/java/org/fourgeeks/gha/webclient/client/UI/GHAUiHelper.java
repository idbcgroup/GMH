package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HTML;

public class GHAUiHelper {

	public static HTML verticalGraySeparator() {
		HTML separator = new HTML();
		separator.setStylePrimaryName("vertical-gray-separator");
		separator.setHeight("10px");
		return separator;
	}

}
