package org.fourgeeks.gha.webclient.client.UI;

import java.util.Map;

import org.fourgeeks.gha.domain.HasKey;

import com.smartgwt.client.widgets.form.fields.ComboBoxItem;

/**
 * @author alacret
 * 
 * @param <T>
 */
public abstract class GHAComboboxItem<T extends HasKey> extends ComboBoxItem {

	/**
	 * 
	 */
	public GHAComboboxItem() {
		super();
		setTextBoxStyle("select");
		setHeight(18);
		setCellStyle("gha-form-cell");
		setWidth(100);
		setTitleStyle("input-title");
		setShowFocused(false);
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
	 * @param callback
	 * @param active
	 */
	public GHAComboboxItem(String title, int width) {
		this(title);
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