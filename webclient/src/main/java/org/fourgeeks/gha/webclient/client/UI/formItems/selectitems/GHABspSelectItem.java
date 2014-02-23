package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot, naramirez
 * 
 */
public class GHABspSelectItem extends GHASelectItem {
	private HashMap<Long, Bsp> bsps;

	/**
	 * 
	 */
	public GHABspSelectItem() {
		super(GHAStrings.get("bsp"));
		fill();
	}

	/**
	 * @param width
	 */
	public GHABspSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHABspSelectItem(int width, boolean required,
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
	public GHABspSelectItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this();
		setTitle(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHABspSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * 
	 */
	public void fill() {
		GHACache.INSTANCE.getBsps(new GHAAsyncCallback<List<Bsp>>() {
			@Override
			public void onSuccess(List<Bsp> result) {
				bsps = new HashMap<Long, Bsp>();

				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Bsp bsp : result) {
					Obu obu = bsp.getObu();
					valueMap.put(bsp.getId() + "", obu.getName());
					bsps.put(bsp.getId(), bsp);
				}
				setValueMap(valueMap);
			}
		});
	}

	/**
	 * @return the Bsp
	 */
	public Bsp getValueAsBsp() {
		long id = Long.valueOf(getValueAsString());
		return bsps.get(id);
	}
}
