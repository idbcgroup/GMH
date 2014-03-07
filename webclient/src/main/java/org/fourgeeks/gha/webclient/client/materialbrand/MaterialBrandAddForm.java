/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandAddForm extends GHAAddForm<MaterialBrand> implements
		MaterialBrandSelectionProducer {

	/**
	 * @param title
	 */
	public MaterialBrandAddForm(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #addMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void addMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #notifyMaterialBrand(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public void notifyMaterialBrand(MaterialBrand materialBrand) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #removeMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void removeMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm#save()
	 */
	@Override
	protected void save() {
		// TODO Auto-generated method stub

	}

}
