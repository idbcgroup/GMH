package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class GHAUiHelper {

	public static final int INNER_TOP_SECTION_HEIGHT = 120	;
	public static final int V_SEPARATOR_HEIGHT = 10;

	public static HTML verticalGraySeparator(String height) {
		HTML separator = new HTML();
		separator.setStylePrimaryName("vertical-gray-separator");
		separator.setHeight(height);
		return separator;
	}

	public static int calculateTabHeight() {
		int rootPanelHeight = Window.getClientHeight();
		int topPartHeight = RootPanel.get("top-bar").getOffsetHeight()
				+ RootPanel.get("header-bar").getOffsetHeight()
				+ RootPanel.get("menu-bar").getOffsetHeight();
		int footerHeight = RootPanel.get("footer-bar").getOffsetHeight();

		return rootPanelHeight - topPartHeight - footerHeight;
	}

	public static int getBottomSectionHeight() {
		int biggerTabHeight = calculateTabHeight();
		int innerTopSection = INNER_TOP_SECTION_HEIGHT + V_SEPARATOR_HEIGHT + 34;

		return biggerTabHeight - innerTopSection;
	}

}
