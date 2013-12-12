package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAFacilitySelectItem extends GHASelectItem {
	/**
	 * 
	 */
	public GHAFacilitySelectItem() {
		super(GHAStrings.get("facility"));
		fill();
	}

	public GHAFacilitySelectItem(String title) {
		super(title);
		fill();
	}

	/**
	 * @param width
	 */
	public GHAFacilitySelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAFacilitySelectItem(int width, boolean required,
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
	public GHAFacilitySelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	public void fill() {
		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {

			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Facility facility : result)
					valueMap.put(facility.getId() + "", facility.getName());
				setValueMap(valueMap);

			}
		});
	}
}
