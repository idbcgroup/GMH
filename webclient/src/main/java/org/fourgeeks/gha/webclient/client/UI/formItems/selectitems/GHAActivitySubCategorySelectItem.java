package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAActivitySubCategorySelectItem extends GHASelectItem {
	/** */
	public GHAActivitySubCategorySelectItem() {
		super(GHAStrings.get("subcategory"));

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ActivitySubCategoryEnum value : ActivitySubCategoryEnum.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}

		setValueMap(valueMap);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAActivitySubCategorySelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAActivitySubCategorySelectItem(boolean active) {
		this();
		setDisabled(!active);
	}
}
