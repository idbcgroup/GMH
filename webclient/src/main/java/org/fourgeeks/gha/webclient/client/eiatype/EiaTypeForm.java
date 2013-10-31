package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAComboboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;

import com.google.gwt.user.client.Window;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot
 * 
 */
public class EiaTypeForm extends GHAVerticalLayout implements
		EiaTypeSelectionProducer {
	private GHACodeItem codeItem;
	private GHATextItem nameItem, modelItem, eiaUmdnsItem;
	private GHATextAreaItem descriptionItem;
	private GHATextAreaItem useDescriptionItem;
	private GHASelectItem mobilityItem;
	private GHAEiaTypeTypeSelectItem typeItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private GHAComboboxItem<Brand> brandItem;
	private GHAComboboxItem<Manufacturer> manItem;

	private List<EIATypeSelectionListener> listeners;

	private DynamicForm form;

	/**
	 * Used only for update purposes, to hold id and such values
	 */
	private EiaType updateEntity;
	private boolean hasUnCommittedChanges = false;
	private ChangedHandler changedHandler = new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {
			hasUnCommittedChanges = true;
		}
	};
	private Validator validator;
	{
		codeItem = new GHACodeItem(true, 300, changedHandler);
		codeItem.disable();

		nameItem = new GHATextItem(GHAStrings.get("name"), 300, true,
				changedHandler);
		typeItem = new GHAEiaTypeTypeSelectItem(300, true, changedHandler);
		//
		subTypeItem = new GHAEiaTypeSubTypeSelectItem(300, changedHandler);
		eiaUmdnsItem = new GHATextItem("EIAUMDNS", 300, false, changedHandler);
		eiaUmdnsItem.setLength(16);
		modelItem = new GHATextItem(GHAStrings.get("model"), 300, false,
				changedHandler);
		modelItem.setLength(20);
		//
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				900, changedHandler);
		descriptionItem.setColSpan(3);
		useDescriptionItem = new GHATextAreaItem(GHAStrings.get("use"), 900,
				changedHandler);
		useDescriptionItem.setColSpan(3);
		//
		manItem = new GHAComboboxItem<Manufacturer>(
				GHAStrings.get("manufacturer"), 300, changedHandler);
		brandItem = new GHAComboboxItem<Brand>(GHAStrings.get("brand"), 300,
				changedHandler);
		mobilityItem = new GHASelectItem(GHAStrings.get("mobility"), 300, true,
				changedHandler);
		//
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<EIATypeSelectionListener>();
		form = new DynamicForm();
	}

	/**
	 * 
	 */
	public EiaTypeForm() {
		final HLayout gridPanel = new HLayout();

		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);

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

		form.setItems(codeItem, nameItem, typeItem, subTypeItem, eiaUmdnsItem,
				modelItem,

				mobilityItem, manItem, brandItem,

				descriptionItem, useDescriptionItem);

		gridPanel.addMembers(form, new LayoutSpacer());
		addMember(gridPanel);
		fillMans(true);
		fillExtras();

	}

	/**
	 * Activate the form
	 * 
	 */
	public void activate() {
		toggleForm(true);
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

	@Override
	public boolean canBeClosen() {
		return !hasUnCommittedChanges;
	}

	@Override
	public boolean canBeHidden() {
		return !hasUnCommittedChanges;
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

	@Override
	public void close() throws UnavailableToCloseException {

	}

	/**
	 * Deactivate the form
	 * 
	 */
	public void deactivate() {
		toggleForm(false);
	}

	private EiaType extract(boolean update) {
		final EiaType eiaType = new EiaType();
		if (update)
			eiaType.setCode(this.updateEntity.getCode());

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
		Set<ConstraintViolation<EiaType>> violations = null;
		try {
			violations = validator.validate(eiaType);
		} catch (Exception e) {
			Window.alert(e.getMessage());
			e.printStackTrace();
		}
		if (form.validate() && violations.isEmpty())
			return eiaType;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<EiaType>> it = violations
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
			GHANotification.alert(violationsList);
		}
		return null;
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

	/**
	 * @return the hasUnCommittedChanges
	 */
	public boolean hasUnCommittedChanges() {
		return hasUnCommittedChanges;
	}

	// Producer stuff
	@Override
	public void notifyEiaType(EiaType eiaType) {
		// notify user
		GHANotification.alert("eiatype-save-success");
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
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

	/**
	 * 
	 */
	public void save() {
		save(null);
	}

	/**
	 * @param callback
	 */
	public void save(final GHAAsyncCallback<EiaType> callback) {
		EiaType eiaType = extract(false);

		if (eiaType != null)
			EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {

				@Override
				public void onSuccess(EiaType result) {
					hasUnCommittedChanges = false;
					notifyEiaType(result);
					cancel();
					// reload manufacturers, possibly one new
					fillMans(true);
					brandItem.clearValue();
					brandItem.disable();
					if (callback != null)
						callback.onSuccess(result);
				}
			});
	}

	/**
	 * @param eiaType
	 */
	public void setEiaType(EiaType eiaType) {
		// reload manufacturer select, in order to avoid issues with new brands
		// or manufacturers
		fillMans(true);

		this.updateEntity = eiaType;
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

	/**
	 * @param hasUnCommittedChanges
	 *            the hasUnCommittedChanges to set
	 */
	public void setHasUnCommittedChanges(boolean hasUnCommittedChanges) {
		this.hasUnCommittedChanges = hasUnCommittedChanges;
	}

	/**
	 * @param activate
	 */
	private void toggleForm(boolean activate) {
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

		// this is to keep the code item disabled while update
		if (updateEntity == null) // this is suposed to happen only on addform
			codeItem.setDisabled(!activate);
		else
			codeItem.disable();

		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		modelItem.setDisabled(!activate);
		useDescriptionItem.setDisabled(!activate);
		eiaUmdnsItem.setDisabled(!activate);
		mobilityItem.setDisabled(!activate);
		typeItem.setDisabled(!activate);
		subTypeItem.setDisabled(!activate);
	}

	/**
	 * This method returns the form to the original entity or clean the form
	 * if(updateEntity == null) cancel() else select(updateEntity)
	 */
	public void undo() {
		if (updateEntity == null)
			cancel();
		else
			this.setEiaType(updateEntity);
		hasUnCommittedChanges = false;
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
					hasUnCommittedChanges = false;
					notifyEiaType(result);
				}
			});
		}
	}

}
