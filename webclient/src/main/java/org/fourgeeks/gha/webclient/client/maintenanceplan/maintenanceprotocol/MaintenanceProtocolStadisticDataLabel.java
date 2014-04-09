package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.math.BigDecimal;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.smartgwt.client.types.Alignment;

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
		setStyleName("text-label");
		setAlign(Alignment.RIGHT);
	}

	/**
	 * @param data
	 */
	public void setStadisticInfo(MaintenanceProtocolStadisticData data) {
		final long numActiv = data.getNumberActivities();
		final long numSubProtoc = data.getNumberSubProtocols();
		final long numSubProtocActiv = data.getNumberSubProtocolsActivities();
		final long time = data.getEstimatedDuration();
		final TimePeriodEnum pot = data.getPot();
		final BigDecimal cost = data.getEstimatedCost();
		final CurrencyTypeEnum currency = data.getCurrency();

		final String totalsStr = GHAStrings.get("totals");
		final String activitiesStr = GHAStrings.get("activities");
		final String subprotocolsStr = GHAStrings.get("subprotocols");
		final String totalTimeStr = GHAStrings.get("total-time");
		final String totalCostStr = GHAStrings.get("total-cost");

		final String totalsString = totalsStr + ": " + numActiv + " " + activitiesStr
				+ " - " + numSubProtoc + "(" + numSubProtocActiv + ") "
				+ subprotocolsStr;

		final String timeAndCostStirng = totalTimeStr + ": " + time + " "
				+ GHAStrings.get(pot.name().toLowerCase()) + " - "
				+ totalCostStr + ": " + cost + " " + currency;

		final StringBuilder builder = new StringBuilder();
		builder.append(totalsString).append(" || ").append(timeAndCostStirng);

		setContents(builder.toString());
	}
}
