package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHAExternalProviderSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAExternalProviderSelectItem() {
		super(GHAStrings.get("maintenance-provider"));
		fill();
	}
	
	public GHAExternalProviderSelectItem(String title) {
		super(title);
		fill();
	}
	
	/**
	 * @param width
	 */
	public GHAExternalProviderSelectItem(int width) {
		this();
		setWidth(width);
	}


	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAExternalProviderSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}
	
	public GHAExternalProviderSelectItem(String title, boolean enabled) {
		this(title);
		setDisabled(!enabled);
	}	

	private void fill() {
		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider entity : result)
							valueMap.put(entity.getId() + "", entity
									.getInstitution().getName() + "");
						setValueMap(valueMap);
					}
				});
	}
}
