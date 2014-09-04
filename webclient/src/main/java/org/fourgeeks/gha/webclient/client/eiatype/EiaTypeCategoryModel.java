/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author emiliot
 * 
 */
public class EiaTypeCategoryModel {
	private static final GWTEiaTypeCategoryServiceAsync categoryService = GWT
			.create(GWTEiaTypeCategoryService.class);

	public static void getAll(GHAAsyncCallback<List<ServiceResourceCategory>> callback) {
		categoryService.getAll(callback);
	}
}
