package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
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
 * @author alacret
 * 
 */
public class MaterialCategoryForm extends GHAVerticalLayout implements
		MaterialCategorySelectionProducer {

	private List<MaterialCategorySelectionListener> listeners;
	private GHATextItem codeItem, externalCodeItem, nameItem, modelItem;
	private GHATextAreaItem descriptionItem;

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
	private MaterialCategory updateEntity;

	{
		listeners = new ArrayList<MaterialCategorySelectionListener>();
		nameItem = new GHATextItem(GHAStrings.get("name"), 900, false,
				changedHandler);
		nameItem.setColSpan(2);
		codeItem = new GHACodeItem(true, 450, changedHandler);
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

	private void fill() {
		typeItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	/**
	 * 
	 */
	public void save() {
		save(null);
	}

	/**
	 * @return
	 */
	private MaterialCategory extract() {
		final MaterialCategory materialCategory = new MaterialCategory();
		materialCategory.setCode(codeItem.getValueAsString());
		materialCategory.setExternalCode(externalCodeItem.getValueAsString());
		materialCategory.setName(nameItem.getValueAsString());
		materialCategory.setDescription(descriptionItem.getValueAsString());
		if (typeItem.getValue() != null)
			materialCategory.setType(MaterialTypeEnum.valueOf(typeItem
					.getValueAsString()));
		materialCategory.setModel(modelItem.getValueAsString());
		Set<ConstraintViolation<MaterialCategory>> violations = validator
				.validate(materialCategory);
		if (violations.isEmpty())
			return materialCategory;
		if (form.validate() && violations.isEmpty())
			return materialCategory;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaterialCategory>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			GHANotification.alert(violationsList);
		}
		return null;
	}

	protected void cancel() {
		codeItem.clearValue();
		externalCodeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
	}

	@Override
	public void notifyMaterialCategory(MaterialCategory material) {
		for (MaterialCategorySelectionListener listener : listeners)
			listener.select(material);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);

	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);

	}

	/**
	 * @param ghaAsyncCallback
	 */
	public void save(final GHAAsyncCallback<MaterialCategory> ghaAsyncCallback) {
		final MaterialCategory materialCategory = extract();
		if (materialCategory != null)
			MaterialCategoryModel.save(materialCategory,
					new GHAAsyncCallback<MaterialCategory>() {

						@Override
						public void onSuccess(MaterialCategory result) {
							hasUnCommittedChanges = false;
							notifyMaterialCategory(result);
							cancel();
							if (ghaAsyncCallback != null)
								ghaAsyncCallback.onSuccess(materialCategory);
						}
					});
	}

	/**
	 * @return the hasUnCommittedChanges
	 */
	public boolean hasUnCommittedChanges() {
		return hasUnCommittedChanges;
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

	/**
	 * @param materialCategory
	 */
	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.updateEntity = materialCategory;

		codeItem.setValue(materialCategory.getCode());
		externalCodeItem.setValue(materialCategory.getExternalCode());
		nameItem.setValue(materialCategory.getName());
		descriptionItem.setValue(materialCategory.getDescription());

		if (materialCategory.getType() != null)
			typeItem.setValue(materialCategory.getType().name());

		if (typeItem.getValue() != null)
			materialCategory.setType(MaterialTypeEnum.valueOf(typeItem
					.getValueAsString()));
		materialCategory.setModel(modelItem.getValueAsString());
	}
}
