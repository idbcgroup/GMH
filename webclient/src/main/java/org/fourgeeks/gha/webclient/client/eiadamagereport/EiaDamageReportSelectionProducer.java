package org.fourgeeks.gha.webclient.client.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.GlaLog;

public interface EiaDamageReportSelectionProducer {
	/**
	 * @param eiaSelectionListener
	 */
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener);

	/**
	 * @param eiaSelectionListener
	 */
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener);

	/**
	 * @param glaLog
	 */
	public void notifyEiaDamageReport(GlaLog glaLog);

}