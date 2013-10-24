package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

public class GHAUserNameItem extends GHATextItem {

	public GHAUserNameItem(int width, boolean active) {
		super(GHAStrings.get("user"), width);
		setDisabled(!active);
		setLength(20);
		setMask("AAAAAAAAAAAAAAAAAAAA");
	}
}