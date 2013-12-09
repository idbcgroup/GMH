package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author jfuentes A select item with the Document Types
 * 
 */
public class GHADoumentTypeSelectItem extends GHASelectItem {
	public static final String labelKey = "id-type";
	
	/**
	 * 
	 */
	public GHADoumentTypeSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(DocumentTypeEnum.toValueMap());
	}
	
	/**
	 * @param width
	 */
	public GHADoumentTypeSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		setValueMap(DocumentTypeEnum.toValueMap());
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHADoumentTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(DocumentTypeEnum.toValueMap());
	}
	
	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHADoumentTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey));
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(DocumentTypeEnum.toValueMap());
	}

}
