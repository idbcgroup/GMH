/**
 * 
 */
package org.fourgeeks.gha.webclient.client.viewpermission;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;

/**
 * @author alacret
 * 
 */
public class ViewPermissionUtil {
	private ViewPermissionUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param function
	 * @return a functionrecord
	 */
	public static ViewPermissionRecord toGridRecord(
			ViewPermission function) {
		return new ViewPermissionRecord(function);
	}

	/**
	 * @param entities
	 * @return alist of functionrecord
	 */
	public static List<ViewPermissionRecord> toGridRecords(
			List<ViewPermission> entities) {
		List<ViewPermissionRecord> list = new ArrayList<ViewPermissionRecord>();
		for (ViewPermission function : entities)
			list.add(new ViewPermissionRecord(function));
		return list;
	}
}
