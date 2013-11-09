package org.fourgeeks.gha.webclient.client.material;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class MaterialResultSet extends GHAResultSet<Material> implements
		MaterialSelectionProducer {

	private List<MaterialSelectionListener> listeners;
	private MaterialGrid grid;

	{
		listeners = new LinkedList<MaterialSelectionListener>();
		grid = new MaterialGrid();
	}

	public MaterialResultSet() {
		super(GHAStrings.get("search-results"));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectMaterial();
					}
				})));
		addMember(gridPanel);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);
	}

	@Override
	public void clean() {
		grid.setData(new MaterialRecord[] {});
		showResultsSize(null, true);
	}

	@Override
	public void notifyMaterial(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);
	}

	private void notifySelectMaterial() {
		Material selectedEntity = grid.getSelectedEntity();
		if (selectedEntity == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyMaterial(selectedEntity);
		grid.removeSelectedData();

	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);
	}

	@Override
	public void setRecords(List<Material> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaterial(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		MaterialRecord[] array = MaterialUtil.toGridRecords(records).toArray(
				new MaterialRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);

	}

}
