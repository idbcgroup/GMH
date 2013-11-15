package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author jfuentes A select item with the Document Types
 * 
 */
public class GHADoumentTypeSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHADoumentTypeSelectItem() {
		super(GHAStrings.get("id-type"));
		setValueMap(DocumentTypeEnum.toValueMap());
	}
	
	/**
	 * @param width
	 */
	public GHADoumentTypeSelectItem(int width) {
		super(GHAStrings.get("id-type"), width);
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
		super(GHAStrings.get("id-type"), width);
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
		super(GHAStrings.get("id-type"));
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(DocumentTypeEnum.toValueMap());
	}

}
