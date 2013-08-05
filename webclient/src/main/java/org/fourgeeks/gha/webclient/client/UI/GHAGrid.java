package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import com.smartgwt.client.widgets.grid.ListGrid;

public abstract class GHAGrid<E> extends ListGrid {

	public abstract E getSelectedEntity();

	public abstract void load(List<E> list);
}