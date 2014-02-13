package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author alacret
 * 
 */
public interface CitizenSelectionProducer {
	/**
	 * @param listener
	 */
	public void addCitizenSelectionListener(CitizenSelectionListener listener);

	/**
	 * @param listener
	 */
	public void removeCitizenSelectionListener(CitizenSelectionListener listener);

	/**
	 * @param citizen
	 */
	public void notifyCitizen(Citizen citizen);

}
