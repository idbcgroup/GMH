/**
 * 
 */
package org.fourgeeks.gha.webclient.client.viewpermission;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class ViewPermissionRecord extends GHAGridRecord<ViewFunction> {
	private ViewFunction function;

	/**
	 * @param function
	 */
	public ViewPermissionRecord(ViewFunction function) {
		this.function = function;
		// TODO
		setAttribute("view", function.getView().getName());
		setAttribute("permission", function.getFunction().getName());
		setAttribute("active", false);
	}

	/**
	 * @param active
	 */
	public void setActive(boolean active) {
		setAttribute("active", active);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public ViewFunction toEntity() {
		return this.function;
	}

}
