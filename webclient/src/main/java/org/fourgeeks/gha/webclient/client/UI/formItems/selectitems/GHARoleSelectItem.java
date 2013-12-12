package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHARoleSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHARoleSelectItem() {
		super(GHAStrings.get("responsible-role"));
		fill();
	}

	/**
	 * @param width
	 */
	public GHARoleSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHARoleSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHARoleSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
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
