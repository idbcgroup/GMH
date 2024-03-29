package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHABpuSelectItem extends GHASelectItem {
	public static final String labelKey = "bpu-select-item";

	/**
	 * 
	 */
	public GHABpuSelectItem() {
		super(GHAStrings.get("bpu"));
		fill();
	}

	public GHABpuSelectItem(String title) {
		super(title);
		fill();
	}

	/**
	 * @param width
	 */
	public GHABpuSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHABpuSelectItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHABpuSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHABpuSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(required, changedHandler);
		setWidth(width);
	}

	public void fill() {
		GHACache.INSTANCE.getBpus(new GHAAsyncCallback<List<Bpu>>() {
			@Override
			public void onSuccess(List<Bpu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Bpu bpu : result) {
					Citizen citizen = bpu.getCitizen();
					valueMap.put(bpu.getId() + "", citizen.getFirstName()
							+ citizen.getFirstLastName());
				}

				setValueMap(valueMap);
			}
		}, false);
	}
}
