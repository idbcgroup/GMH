package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAComboboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EiaTypeForm extends VLayout implements EiaTypeSelectionProducer {
	private GHATextItem codeItem, nameItem, modelItem, descriptionItem,
			useDescriptionItem, eiaUmdnsItem;
	private GHASelectItem mobilityItem, typeItem, subTypeItem;
	private GHAComboboxItem<Brand> brandItem;
	private GHAComboboxItem<Manufacturer> manItem;

	private List<EIATypeSelectionListener> listeners;

	/**
	 * Used only for update purposes, to hold id and such values
	 */
	private EiaType updateEiaType;

	private Validator validator;
	{
		codeItem = new GHATextItem("Código", 150);
		codeItem.setRequired(true);
		codeItem.setLength(20);
		codeItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		nameItem = new GHATextItem("Nombre", 150);
		nameItem.setRequired(true);
		nameItem.setLength(255);
		modelItem = new GHATextItem("Modelo", 150);
		modelItem.setLength(20);
		descriptionItem = new GHATextItem("Descripción", 480);
		descriptionItem.setColSpan(3);
		descriptionItem.setLength(255);
		useDescriptionItem = new GHATextItem("Uso", 480);
		useDescriptionItem.setColSpan(3);
		useDescriptionItem.setLength(255);
		eiaUmdnsItem = new GHATextItem("EIAUMDNS", 150);
		eiaUmdnsItem.setLength(16);
		eiaUmdnsItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		manItem = new GHAComboboxItem<Manufacturer>("Fabricante", 150);
		brandItem = new GHAComboboxItem<Brand>("Marca", 150);
		mobilityItem = new GHASelectItem("Movilidad", 150);
		mobilityItem.setRequired(true);
		typeItem = new GHASelectItem("Tipo", 150);
		typeItem.setRequired(true);
		subTypeItem = new GHASelectItem("Subtipo", 150);

		validator = Validation.buildDefaultValidatorFactory().getValidator();

		listeners = new ArrayList<EIATypeSelectionListener>();
	}

	public EiaTypeForm() {
		final HLayout gridPanel = new HLayout();

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);

		// disable the brand select if no manufacturer is selected
		brandItem.disable();

		// set the handler for selected manufacturer
		manItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String manItemValue = manItem.getValueAsString();
				if (manItemValue == null || manItemValue.isEmpty()) {
					brandItem.disable();
					brandItem.setValue("");
				} else {
					if (manItemValue.matches("[1-9]+\\d*")) {
						fillBrands(new Manufacturer(Integer
								.valueOf(manItemValue), null));
					}
					brandItem.setValue("");
					brandItem.enable();
				}
			}
		});

		form.setItems(codeItem, nameItem, typeItem, subTypeItem,
				descriptionItem, mobilityItem, useDescriptionItem,
				eiaUmdnsItem, manItem, brandItem, modelItem);

		gridPanel.addMembers(form, new LayoutSpacer());
		addMember(gridPanel);
		fillMans(true);
		fillExtras();

	}

	private void fillExtras() {
		// types
		typeItem.setValueMap(EiaTypeEnum.toValueMap());
		// subtypes
		subTypeItem.setValueMap(EiaSubTypeEnum.toValueMap());
		// mobility
		mobilityItem.setValueMap(EiaMobilityEnum.toValueMap());
	}

	private void fillMans(boolean forceFromServer) {
		GHACache.INSTANCE.getManufacturesrs(
				new GHAAsyncCallback<List<Manufacturer>>() {

					@Override
					public void onSuccess(List<Manufacturer> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Manufacturer manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getName());
						manItem.setValueMap(valueMap);

					}
				}, forceFromServer);
	}

	private void fillBrands(Manufacturer manufacturer) {
		BrandModel.findByManufacturer(manufacturer,
				new GHAAsyncCallback<List<Brand>>() {

					@Override
					public void onSuccess(List<Brand> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Brand brand : result)
							valueMap.put(brand.getId() + "", brand.getName());
						brandItem.setValueMap(valueMap);
					}

				});
	}

	private void fillBrands(boolean forceFromServer) {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		}, forceFromServer);

	}

	/**
	 * 
	 */
	public void save() {
		EiaType eiaType = extract(false);

		if (eiaType != null)
			EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {

				@Override
				public void onSuccess(EiaType result) {
					notifyEiaType(result);
					cancel();

					// reload manufacturers, possibly one new
					fillMans(true);
					brandItem.clearValue();
					brandItem.disable();
				}
			});
	}

	/**
	 * 
	 */
	public void update() {
		EiaType eiaType = extract(true);

		if (eiaType != null) {
			EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {

				@Override
				public void onSuccess(EiaType result) {
					notifyEiaType(result);
				}
			});
		}
	}

	/**
	 * 
	 */
	public void cancel() {
		brandItem.clearValue();
		brandItem.disable();
		manItem.clearValue();
		codeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
		useDescriptionItem.clearValue();
		eiaUmdnsItem.clearValue();
		mobilityItem.clearValue();
		typeItem.clearValue();
		subTypeItem.clearValue();
	}

	public void activateForm(boolean activate) {
		if (activate) {
			if (manItem.getValue() != null
					&& !manItem.getValueAsString().isEmpty()) {
				brandItem.setDisabled(false);
			} else {
				brandItem.setDisabled(true);
			}
		} else
			brandItem.setDisabled(!activate);
		manItem.setDisabled(!activate);
		codeItem.setDisabled(!activate);
		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		modelItem.setDisabled(!activate);
		useDescriptionItem.setDisabled(!activate);
		eiaUmdnsItem.setDisabled(!activate);
		mobilityItem.setDisabled(!activate);
		typeItem.setDisabled(!activate);
		subTypeItem.setDisabled(!activate);
	}

	private EiaType extract(boolean update) {
		final EiaType eiaType = new EiaType();
		if (update)
			eiaType.setCode(this.updateEiaType.getCode());

		if (brandItem.getValue() != null) {
			if (brandItem.getValueAsString().matches("[1-9]+\\d*")) {
				eiaType.setBrand(new Brand(Integer.valueOf(brandItem
						.getValueAsString()), null));
			} else {
				eiaType.setBrand(new Brand(brandItem.getValueAsString()));
			}
		}
		if (manItem.getValue() != null) {
			if (manItem.getValueAsString().matches("[1-9]+\\d*")) {
				eiaType.getBrand().setManufacturer(
						new Manufacturer(Integer.valueOf(manItem
								.getValueAsString()), null));
			} else {
				eiaType.getBrand().setManufacturer(
						new Manufacturer(manItem.getValueAsString()));
			}
		}

		if (!update)
			eiaType.setCode(codeItem.getValueAsString());
		eiaType.setName(nameItem.getValueAsString());
		eiaType.setDescription(descriptionItem.getValueAsString());
		eiaType.setModel(modelItem.getValueAsString());
		eiaType.setUseDescription(useDescriptionItem.getValueAsString());
		eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
					.getValueAsString()));
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));

		Set<ConstraintViolation<EiaType>> violations = validator
				.validate(eiaType);
		if (violations.isEmpty())
			return eiaType;
		else {
			GHANotification.alert(GHAStrings.get(violations.iterator().next()
					.getMessage()));
			return null;
		}
	}

	public void setEiaType(EiaType eiaType) {
		// reload manufacturer select, in order to avoid issues with new brands
		// or manufacturers
		fillMans(true);

		this.updateEiaType = eiaType;
		if (eiaType.getBrand() != null) {
			if (eiaType.getBrand().getManufacturer() != null) {
				manItem.setValue(eiaType.getBrand().getManufacturer().getId());

				// fill brands by manufacturer
				fillBrands(eiaType.getBrand().getManufacturer());
			} else {
				// this shouldnt be happening, because if the eiatype has a
				// brand this brand should have manufacturer
				fillBrands(true);
			}
			// set brand value
			brandItem.setValue(eiaType.getBrand().getId());
		}
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());
		descriptionItem.setValue(eiaType.getDescription());
		modelItem.setValue(eiaType.getModel());
		useDescriptionItem.setValue(eiaType.getUseDescription());
		eiaUmdnsItem.setValue(eiaType.getEiaUmdns());
		mobilityItem.setValue(eiaType.getMobility().name());
		typeItem.setValue(eiaType.getType().name());
		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());
		// showPhotographics(eiaType);
	}

	// Producer stuff
	private void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * addEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * removeEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);

	}

}
