package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author alacret
 * 
 */
public interface MaterialSelectionListener {
	/**
	 * @param eiaType
	 *            triggers when a material is selected
	 */
	public void select(Material eiaType);
}