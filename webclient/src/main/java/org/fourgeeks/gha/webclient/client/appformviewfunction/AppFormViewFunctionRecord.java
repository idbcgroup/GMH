/**
 * 
 */
package org.fourgeeks.gha.webclient.client.appformviewfunction;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionRecord extends
		GHAGridRecord<AppFormViewFunction> {
	private AppFormViewFunction function;

	/**
	 * @param function
	 */
	public AppFormViewFunctionRecord(AppFormViewFunction function) {
		this.function = function;
		setAttribute("module", function.getAppForm().getModule().getName());
		setAttribute("screen", function.getAppForm().getName());
		setAttribute("view", function.getView().getName());
		setAttribute("function", function.getFunction().getName());
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
	public AppFormViewFunction toEntity() {
		return this.function;
	}

}
