/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class GHAEiaTypeCategorySelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAEiaTypeCategorySelectItem() {
		super();
		fill();
	}

	/**
	 * @param width
	 */
	public GHAEiaTypeCategorySelectItem(int width) {
		super(width);
		fill();
	}

	/**
	 * @param title
	 */
	public GHAEiaTypeCategorySelectItem(String title) {
		super(title);
		fill();
	}

	/**
	 * @param title
	 * @param enabled
	 */
	public GHAEiaTypeCategorySelectItem(String title, boolean enabled) {
		super(title, enabled);
		fill();
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeCategorySelectItem(String title, boolean required,
			ChangedHandler changedHandler) {
		super(title, required, changedHandler);
		fill();
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHAEiaTypeCategorySelectItem(String title, int width) {
		super(title, width);
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHAEiaTypeCategorySelectItem(String title, int width, boolean active) {
		super(title, width, active);
		fill();
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeCategorySelectItem(String title, int width,
			boolean required, ChangedHandler changedHandler) {
		super(title, width, required, changedHandler);
		fill();
	}

	public void fill() {
		final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		GHACache.INSTANCE
				.getEiaTypeCategories(new GHAAsyncCallback<List<EiaTypeCategory>>() {

					@Override
					public void onSuccess(List<EiaTypeCategory> result) {
						String s = "";
						for (EiaTypeCategory category : result) {
							valueMap.put(category.getCode(), category.getName());
							s += " " + category.getCode() + category.getName();
						}
						Window.alert(s);
						setValueMap(valueMap);
					}
				});
	}

}
