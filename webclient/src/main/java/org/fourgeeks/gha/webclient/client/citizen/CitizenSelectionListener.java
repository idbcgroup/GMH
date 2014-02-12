package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author alacret
 * 
 */
public interface CitizenSelectionListener {

	/**
	 * @param citizen
	 *            the citizen selected
	 */
	public void onCitizenSelect(Citizen citizen);

}