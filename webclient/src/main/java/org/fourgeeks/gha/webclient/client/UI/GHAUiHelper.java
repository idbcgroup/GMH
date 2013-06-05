package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HTML;

public class GHAUiHelper {

	public static HTML verticalGraySeparator(String height) {
		HTML separator = new HTML();
		separator.setStylePrimaryName("vertical-gray-separator");
		separator.setHeight(height);
		return separator;
	}

}
