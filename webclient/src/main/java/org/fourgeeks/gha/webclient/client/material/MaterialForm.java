package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaterialForm extends VLayout implements MaterialSelectionProducer {

	private List<MaterialSelectionListener> listeners;
	private GHATextItem codeItem, extCodeItem, nameItem, descriptionItem,
			modelItem;
	private GHASelectItem typeItem, externalProviderItem;
	private Validator validator;

	{
		listeners = new ArrayList<MaterialSelectionListener>();
		codeItem = new GHATextItem("Código", 150);
		extCodeItem = new GHATextItem("Código externo", 150);
		nameItem = new GHATextItem("Nombre", 150);
		descriptionItem = new GHATextItem("Descripción", 480);
		descriptionItem.setColSpan(3);
		typeItem = new GHASelectItem("Tipo", 150);
		modelItem = new GHATextItem("Modelo", 150);
		externalProviderItem = new GHASelectItem("Proveedor externo", 150);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 
	 */
	public MaterialForm() {
		super();
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(codeItem, extCodeItem, nameItem, descriptionItem,
				typeItem, modelItem, externalProviderItem);
		addMember(form);
		fill();

	}

	private void fill() {
		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {

					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider externalProvider : result)
							valueMap.put(
									String.valueOf(externalProvider.getId()),
									externalProvider.getInstitution().getName());
						externalProviderItem.setValueMap(valueMap);

					}
				});

		typeItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	void save() {
		final Material material = extract();
		if (material != null)
			MaterialModel.save(material, new GHAAsyncCallback<Material>() {

				@Override
				public void onSuccess(Material result) {
					select(result);
					cancel();
				}
			});

	}

	/**
	 * @return
	 */
	private Material extract() {
		final Material material = new Material();
		material.setCode(codeItem.getValueAsString());
		material.setExtCode(extCodeItem.getValueAsString());
		material.setName(nameItem.getValueAsString());
		material.setDescription(descriptionItem.getValueAsString());
		if (typeItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeItem
					.getValueAsString()));
		material.setModel(modelItem.getValueAsString());
		if (externalProviderItem.getValue() != null)
			material.setExternalProvider(new ExternalProvider(Integer
					.parseInt(externalProviderItem.getValueAsString())));

		Set<ConstraintViolation<Material>> violations = validator
				.validate(material);

		if (violations.isEmpty())
			return material;

		GHANotification.alert(violations.iterator().next().getMessage());

		return null;
	}

	protected void cancel() {
		codeItem.clearValue();
		extCodeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
	}

	protected void select(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);

	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);

	}

}
