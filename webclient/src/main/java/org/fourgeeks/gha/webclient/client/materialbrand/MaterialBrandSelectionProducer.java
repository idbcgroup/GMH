/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.MaterialBrand;

/**
 * @author emiliot
 * 
 */
public interface MaterialBrandSelectionProducer {
	public void addMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener);

	public void notifyMaterialBrand(MaterialBrand materialBrand);

	public void removeMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener);
}
