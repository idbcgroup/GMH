package org.fourgeeks.gha.webclient.client.UI.grids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

public class GHAGridField extends ListGridField {
	private boolean hidden;

	public GHAGridField(String name, String title) {
		super(name, title);
		setAlign(Alignment.CENTER);
		hidden = false;
	}

	@Override
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		super.setHidden(hidden);
	}

	public boolean getIsHidden() {
		return this.hidden;
	}
}