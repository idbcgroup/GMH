/**
 * 
 */
package org.fourgeeks.gha.webclient.client.function;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.Function;

/**
 * @author alacret
 * 
 */
public class FunctionUtil {
	private FunctionUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param function
	 * @return a functionrecord
	 */
	public static FunctionRecord toGridRecord(Function function) {
		return new FunctionRecord(function);
	}

	/**
	 * @param entities
	 * @return alist of functionrecord
	 */
	public static List<FunctionRecord> toGridRecords(List<Function> entities) {
		List<FunctionRecord> list = new ArrayList<FunctionRecord>();
		for (Function function : entities)
			list.add(new FunctionRecord(function));
		return list;
	}
}
