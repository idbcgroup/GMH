package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHASelectItem extends SelectItem {

	/**
	 * 
	 */
	public GHASelectItem() {
		super();
		setHeight(20);
		setWidth(100);
		setPickerIconHeight(20);
		setTextBoxStyle("select");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
		setShowFocused(false);
		setAllowEmptyValue(true);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHASelectItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param changedHandler
	 */
	public GHASelectItem(String title, int width, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setWidth(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHASelectItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	/**
	 * @param width
	 */
	public GHASelectItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 */
	public GHASelectItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 * @param enabled
	 */
	public GHASelectItem(String title, boolean enabled) {
		this(title);
		setDisabled(!enabled);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required){
			setTextBoxStyle("select required");
			addBlurHandler(new BlurHandler() {
				@Override
				public void onBlur(BlurEvent event) {
					if(validate()){
						setTextBoxStyle("select requiredValidated");
					}else{
						setTextBoxStyle("select required");
					}
				}
			});			
		}else{
			setTextBoxStyle("select");
		}
	}
}