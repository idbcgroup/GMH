package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.ActivityTypeModel;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAActivityTypeSelectItem extends GHASelectItem {
	private HashMap<Long, ActivityType> subTypes;

	/** */
	public GHAActivityTypeSelectItem() {
		super(GHAStrings.get("category"));
		fill();
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAActivityTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAActivityTypeSelectItem(boolean active) {
		this();
		setDisabled(!active);
	}

	private void fill() {
		ActivityTypeModel
				.getAllTypes(new GHAAsyncCallback<List<ActivityType>>() {
					@Override
					public void onSuccess(List<ActivityType> result) {
						subTypes = new HashMap<Long, ActivityType>();

						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ActivityType type : result) {
							subTypes.put(type.getId(), type);
							valueMap.put(Long.toString(type.getId()),
									GHAStrings.get(type.getDescription()));
						}
						setValueMap(valueMap);
					}
				});
	}

	/**
	 * @return the Activity Type
	 */
	public ActivityType getValueAsActivityType() {
		long id = Long.valueOf(getValueAsString());
		return subTypes.get(id);
	}
}
