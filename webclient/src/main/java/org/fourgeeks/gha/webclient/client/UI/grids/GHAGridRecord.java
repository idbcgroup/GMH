package org.fourgeeks.gha.webclient.client.UI.grids;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public abstract class GHAGridRecord<T> extends ListGridRecord {

	public abstract T toEntity();
}