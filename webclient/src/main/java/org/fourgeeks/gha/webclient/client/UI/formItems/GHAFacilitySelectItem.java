package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

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
		super(GHAStrings.get("facility-select-item"));
		fill();
	}
	
	/**
	 * @param width
	 */
	public GHAFacilitySelectItem(int width) {
		super(GHAStrings.get("facility-select-item"), width);
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAFacilitySelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("facility-select-item"), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAFacilitySelectItem(boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get("facility-select-item"));
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
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
