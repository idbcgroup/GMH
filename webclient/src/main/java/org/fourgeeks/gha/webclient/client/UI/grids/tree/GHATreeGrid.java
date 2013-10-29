package org.fourgeeks.gha.webclient.client.UI.grids.tree;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * @author jfuentes
 * 
 * @param <T>
 *            The type of entity for the grid
 */
public class GHATreeGrid<T> extends TreeGrid implements ResizeHandler {

	public GHATreeGrid() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getSubtabGridSize(30));
		setAlternateRecordStyles(false);
		setMinFieldWidth(100);
	}
	/**
	 * @param entities
	 */
	public void setData(GHATreeGridNode<T>[] entities) {
		setData(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public GHATreeGridNode<T> getSelectedRecord() {
		TreeNode selectedRecord = super.getSelectedRecord();
		return (GHATreeGridNode<T>) selectedRecord;
	}

	/**
	 * @return the entity selected
	 */
	public T getSelectedEntity() {
		@SuppressWarnings("unchecked")
		GHATreeGridNode<T> selectedRecord = (GHATreeGridNode<T>) super
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