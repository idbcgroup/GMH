package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;

/**
 * 
 * @author caparicio
 * 
 */
public interface MaterialListSelectionListener {

	/**
	 * @param materials
	 */
	public void select(List<Material> materials);

}