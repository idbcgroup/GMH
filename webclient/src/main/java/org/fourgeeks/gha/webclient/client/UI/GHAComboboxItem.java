package org.fourgeeks.gha.webclient.client.UI;

import java.util.LinkedHashMap;
import java.util.Map;

import org.fourgeeks.gha.domain.HasKey;

import com.smartgwt.client.widgets.form.fields.ComboBoxItem;

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
	public GHAComboboxItem(String title, int width) {
		this(title, width, true);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHAComboboxItem(String title, int width, boolean active) {
		super(title);
		setWidth(width);
		setDisabled(!active);
	}

	/**
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	public void setValue(T entity) {
		Map<String, String> newMap = null;

		try {
			newMap = (LinkedHashMap) getAttributeAsMap("valueMap");
		} catch (Exception e) {
			throw new IllegalStateException("Value Map has not been set");
		}

		if (newMap.containsKey(entity.getKey()))
			setValue(entity.getKey());

	}
}