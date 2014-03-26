package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
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
public class MaterialResultSet extends GHAResultSet<Material> implements
		MaterialSelectionProducer, MaterialListSelectionProducer {

	private List<MaterialSelectionListener> listeners;
	private List<MaterialListSelectionListener> listenersList;
	private MaterialGrid grid;
	private ResultSetContainerType containerType;

	{
		listeners = new ArrayList<MaterialSelectionListener>();
		listenersList = new ArrayList<MaterialListSelectionListener>();
	}

	/**
	 * @param container
	 */
	public MaterialResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;

		grid = new MaterialGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedMaterial();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (grid.getSelectedRecords().length > 1)
							notifySelectedMaterials();
						else
							notifySelectedMaterial();
					}
				})));
		// if (containerType == ResultSetContainerType.SEARCH_FORM) {
		// setHeight(getHeight() - 42);
		// }
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
			GHAAlertManager.alert("record-not-selected");
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

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	@Override
	public void addMaterialListSelectionListener(
			MaterialListSelectionListener materialListSelectionListener) {
		listenersList.add(materialListSelectionListener);
	}

	@Override
	public void removeMaterialListSelectionListener(
			MaterialListSelectionListener materialListSelectionListener) {
		listenersList.remove(materialListSelectionListener);
	}

	@Override
	public void notifyMaterialList(List<Material> materials) {
		for (MaterialListSelectionListener listener : listenersList)
			listener.select(materials);
	}

	private void notifySelectedMaterials() {
		notifyMaterialList(grid.getSelectedEntities());
		hide();
		grid.removeSelectedData();
	}
}
