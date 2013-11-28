package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Map;

import org.fourgeeks.gha.domain.HasKey;

import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 * @param <T>
 */
public class GHAComboboxItem<T extends HasKey> extends ComboBoxItem {

	/**
	 * 
	 */
	public GHAComboboxItem() {
		super();
		setHeight(20);
		setWidth("*");
		setPickerIconHeight(20);
		setOriginalStyle();
		
		setShowFocused(false);
		setAllowEmptyValue(true);
		
		// setErrorOrientation(FormErrorOrientation.RIGHT);
		setShowErrorIcon(false);
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("combobox");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}
	
	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHAComboboxItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHAComboboxItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHAComboboxItem(String title, int width,	ChangedHandler changedHandler) {
		this(title);
		setWidth(width);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param title
	 */
	public GHAComboboxItem(String title, ChangedHandler changedHandler) {
		this(title);
		addChangedHandler(changedHandler);
	}

	public GHAComboboxItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 * @param callback
	 */
	public GHAComboboxItem(String title, int width, boolean active) {
		this(title, width);
		setDisabled(!active);
	}

	/**
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	public void setValue(T entity) {
		Map<String, String> newMap = null;

		try {
			newMap = (Map<String, String>) getAttributeAsMap("valueMap");
		} catch (Exception e) {
			throw new IllegalStateException("Value Map has not been set");
		}

		if (newMap.containsKey(entity.getKey()))
			setValue(entity.getKey());

		throw new IllegalStateException("Values is not in map");
	}

}