/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.materialcategory.GHAMaterialCategoryPickTreeItem;

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
	private GHAMaterialCategoryPickTreeItem categoryItem;

	private GHASelectItem typeItem;
	private GHADynamicForm form;
	private boolean cleanCodeItem = true;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);
		listeners = new ArrayList<MaterialBrandSelectionListener>();
		nameItem = new GHATextItem(GHAStrings.get("name"), true, changedHandler);
		nameItem.setColSpan(2);
		codeItem = new GHACodeTextItem(true, changedHandler);
		codeItem.disable();

		externalCodeItem = new GHATextItem(GHAStrings.get("external-code"),
				false, changedHandler);
		typeItem = new GHASelectItem(GHAStrings.get("type"), true,
				changedHandler);
		brandItem = new GHABrandSelectItem();

		modelItem = new GHATextItem(GHAStrings.get("model"), false,
				changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				changedHandler);
		descriptionItem.setColSpan(2);
		categoryItem = new GHAMaterialCategoryPickTreeItem(
				GHAStrings.get("category"), form.getItemW());

	}

	public MaterialBrandForm() {
		super();
		form.setItems(nameItem, categoryItem, codeItem, externalCodeItem,
				typeItem, brandItem, modelItem, new GHASpacerItem(1),
				descriptionItem);
		addMember(form);
		fill();
	}

	public MaterialBrandForm(String value) {
		this();
		codeItem.setValue(value);
		this.cleanCodeItem = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#activate()
	 */
	@Override
	public void activate() {
		toggleForm(true);
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
		listeners.add(listener);

	}

	@Override
	public void clear() {
		super.clear();

		if (this.cleanCodeItem)
			codeItem.clearValue();
		categoryItem.clearValue();

		externalCodeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();

		if (!typeItem.isDisabled()) {
			typeItem.clearValue();
		}

		brandItem.clearValue();
		this.form.clearErrors(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#deactivate()
	 */
	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private MaterialBrand extract() {
		if (!hasUnCommittedChanges)
			return null;

		final Material material = new Material();
		final MaterialBrand materialBrand = new MaterialBrand();

		if (brandItem.getValue() != null) {
			materialBrand.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString())));
		}

		material.setCode(codeItem.getValueAsString());
		material.setExternalCode(externalCodeItem.getValueAsString());
		material.setName(nameItem.getValueAsString());
		material.setDescription(descriptionItem.getValueAsString());
		if (typeItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeItem
					.getValueAsString()));
		material.setModel(modelItem.getValueAsString());
		if (categoryItem.getValue() != null) {
			material.setMaterialCategory(new MaterialCategory(categoryItem
					.getValue().toString()));
		}

		final Set<ConstraintViolation<Material>> violations = validator
				.validate(material);
		materialBrand.setMaterial(material);

		final Set<ConstraintViolation<MaterialBrand>> violations1 = validator
				.validate(materialBrand);

		if (form.validate() && violations.isEmpty() && violations1.isEmpty())
			return materialBrand;
		else {
			final List<String> violationsList = new ArrayList<String>();
			for (final Iterator<ConstraintViolation<Material>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}

			for (final Iterator<ConstraintViolation<MaterialBrand>> it = violations1
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());

			GHAAlertManager.alert(violationsList.get(0));
		}
		return null;
	}

	/**
	 * 
	 */
	private void fill() {
		typeItem.setValueMap(MaterialTypeEnum.toValueMap());
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
		for (final MaterialBrandSelectionListener listener : listeners)
			listener.select(materialBrand);
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
		form.resize();
		categoryItem.resizeWidth(form.getItemW());
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
		listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#save(org.fourgeeks
	 * .gha.webclient.client.UI.GHAAsyncCallback)
	 */
	@Override
	public void save(final GHAAsyncCallback<MaterialBrand> callback) {
		final MaterialBrand materialBrand = extract();
		if (materialBrand != null) {
			MaterialBrandModel.save(materialBrand,
					new GHAAsyncCallback<MaterialBrand>() {

				@Override
				public void onSuccess(MaterialBrand result) {
					hasUnCommittedChanges = false;
					notifyMaterialBrand(result);
					clear();
					if (callback != null)
						callback.onSuccess(result);
				}
			});
		}

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
		this.originalEntity = entity;
		final Material material = entity.getMaterial();

		codeItem.setValue(material.getCode());
		externalCodeItem.setValue(material.getExternalCode());
		nameItem.setValue(material.getName());
		descriptionItem.setValue(material.getDescription());

		if (material.getType() != null)
			typeItem.setValue(material.getType().name());

		if (typeItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeItem
					.getValueAsString()));
		material.setModel(modelItem.getValueAsString());

		brandItem.setValue(entity.getBrand().getId());
	}

	public void setType(MaterialTypeEnum type) {
		typeItem.setValue(type.name());
		typeItem.setDisabled(true);
	}

	private void toggleForm(boolean activate) {
		codeItem.disable();

		externalCodeItem.setDisabled(!activate);
		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		modelItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		brandItem.setDisabled(!activate);
	}

	@Override
	public void undo() {
		if (originalEntity == null)
			clear();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;
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
