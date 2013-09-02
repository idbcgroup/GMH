package org.fourgeeks.gha.webclient.client.UI.grids.tree;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class GHATreeGridField extends TreeGridField {

	public GHATreeGridField(String name, String title) {
		super(name, title);
		setAlign(Alignment.CENTER);
	}

}
