/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot
 * 
 */
public class CitizenUtil {
	private CitizenUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param citizen
	 * @return
	 */
	public static CitizenRecord toGridRecord(Citizen citizen) {
		return new CitizenRecord(citizen);
	}

	/**
	 * @param citizens
	 * @return
	 */
	public static List<CitizenRecord> toGridRecords(List<Citizen> citizens) {
		final List<CitizenRecord> list = new ArrayList<CitizenRecord>();
		for (final Citizen citizen : citizens)
			list.add(new CitizenRecord(citizen));
		return list;
	}
}
