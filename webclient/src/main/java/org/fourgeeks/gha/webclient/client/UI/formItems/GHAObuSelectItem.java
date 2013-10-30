package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAObuSelectItem extends GHASelectItem {
	/**
	 * @param width
	 */
	public GHAObuSelectItem(int width) {
		super(GHAStrings.get("obu-select-item"), width);
		fill();
	}

	/**
	 * 
	 */
	public GHAObuSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAObuSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("obu-select-item"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	public void fill() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {

			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				// valueMap.put(GHAStrings.get("none"), null);
				for (Obu obu : result)
					valueMap.put(obu.getId() + "", obu.getName());
				setValueMap(valueMap);

			}
		});
	}
}