/**
 * 
 */
package org.fourgeeks.gha.webclient.client.function;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class FunctionRecord extends GHAGridRecord<Function> {
	private Function function;

	/**
	 * @param function
	 */
	public FunctionRecord(Function function) {
		this.function = function;
		setAttribute("module", function.getView().getScreen().getModule()
				.getName());
		setAttribute("screen", function.getView().getScreen().getName());
		setAttribute("view", function.getView().getName());
		setAttribute("function", function.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public Function toEntity() {
		return this.function;
	}

}
