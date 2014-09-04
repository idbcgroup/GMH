package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class MaterialCategoryForm extends GHAFormLayout implements
		MaterialCategorySelectionProducer {

	private List<MaterialCategorySelectionListener> listeners;
	private GHATextItem codeItem, externalCodeItem, nameItem, modelItem;
	private GHATextAreaItem descriptionItem;

	private GHASelectItem typeItem;
	private Validator validator;
	private final DynamicForm form;
	private boolean hasUnCommittedChanges = false;
	private final ChangedHandler changedHandler = new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {
			hasUnCommittedChanges = true;
		}
	};
	private ServicesResourceCategory updateEntity;

	{
		listeners = new ArrayList<MaterialCategorySelectionListener>();
		nameItem = new GHATextItem(GHAStrings.get("name"), 900, false,
				changedHandler);
		nameItem.setColSpan(2);
		codeItem = new GHACodeTextItem(true, 450, changedHandler);
		externalCodeItem = new GHATextItem(GHAStrings.get("external-code"),
				450, false, changedHandler);
		typeItem = new GHASelectItem(GHAStrings.get("type"), 450, true,
				changedHandler);
		modelItem = new GHATextItem(GHAStrings.get("model"), 450, false,
				changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				900, changedHandler);
		descriptionItem.setColSpan(2);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 
	 */
	public MaterialCategoryForm() {
		super();
		form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);
		form.setItems(nameItem, codeItem, externalCodeItem, typeItem,
				modelItem, descriptionItem);
		addMember(form);
		fill();

	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
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
	}

	/**
	 * @return
	 */
	private ServicesResourceCategory extract() {
		final ServicesResourceCategory servicesResourceCategory = new ServicesResourceCategory();
		servicesResourceCategory.setCode(codeItem.getValueAsString());
		// materialCategory.setExternalCode(externalCodeItem.getValueAsString());
		// materialCategory.setName(nameItem.getValueAsString());
		// materialCategory.setDescription(descriptionItem.getValueAsString());
		// if (typeItem.getValue() != null)
		// materialCategory.setType(MaterialTypeEnum.valueOf(typeItem
		// .getValueAsString()));
		// materialCategory.setModel(modelItem.getValueAsString());
		final Set<ConstraintViolation<ServicesResourceCategory>> violations = validator
				.validate(servicesResourceCategory);
		if (violations.isEmpty())
			return servicesResourceCategory;
		if (form.validate() && violations.isEmpty())
			return servicesResourceCategory;
		else {
			final List<String> violationsList = new ArrayList<String>();
			for (final Iterator<ConstraintViolation<ServicesResourceCategory>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			// GHAAlertManager.alert(violationsList);
			GHAErrorMessageProcessor.alert(violationsList.get(0));
		}
		return null;
	}

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
	public void notifyMaterialCategory(ServicesResourceCategory material) {
		for (final MaterialCategorySelectionListener listener : listeners)
			listener.select(material);
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
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
	public void save(final GHAAsyncCallback<ServicesResourceCategory> ghaAsyncCallback) {
		final ServicesResourceCategory servicesResourceCategory = extract();
		if (servicesResourceCategory != null)
			MaterialCategoryModel.save(servicesResourceCategory,
					new GHAAsyncCallback<ServicesResourceCategory>() {

						@Override
						public void onSuccess(ServicesResourceCategory result) {
							hasUnCommittedChanges = false;
							notifyMaterialCategory(result);
							cancel();
							if (ghaAsyncCallback != null)
								ghaAsyncCallback.onSuccess(servicesResourceCategory);
						}
					});
	}

	/**
	 * @param servicesResourceCategory
	 */
	public void setMaterialCategory(ServicesResourceCategory servicesResourceCategory) {
		this.updateEntity = servicesResourceCategory;

		codeItem.setValue(servicesResourceCategory.getCode());
		// externalCodeItem.setValue(materialCategory.getExternalCode());
		// nameItem.setValue(materialCategory.getName());
		// descriptionItem.setValue(materialCategory.getDescription());
		//
		// if (materialCategory.getType() != null)
		// typeItem.setValue(materialCategory.getType().name());
		//
		// if (typeItem.getValue() != null)
		// materialCategory.setType(MaterialTypeEnum.valueOf(typeItem
		// .getValueAsString()));
		// materialCategory.setModel(modelItem.getValueAsString());
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
			this.setMaterialCategory(updateEntity);
		hasUnCommittedChanges = false;
	}
}
