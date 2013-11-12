/**
 * 
 */
package org.fourgeeks.gha.webclient.client.appformviewfunction;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionUtil {
	private AppFormViewFunctionUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param function
	 * @return a functionrecord
	 */
	public static AppFormViewFunctionRecord toGridRecord(
			AppFormViewFunction function) {
		return new AppFormViewFunctionRecord(function);
	}

	/**
	 * @param entities
	 * @return alist of functionrecord
	 */
	public static List<AppFormViewFunctionRecord> toGridRecords(
			List<AppFormViewFunction> entities) {
		List<AppFormViewFunctionRecord> list = new ArrayList<AppFormViewFunctionRecord>();
		for (AppFormViewFunction function : entities)
			list.add(new AppFormViewFunctionRecord(function));
		return list;
	}
}
