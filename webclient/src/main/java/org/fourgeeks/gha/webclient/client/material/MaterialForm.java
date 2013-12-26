package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;

/**
 * @author emiliot
 * 
 */
public class MaterialForm extends GHAForm<Material> implements
		MaterialSelectionProducer {
	private List<MaterialSelectionListener> listeners;
	private GHATextItem codeItem, externalCodeItem, nameItem, modelItem;
	private GHATextAreaItem descriptionItem;
	private GHABrandSelectItem brandItem;

	private GHASelectItem typeItem;
	private GHADynamicForm form;
	
	{
		listeners = new ArrayList<MaterialSelectionListener>();
		nameItem = new GHATextItem(GHAStrings.get("name"), false,	changedHandler);
		nameItem.setColSpan(2);
		codeItem = new GHACodeTextItem(true, changedHandler);
		externalCodeItem = new GHATextItem(GHAStrings.get("external-code"), false, changedHandler);
		typeItem = new GHASelectItem(GHAStrings.get("type"), true,	changedHandler);
		brandItem = new GHABrandSelectItem();

		modelItem = new GHATextItem(GHAStrings.get("model"), false,changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"), changedHandler);
		descriptionItem.setColSpan(2);
				
		form = new GHADynamicForm(3,FormType.NORMAL_FORM);

	}

	public MaterialForm() {
		super();
		form.setItems(nameItem, new GHASpacerItem(),
				codeItem, externalCodeItem,new GHASpacerItem(1),
				typeItem,brandItem,new GHASpacerItem(),
				modelItem,new GHASpacerItem(2),
				descriptionItem);
		addMember(form);
		fill();
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);

	}

	protected void cancel() {
		codeItem.clearValue();
		externalCodeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
		if (!typeItem.isDisabled()) {
			typeItem.clearValue();
		}
		brandItem.clearValue();
	}

	/**
	 * @return
	 */
	private Material extract() {
		final Material material = new Material();

		if (brandItem.getValue() != null) {
			material.setBrand(new Brand(Integer.valueOf(brandItem
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

		Set<ConstraintViolation<Material>> violations = validator
				.validate(material);

		if (form.validate() && violations.isEmpty())
			return material;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<Material>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}

			GHANotification.alert(violationsList);
		}
		return null;
	}

	/**
	 * 
	 */
	private void fill() {
		typeItem.setValueMap(MaterialTypeEnum.toValueMap());
	}


	@Override
	public void notifyMaterial(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);

	}

	/**
	 * @param ghaAsyncCallback
	 */
	public void save(final GHAAsyncCallback<Material> ghaAsyncCallback) {
		final Material material = extract();
		if (material != null)
			MaterialModel.save(material, new GHAAsyncCallback<Material>() {

				@Override
				public void onSuccess(Material result) {
					hasUnCommittedChanges = false;
					notifyMaterial(result);
					cancel();
					if (ghaAsyncCallback != null)
						ghaAsyncCallback.onSuccess(material);
				}
			});
	}

	/**
	 * @param materialCategory
	 */
	public void set(Material material) {
		this.originalEntity = material;

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
	}

	/**
	 * @param type
	 */
	public void setType(MaterialTypeEnum type) {
		typeItem.setValue(type.name());
		typeItem.setDisabled(true);
	}

	/**
	 * This method returns the form to the original entity or clean the form
	 * if(originalEntity == null) cancel() else select(originalEntity)
	 */
	public void undo() {
		if (originalEntity == null)
			cancel();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		form.resize();
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GHAAsyncCallback<Material> callback) {
		// TODO Auto-generated method stub
		
	}

	
}
