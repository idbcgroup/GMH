package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHAPeriodOfTimeSelectItem extends GHASelectItem {
	public static final String labelKey = "period-of-time";
	
	/**
	 * 
	 */
	public GHAPeriodOfTimeSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(getValueMap());
		setDefaultValue(TimePeriodEnum.YEARS.name());
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
	 * @param width
	 * @param required
	 */
	public GHAPeriodOfTimeSelectItem(int width, boolean required) {
		this(width);
		setRequired(required);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAPeriodOfTimeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this(required);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAPeriodOfTimeSelectItem(int width, boolean required,ChangedHandler changedHandler) {
		this(width,required);
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
