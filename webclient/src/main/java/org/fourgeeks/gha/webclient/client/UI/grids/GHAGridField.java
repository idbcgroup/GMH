package org.fourgeeks.gha.webclient.client.UI.grids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

public class GHAGridField extends ListGridField {

	public GHAGridField(String name, String title) {
		super(name, title);
		setAlign(Alignment.CENTER);
	}

}
