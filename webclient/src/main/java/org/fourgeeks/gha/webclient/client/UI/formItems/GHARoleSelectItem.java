package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHARoleSelectItem extends GHASelectItem {
	public static final String labelKey = "responsible-role";

	/**
	 * @param width
	 */
	public GHARoleSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		fill();
	}

	/**
	 * 
	 */
	public GHARoleSelectItem() {
		super(GHAStrings.get(labelKey));
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHARoleSelectItem(int width, boolean required,
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
	public GHARoleSelectItem(boolean required, ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey));
		setRequired(required);
		addChangedHandler(changedHandler);
		fill();
	}

	public void fill() {
		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<Role>>() {

			@Override
			public void onSuccess(List<Role> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Role role : result)
					valueMap.put(role.getId() + "", role.getName());
				setValueMap(valueMap);

			}
		});
	}
}
