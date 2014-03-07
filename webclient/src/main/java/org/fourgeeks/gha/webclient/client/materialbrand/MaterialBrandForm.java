/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;

import com.google.gwt.event.logical.shared.ResizeEvent;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandForm extends GHAForm<MaterialBrand> implements
		MaterialBrandSelectionProducer {

	private List<MaterialBrandSelectionListener> listeners;
	private GHATextItem codeItem, externalCodeItem, nameItem, modelItem;
	private GHATextAreaItem descriptionItem;
	private GHABrandSelectItem brandItem;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#activate()
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub

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
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#deactivate()
	 */
	@Override
	public void deactivate() {
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
	 * @see
	 * com.google.gwt.event.logical.shared.ResizeHandler#onResize(com.google
	 * .gwt.event.logical.shared.ResizeEvent)
	 */
	@Override
	public void onResize(ResizeEvent arg0) {
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
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#save(org.fourgeeks
	 * .gha.webclient.client.UI.GHAAsyncCallback)
	 */
	@Override
	public void save(GHAAsyncCallback<MaterialBrand> callback) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#set(java.lang
	 * .Object)
	 */
	@Override
	public void set(MaterialBrand entity) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#update(org
	 * .fourgeeks.gha.webclient.client.UI.GHAAsyncCallback)
	 */
	@Override
	public void update(GHAAsyncCallback<MaterialBrand> callback) {
		// TODO Auto-generated method stub

	}

}
