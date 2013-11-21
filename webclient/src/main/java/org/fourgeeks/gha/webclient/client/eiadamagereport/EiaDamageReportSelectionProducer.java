package org.fourgeeks.gha.webclient.client.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;

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
	 * @param eiaDamageReport
	 */
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport);

}