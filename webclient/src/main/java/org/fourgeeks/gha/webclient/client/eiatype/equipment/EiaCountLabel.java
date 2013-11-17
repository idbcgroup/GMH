package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

/**
 * @author emiliot adds a leyend to Eia resultSet
 * 
 */
public class EiaCountLabel extends GHALabel {
	/**
	 * 
	 */
	public EiaCountLabel() {
		super("");
		setStyleName("text-label-mini");
	}

	public void setEiaStateTotals(List<Eia> list) {
		final int n = EiaStateEnum.values().length;
		int count[] = new int[n];
		for (int i = 0; i < n; ++i)
			count[i] = 0;

		for (Eia eia : list) {
			count[eia.getState().ordinal()]++;
		}

		// TODO: INTERNACIONALIZAR EL NAME DE CADA EIASTATEENUM
		StringBuilder builder = new StringBuilder();
		builder.append(EiaStateEnum.values()[0].name() + ": "
				+ Integer.toString(count[0]));

		for (int i = 1; i < n; ++i) {
			builder.append(", " + EiaStateEnum.values()[i].name() + ": "
					+ Integer.toString(count[i]));
		}

		setContents(builder.toString());
	}
}
