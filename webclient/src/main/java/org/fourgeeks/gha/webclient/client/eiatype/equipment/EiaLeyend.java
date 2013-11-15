package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

/**
 * @author emiliot adds a leyend to Eia resultSet
 * 
 */
public class EiaLeyend extends GHAVerticalLayout {
	/**
	 * label used to show the info
	 */
	private GHALabel label;

	/**
	 * 
	 */
	public EiaLeyend() {
		super();
		label = new GHALabel();
	}

	public void setEiaStateTotals(List<Eia> list) {
		final int n = EiaStateEnum.values().length;
		int count[] = new int[n];
		for (int i = 0; i < n; ++i)
			count[i] = 0;

		for (Eia eia : list) {
			count[eia.getState().ordinal()]++;
		}

		// String builder = new StringBuilder();
		// builder.append(EiaStateEnum.values()[0].name() + ": "
		// + Integer.toString(count[0]));
	}
}
