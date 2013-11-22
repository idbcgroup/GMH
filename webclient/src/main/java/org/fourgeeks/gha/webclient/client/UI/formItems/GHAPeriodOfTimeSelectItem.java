package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHAPeriodOfTimeSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAPeriodOfTimeSelectItem() {
		super(GHAStrings.get("period-of-time"));
		setValueMap(getValueMap());
		setDefaultValue(TimePeriodEnum.HOURS.name());
	}

	/**
	 * @param width
	 */
	public GHAPeriodOfTimeSelectItem(int width) {
		this();
		setWidth(width);
	}
	
	/**
	 * @param required
	 */
	public GHAPeriodOfTimeSelectItem(boolean required) {
		this();
		setRequired(required);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAPeriodOfTimeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAPeriodOfTimeSelectItem(int width, boolean required,ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @return a valueMap to fill the select
	 */
	public static LinkedHashMap<String, String> getValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (TimePeriodEnum timePeriod : TimePeriodEnum.values()){
			valueMap.put(timePeriod.name()+"",GHAStrings.get(timePeriod.name().toLowerCase()));
		}
		return valueMap;
	}

}
