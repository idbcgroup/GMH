package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAActivityCategorySelectItem extends GHASelectItem {

	/** */
	public GHAActivityCategorySelectItem() {
		super(GHAStrings.get("category"));

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ActivityCategoryEnum value : ActivityCategoryEnum.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}
		setValueMap(valueMap);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAActivityCategorySelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAActivityCategorySelectItem(boolean active) {
		this();
		setDisabled(!active);
	}
}
