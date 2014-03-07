/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandResultSet extends GHAResultSet<MaterialBrand> implements
		MaterialBrandSelectionProducer {
	private List<MaterialBrandSelectionListener> listeners;
	private final MaterialBrandGrid grid;
	private final ResultSetContainerType containerType;

	{
		listeners = new ArrayList<MaterialBrandSelectionListener>();
	}

	/**
	 * @param label
	 */
	public MaterialBrandResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;

		grid = new MaterialBrandGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedMaterialBrand();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedMaterialBrand();

					}
				})));

		addMember(gridPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #addMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void addMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		listeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet#clean()
	 */
	@Override
	public void clean() {
		grid.setData(new MaterialBrandRecord[] {});
		showResultsSize(null, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #notifyMaterialBrand(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public void notifyMaterialBrand(MaterialBrand materialBrand) {
		for (MaterialBrandSelectionListener listener : listeners)
			listener.select(materialBrand);
	}

	private void notifySelectedMaterialBrand() {
		GHAGridRecord<MaterialBrand> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}

		notifyMaterialBrand(selectedRecord.toEntity());
		hide();
		grid.removeSelectedData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.logical.shared.ResizeHandler#onResize(com.google
	 * .gwt.event.logical.shared.ResizeEvent)
	 */
	@Override
	public void onResize(ResizeEvent arg0) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #removeMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void removeMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet#setRecords
	 * (java.util.List, boolean)
	 */
	@Override
	public void setRecords(List<MaterialBrand> records,
			boolean notifyIfOnlyOneResult) {
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaterialBrand(records.get(0));
			hide();
			return;
		}

		showResultsSize(records, false);
		ListGridRecord array[] = MaterialBrandUtil.toGridRecords(records)
				.toArray(new MaterialBrandRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

}
