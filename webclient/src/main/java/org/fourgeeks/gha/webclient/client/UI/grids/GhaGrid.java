package org.fourgeeks.gha.webclient.client.UI.grids;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author alacret
 * 
 * @param <T>
 *            The type of entity for the grid
 */
public class GhaGrid<T> extends ListGrid implements ResizeHandler {

	/**
	 * 
	 */
	public GhaGrid() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getSubtabGridSize(30));
		setAlternateRecordStyles(false);
		setMinFieldWidth(100);
		setOverflow(Overflow.AUTO);
		setEmptyMessage(GHAStrings.get("no-results-to-show"));
	}

	/**
	 * @param entities
	 */
	public void setData(GHAGridRecord<T>[] entities) {
		super.setData(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public GHAGridRecord<T> getSelectedRecord() {
		final ListGridRecord selectedRecord = super.getSelectedRecord();
		return (GHAGridRecord<T>) selectedRecord;
	}

	/**
	 * @return the selected entity
	 */
	@SuppressWarnings("unchecked")
	public T getSelectedEntity() {
		final GHAGridRecord<T> selectedRecord = (GHAGridRecord<T>) super
				.getSelectedRecord();

		if (selectedRecord == null) {
			return null;
		}
		return selectedRecord.toEntity();
	}

	/**
	 * @return the selected entities
	 */
	@SuppressWarnings("unchecked")
	public List<T> getSelectedEntities() {
		final GHAGridRecord<T> selectedRecord = (GHAGridRecord<T>) super
				.getSelectedRecord();

		if (selectedRecord == null)
			return null;

		final ArrayList<T> lista = new ArrayList<T>();
		final ListGridRecord[] selectedRecords = super.getSelectedRecords();

		for (final ListGridRecord record : selectedRecords) {
			final GHAGridRecord<T> ghaRecord = (GHAGridRecord<T>) record;
			lista.add(ghaRecord.toEntity());
		}

		return lista;
	}

	/**
	 * @return the entities displayed in the grid
	 */
	@SuppressWarnings("unchecked")
	public List<T> getEntities() {
		final ListGridRecord[] records = super.getRecords();

		if (records == null)
			return null;

		final ArrayList<T> list = new ArrayList<T>();
		for (final ListGridRecord record : records) {
			final GHAGridRecord<T> ghaRecord = (GHAGridRecord<T>) record;
			list.add(ghaRecord.toEntity());
		}

		return list;
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getSubtabGridSize(30));
	}
}