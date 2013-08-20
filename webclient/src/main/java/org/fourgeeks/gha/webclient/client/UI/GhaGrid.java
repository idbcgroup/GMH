package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author alacret
 * 
 * @param <T>
 *            The type of entity for the grid
 */
public class GhaGrid<T> extends ListGrid implements ResizeHandler {

	public GhaGrid() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getSubtabGridSize(30));
		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		// setShowFilterEditor(true);

	}
	/**
	 * @param entities
	 */
	public void setData(GHAGridRecord<T>[] entities) {
		setData(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public GHAGridRecord<T> getSelectedRecord() {
		ListGridRecord selectedRecord = super.getSelectedRecord();
		return (GHAGridRecord<T>) selectedRecord;
	}

	/**
	 * @return the entity selected
	 */
	public T getSelectedEntity() {
		@SuppressWarnings("unchecked")
		GHAGridRecord<T> selectedRecord = (GHAGridRecord<T>) super
				.getSelectedRecord();
		if (selectedRecord == null)
			return null;
		return selectedRecord.toEntity();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getSubtabGridSize(30));
	}
}