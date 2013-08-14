package org.fourgeeks.gha.webclient.client.eia;

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

}