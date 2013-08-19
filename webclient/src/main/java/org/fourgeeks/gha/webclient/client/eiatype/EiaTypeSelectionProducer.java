package org.fourgeeks.gha.webclient.client.eiatype;

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

}