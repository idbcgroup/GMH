package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAEiaTypeTypeSelectItem extends GHASelectItem {

	/**
	 * @param width
	 * 
	 */
	public GHAEiaTypeTypeSelectItem(int width) {
		super(GHAStrings.get("eiatype"), width);

		setValueMap(EiaTypeEnum.toValueMap());
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("eiatype"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(EiaTypeEnum.toValueMap());
	}

	/**
	 * 
	 */
	public GHAEiaTypeTypeSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	// public EiaTypeEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaTypeEnum.valueOf(getValueAsString());
	// }
}