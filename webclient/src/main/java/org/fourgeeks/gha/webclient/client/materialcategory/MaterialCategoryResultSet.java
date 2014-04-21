package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 *
 */
public class MaterialCategoryResultSet extends GHAResultSet<MaterialCategory>
		implements MaterialCategorySelectionProducer {
	private List<MaterialCategorySelectionListener> listeners;
	private MaterialCategoryGrid grid;
	private ResultSetContainerType containerType;

	{
		listeners = new ArrayList<MaterialCategorySelectionListener>();
	}

	public MaterialCategoryResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		
		grid = new MaterialCategoryGrid(){
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
		
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid,
				GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectMaterialCategory();
					}
				})));
		
//		if (containerType == ResultSetContainerType.SEARCH_FORM) {
//			setHeight(getHeight() - 42);
//		}
		
		addMember(gridPanel);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);
	}

	@Override
	public void clean() {
		grid.setData(new MaterialCategoryRecord[] {});
		showResultsSize(null, true);
	}

	@Override
	public void notifyMaterialCategory(MaterialCategory materialCategory) {
		for (MaterialCategorySelectionListener listener : listeners)
			listener.select(materialCategory);
	}

	/**
	 * notify selected {@link MaterialCategory} from the grid
	 */
	private void notifySelectMaterialCategory() {
		MaterialCategory selectedEntity = grid.getSelectedEntity();
		if (selectedEntity == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}
		notifyMaterialCategory(selectedEntity);
		grid.removeSelectedData();
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);

	}

	@Override
	public void setRecords(List<MaterialCategory> records,
			boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyMaterialCategory(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		MaterialCategoryRecord[] array = MaterialCategoryUtil.toGridRecords(
				records).toArray(new MaterialCategoryRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}
}
