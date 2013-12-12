package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAObuSelectItem extends GHASelectItem {
	public static final String labelKey = "obu";

	/**
	 * 
	 */
	public GHAObuSelectItem() {
		super(GHAStrings.get("obu"));
		fill();
	}
	
	/**
	 * @param width
	 */
	public GHAObuSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAObuSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAObuSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
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
