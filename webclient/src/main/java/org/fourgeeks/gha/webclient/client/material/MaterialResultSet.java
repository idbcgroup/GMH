package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

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
public class MaterialResultSet extends GHAResultSet<Material> implements
		MaterialSelectionProducer {

	private List<MaterialSelectionListener> listeners;
	private MaterialGrid grid;

	{
		listeners = new ArrayList<MaterialSelectionListener>();
		grid = new MaterialGrid();
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedMaterial();
			}
		});
	}

	public MaterialResultSet() {
		super(GHAStrings.get("search-results"));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedMaterial();

					}
				}), GHAUiHelper.verticalGraySeparator("2px"),
				new GHADeleteButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO delete

					}
				})));
		addMember(gridPanel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #addMaterialSelectionListener
	 * (org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener)
	 */
	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet#clean()
	 */
	@Override
	public void clean() {
		grid.setData(new MaterialRecord[] {});
		showResultsSize(null, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #notifyMaterial(org.fourgeeks.gha.domain.glm.Material)
	 */
	@Override
	public void notifyMaterial(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);

	}

	private void notifySelectedMaterial() {
		GHAGridRecord<Material> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert("record-not-selected");
			return;
		}

		notifyMaterial(selectedRecord.toEntity());
		hide();
		grid.removeSelectedData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #removeMaterialSelectionListener
	 * (org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener)
	 */
	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet#setRecords
	 * (java.util.List, boolean)
	 */
	@Override
	public void setRecords(List<Material> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaterial(records.get(0));
			hide();
			return;
		}

		showResultsSize(records, false);
		ListGridRecord[] array = MaterialUtil.toGridRecords(records).toArray(
				new MaterialRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);

	}

}
