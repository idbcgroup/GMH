package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class GHAGridRecord extends ListGridRecord {

	public void setId(long id) {
		setAttribute("id", id);
	}

	public long getId() {
		return getAttributeAsLong("id");
	}
}