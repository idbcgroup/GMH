package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHABpiSelectItem extends GHASelectItem {
	/**
	 * @param width
	 */
	public GHABpiSelectItem(int width) {
		super(GHAStrings.get("bpi-select-item"), width);
		fill();
	}

	/**
	 * 
	 */
	public GHABpiSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHABpiSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("bpi-select-item"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	public void fill() {
		GHACache.INSTANCE.getBpis(new GHAAsyncCallback<List<Bpi>>() {
			@Override
			public void onSuccess(List<Bpi> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Bpi bpi : result)
					valueMap.put(bpi.getId() + "", bpi.getInstitution()
							.getName());
				setValueMap(valueMap);
			}
		}, true);

	}
}
