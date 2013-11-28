package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHABpiSelectItem extends GHASelectItem {
	public static final String labelKey = "bpi";

	/**
	 * @param width
	 */
	public GHABpiSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		fill();
	}

	/**
	 * 
	 */
	public GHABpiSelectItem() {
		super(GHAStrings.get(labelKey));
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHABpiSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHABpiSelectItem(boolean required, ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey));
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
