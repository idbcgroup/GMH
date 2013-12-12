package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot,jfuentes
 * 
 */
public class GHAWorkingAreaSelectItem extends GHASelectItem {
	/**
	 * 
	 */
	public GHAWorkingAreaSelectItem() {
		super(GHAStrings.get("workingarea"));
		fill();
	}
	
	/**
	 * @param title
	 */
	public GHAWorkingAreaSelectItem(String title) {
		super(title);
		fill();
	}
	
	/**
	 * @param width
	 */
	public GHAWorkingAreaSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAWorkingAreaSelectItem(int width, boolean required,
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
	public GHAWorkingAreaSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
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
