package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAEiaStateSelectItem extends GHASelectItem {

	/**
	 * @param width
	 */
	public GHAEiaStateSelectItem(int width) {
		super(GHAStrings.get("eiastate-select-item"), width);
		setValueMap(EiaStateEnum.toValueMap());
	}

	/**
	 * 
	 */
	public GHAEiaStateSelectItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("eia-state-select-item"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(EiaStateEnum.toValueMap());
	}

}
