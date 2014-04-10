package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;

/**
 * 
 * @author caparicio
 * 
 */
public interface MaterialListSelectionProducer {
	/**
	 * 
	 * @param materialListSelectionListener
	 */
	public void addMaterialListSelectionListener(
			MaterialListSelectionListener materialListSelectionListener);

	/**
	 * 
	 * @param materialListSelectionListener
	 */
	public void removeMaterialListSelectionListener(
			MaterialListSelectionListener materialListSelectionListener);

	/**
	 * 
	 * @param materials
	 */
	public void notifyMaterialList(List<Material> materials);
}