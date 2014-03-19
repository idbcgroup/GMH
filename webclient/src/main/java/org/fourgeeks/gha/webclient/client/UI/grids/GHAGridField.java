package org.fourgeeks.gha.webclient.client.UI.grids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

public class GHAGridField extends ListGridField {
	private boolean hidden;

	/**
	 * @param name
	 * @param title
	 */
	public GHAGridField(String name, String title) {
		super(name, title);
		setAlign(Alignment.CENTER);
		setHidden(false);
	}

	/**
	 * @param name
	 * @param title
	 * @param hidden
	 */
	public GHAGridField(String name, String title, boolean hidden) {
		super(name, title);
		setAlign(Alignment.CENTER);
		setHidden(hidden);
	}

	/**
	 * @return true if the {@link ListGridField} is hidden, false in other case
	 */
	public boolean getIsHidden() {
		return this.hidden;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartgwt.client.widgets.grid.ListGridField#setHidden(boolean)
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		super.setHidden(hidden);
	}
}