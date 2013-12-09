package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author alacret A select item with the mobility items
 */
public class GHABrandSelectItem extends GHASelectItem {
	public static final String labelKey = "brand";
	
	/**
	 * @param width
	 * 
	 */
	public GHABrandSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		fill(false);
	}

	/**
	 * Fills the select item
	 * 
	 * @param forceFromServer
	 *            if the cache must be dismissed
	 */
	public void fill(boolean forceFromServer) {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				setValueMap(valueMap);
				redraw();

			}
		}, forceFromServer);
	}

	/**
	 * 
	 */
	public GHABrandSelectItem() {
		super(GHAStrings.get(labelKey));
		fill(false);
	}

	// public boolean isNull() {
	// Window.alert("3.1.1");
	// Window.alert(getValueAsString());
	// return getValue() == null;
	// }
	//
	// @Override
	// public String getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return getValueAsString();
	// }
}
