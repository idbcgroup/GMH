package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot
 * 
 */
public class MaterialForm extends GHAVerticalLayout implements
		MaterialSelectionProducer {
	private List<MaterialSelectionListener> listeners;
	private GHATextItem codeItem, externalCodeItem, nameItem, modelItem;
	private GHATextAreaItem descriptionItem;
	private GHABrandSelectItem brandItem;

	private GHASelectItem typeItem;
	private Validator validator;
	private DynamicForm form;
	private boolean hasUnCommittedChanges = false;
	private ChangedHandler changedHandler = new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {
			hasUnCommittedChanges = true;
		}
	};
	private Material updateEntity;

	{
		listeners = new ArrayList<MaterialSelectionListener>();
		nameItem = new GHATextItem(GHAStrings.get("name"), 900, false,
				changedHandler);
		nameItem.setColSpan(2);
		codeItem = new GHACodeItem(true, 450, changedHandler);
		externalCodeItem = new GHATextItem(GHAStrings.get("external-code"),
				450, false, changedHandler);
		typeItem = new GHASelectItem(GHAStrings.get("type"), 300, true,
				changedHandler);
		brandItem = new GHABrandSelectItem(300);

		modelItem = new GHATextItem(GHAStrings.get("model"), 300, false,
				changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				900, changedHandler);
		descriptionItem.setColSpan(2);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public MaterialForm() {
		super();
		form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);
		form.setItems(nameItem, codeItem, externalCodeItem, typeItem,
				brandItem, modelItem, descriptionItem);
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

	/**
	 * @return the hasUnCommittedChanges
	 */
	public boolean hasUnCommittedChanges() {
		return hasUnCommittedChanges;
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
	 * 
	 */
	public void save() {
		save(null);
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
	public void setMaterial(Material material) {
		this.updateEntity = material;

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
	 * if(updateEntity == null) cancel() else select(updateEntity)
	 */
	public void undo() {
		if (updateEntity == null)
			cancel();
		else
			this.setMaterial(updateEntity);
		hasUnCommittedChanges = false;
	}

}
