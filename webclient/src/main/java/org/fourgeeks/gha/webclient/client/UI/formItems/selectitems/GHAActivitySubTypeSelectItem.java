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
public class GHAActivitySubTypeSelectItem extends GHASelectItem {
	private HashMap<Long, ActivityType> subTypes;

	/** */
	public GHAActivitySubTypeSelectItem() {
		super(GHAStrings.get("subcategory"));
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAActivitySubTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAActivitySubTypeSelectItem(boolean active) {
		this();
		setDisabled(!active);
	}

	/**
	 * @param type
	 */
	public void fill(ActivityType type) {
		if (type == null)
			return;

		ActivityTypeModel.getSubTypes(type,
				new GHAAsyncCallback<List<ActivityType>>() {
					@Override
					public void onSuccess(List<ActivityType> result) {
						subTypes = new HashMap<Long, ActivityType>();

						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ActivityType subType : result) {
							subTypes.put(subType.getId(), subType);
							valueMap.put(Long.toString(subType.getId()),
									GHAStrings.get(subType.getDescription()));
						}

						setValueMap(valueMap);
					}
				});
	}

	/**
	 * @return the Activity Type
	 */
	public ActivityType getValueAsActivitySubType() {
		long id = Long.valueOf(getValueAsString());
		return subTypes.get(id);
	}
}
