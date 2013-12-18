package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import java.math.BigDecimal;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

/**
 * @author naramirez
 */
public class MaintenanceProtocolStadisticDataLabel extends GHALabel {
	final String ONE_SPACE = " ", TWO_POINTS = ": ", OPEN_PARENTESIS = "(",
			CLOSE_PARENTESIS = ") ", SIGN = " - ";

	/**
	 * 
	 */
	public MaintenanceProtocolStadisticDataLabel() {
		super("");
		setStyleName("text-label-mini");
	}

	/**
	 * @param data
	 */
	public void setStadisticInfo(MaintenanceProtocolStadisticData data) {
		long numActiv = data.getNumberActivities();
		long numSubProtoc = data.getNumberSubProtocols();
		long numSubProtocActiv = data.getNumberSubProtocolsActivities();
		long time = data.getEstimatedDuration();
		TimePeriodEnum pot = data.getPot();
		BigDecimal cost = data.getEstimatedCost();
		CurrencyTypeEnum currency = data.getCurrency();

		String totalsStr = GHAStrings.get("totals");
		String activitiesStr = GHAStrings.get("activities");
		String subprotocolsStr = GHAStrings.get("subprotocols");
		String totalTimeStr = GHAStrings.get("total-time");
		String totalCostStr = GHAStrings.get("total-cost");

		String totalsString = totalsStr + TWO_POINTS + numActiv + ONE_SPACE
				+ activitiesStr + SIGN + numSubProtoc + OPEN_PARENTESIS
				+ numSubProtocActiv + CLOSE_PARENTESIS + subprotocolsStr;

		String timeAndCostStirng = totalTimeStr + TWO_POINTS + time + ONE_SPACE
				+ pot + SIGN + totalCostStr + TWO_POINTS + cost + ONE_SPACE
				+ currency;

		StringBuilder builder = new StringBuilder();
		builder.append(totalsString).append(timeAndCostStirng);

		setContents(builder.toString());
	}
}
