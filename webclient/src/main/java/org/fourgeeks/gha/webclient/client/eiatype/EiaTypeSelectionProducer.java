package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author alacret
 * 
 */
public interface EiaTypeSelectionProducer {
	/**
	 * @param eIATypeSelectionListener
	 */
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener);

	/**
	 * @param eIATypeSelectionListener
	 */
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener);

	public void notifyEiaType(EiaType eiaType);

}