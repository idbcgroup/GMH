package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot,jfuentes
 * 
 */
public class GHAEiaTypeSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAEiaTypeSelectItem() {
		super(GHAStrings.get("eiatype"));
		fill(true);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHAEiaTypeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this();
		setWidth(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param forceFromServer
	 */
	public void fill(boolean forceFromServer) {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (EiaType eiaType : result)
					valueMap.put(eiaType.getCode() + "", eiaType.getName());
				setValueMap(valueMap);

			}
		}, forceFromServer);
	}
}
