package org.fourgeeks.gha.webclient.client.UI.formItems;

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
		setValueMap(TimePeriodEnum.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAPeriodOfTimeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAPeriodOfTimeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
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

}
