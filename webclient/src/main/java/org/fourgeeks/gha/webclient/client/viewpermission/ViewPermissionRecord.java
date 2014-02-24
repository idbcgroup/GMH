/**
 * 
 */
package org.fourgeeks.gha.webclient.client.viewpermission;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class ViewPermissionRecord extends GHAGridRecord<ViewPermission> {
	private ViewPermission function;

	/**
	 * @param function
	 */
	public ViewPermissionRecord(ViewPermission function) {
		this.function = function;
		// TODO
		setAttribute("view", function.getView().getName());
		setAttribute("permission", function.getPermission().getName());
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
	public ViewPermission toEntity() {
		return this.function;
	}

}
