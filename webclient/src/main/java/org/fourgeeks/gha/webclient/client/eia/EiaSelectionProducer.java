package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;

/**
 * @author alacret
 * 
 */
public interface EiaSelectionProducer {
	/**
	 * @param eiaSelectionListener
	 */
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener);

	/**
	 * @param eiaSelectionListener
	 */
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener);

	/**
	 * @param eia
	 */
	public void notifyEia(Eia eia);

}