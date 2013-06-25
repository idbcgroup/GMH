package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.ImgButton;

public class GHAButton extends ImgButton {
	public GHAButton(String src) {
		setSrc(src);
		// setShowRollOver(false);
		setSize("20px", "20px");
		setShowDown(false);
	}
}
