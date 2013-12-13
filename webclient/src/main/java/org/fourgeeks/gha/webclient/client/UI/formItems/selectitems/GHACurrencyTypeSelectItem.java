package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHACurrencyTypeSelectItem extends GHASelectItem {
	/** */
	public static final String labelKey = "currency";

	/** */
	public GHACurrencyTypeSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(CurrencyTypeEnum.toValueMap());
		setDefaultValue(CurrencyTypeEnum.BS.name());
	}

	/**
	 * @param width
	 */
	public GHACurrencyTypeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHACurrencyTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHACurrencyTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}
}
