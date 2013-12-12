package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAEiaStateSelectItem extends GHASelectItem {
	public static final String labelKey = "state";

	/**
	 * 
	 */
	public GHAEiaStateSelectItem() {
		super(GHAStrings.get("state"));
		setValueMap(EiaStateEnum.toValueMap());
	}
	
	/**
	 * 
	 */
	public GHAEiaStateSelectItem(String title) {
		super(title);
		setValueMap(EiaStateEnum.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAEiaStateSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(required,changedHandler);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

}
