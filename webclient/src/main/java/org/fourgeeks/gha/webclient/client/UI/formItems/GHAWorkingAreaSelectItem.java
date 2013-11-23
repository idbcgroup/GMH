package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAWorkingAreaSelectItem extends GHASelectItem {
	public static final String labelKey = "workingarea";

	/**
	 * @param width
	 */
	public GHAWorkingAreaSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		fill();
	}

	/**
	 * 
	 */
	public GHAWorkingAreaSelectItem() {
		super(GHAStrings.get(labelKey));
		fill();
	}

	public GHAWorkingAreaSelectItem(String title) {
		super(title);
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAWorkingAreaSelectItem(int width, boolean required,
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
	public GHAWorkingAreaSelectItem(boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey));
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	public void fill() {
		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {

					@Override
					public void onSuccess(List<WorkingArea> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (WorkingArea workingArea : result)
							valueMap.put(workingArea.getId() + "",
									workingArea.getName());
						setValueMap(valueMap);

					}
				});
	}
}
