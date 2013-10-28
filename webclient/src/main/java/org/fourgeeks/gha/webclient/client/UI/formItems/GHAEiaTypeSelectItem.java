package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAEiaTypeSelectItem extends GHASelectItem {
	/**
	 * @param width
	 */
	public GHAEiaTypeSelectItem(int width) {
		super(GHAStrings.get("eiatype-select-item"), width);
		fill(true);
	}

	/**
	 * 
	 */
	public GHAEiaTypeSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("eiatype-select-item"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		fill(true);
	}

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
