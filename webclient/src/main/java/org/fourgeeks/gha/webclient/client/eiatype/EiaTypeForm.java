package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAComboboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot
 * 
 */
public class EiaTypeForm extends GHAForm<EiaType> implements
		EiaTypeSelectionProducer {

	protected GHADynamicForm form;
	private GHACodeTextItem codeItem;
	private GHATextItem nameItem, modelItem, eiaUmdnsItem;
	private GHATextAreaItem descriptionItem;
	private GHATextAreaItem useDescriptionItem;
	private GHASelectItem mobilityItem;
	private GHAEiaTypeTypeSelectItem typeItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private GHAComboboxItem<Brand> brandItem;
	private GHAComboboxItem<Manufacturer> manItem;

	private List<EIATypeSelectionListener> listeners;

	{
		codeItem = new GHACodeTextItem(true, changedHandler);

		nameItem = new GHATextItem(GHAStrings.get("name"), true, changedHandler);
		typeItem = new GHAEiaTypeTypeSelectItem(true, changedHandler);
		//
		subTypeItem = new GHAEiaTypeSubTypeSelectItem(changedHandler);
		eiaUmdnsItem = new GHATextItem("EIAUMDNS", false, changedHandler);
		eiaUmdnsItem.setLength(16);
		modelItem = new GHATextItem(GHAStrings.get("model"), false,
				changedHandler);
		modelItem.setLength(20);
		//
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				changedHandler);
		descriptionItem.setColSpan(3);
		useDescriptionItem = new GHATextAreaItem(GHAStrings.get("use"),
				changedHandler);
		useDescriptionItem.setColSpan(3);
		//
		manItem = new GHAComboboxItem<Manufacturer>(
				GHAStrings.get("manufacturer"), changedHandler);
		brandItem = new GHAComboboxItem<Brand>(GHAStrings.get("brand"),
				changedHandler);
		mobilityItem = new GHASelectItem(GHAStrings.get("mobility"), true,
				changedHandler);
		//
		listeners = new ArrayList<EIATypeSelectionListener>();

		form = new GHADynamicForm(4,FormType.NORMAL_FORM);
	}

	/**
	 * 
	 */
	public EiaTypeForm() {
		super();
		final HLayout gridPanel = new HLayout();
		// disable the brand select if no manufacturer is selected
		brandItem.disable();
		brandItem.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				String manItemValue = manItem.getValueAsString();
				if (manItemValue.matches("[1-9]+\\d*")) {
					fillBrands(new Manufacturer(Integer.valueOf(manItemValue),
							null));
				}
				brandItem.setValue("");

			}
		});
		// set the handler for selected manufacturer
		manItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String manItemValue = manItem.getValueAsString();
				if (manItemValue == null || manItemValue.isEmpty()) {
					brandItem.disable();
					brandItem.setValue("");
				} else {
					brandItem.enable();
				}
			}
		});

		form.setItems(codeItem, nameItem, typeItem, new GHASpacerItem(),
				subTypeItem, eiaUmdnsItem, modelItem, new GHASpacerItem(),
				mobilityItem, manItem, brandItem, new GHASpacerItem(),
				descriptionItem, new GHASpacerItem(), useDescriptionItem);

		gridPanel.addMembers(form, new LayoutSpacer());
		addMember(gridPanel);
		fillMans(true);
		fillExtras();

	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);

	}

	@Override
	public void clear() {
		super.clear();
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
	public void deactivate() {
		toggleForm(false);
	}

	private EiaType extract(boolean update) {
		final EiaType eiaType = new EiaType();
		if (update)
			eiaType.setCode(this.originalEntity.getCode());

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
		violations = validator.validate(eiaType);
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

	private void fillBrands(final Brand brand) {
		Manufacturer manufacturer = brand.getManufacturer();
		BrandModel.findByManufacturer(manufacturer,
				new GHAAsyncCallback<List<Brand>>() {

					@Override
					public void onSuccess(List<Brand> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Brand brand : result)
							valueMap.put(brand.getId() + "", brand.getName());
						brandItem.setValueMap(valueMap);
						brandItem.setValue(brand.getId());
					}

				});

	}

	private void fillBrands(Manufacturer manufacturer) {
		final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		brandItem.setValueMap(valueMap);
		brandItem.redraw();

		BrandModel.findByManufacturer(manufacturer,
				new GHAAsyncCallback<List<Brand>>() {

					@Override
					public void onSuccess(List<Brand> result) {
						for (Brand brand : result)
							valueMap.put(brand.getId() + "", brand.getName());
						brandItem.setValueMap(valueMap);
						brandItem.redraw();
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
						manItem.redraw();
					}
				}, forceFromServer);
	}

	private void fillMans(final Manufacturer manufacturer) {
		GHACache.INSTANCE.getManufacturesrs(
				new GHAAsyncCallback<List<Manufacturer>>() {

					@Override
					public void onSuccess(List<Manufacturer> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Manufacturer manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getName());
						manItem.setValueMap(valueMap);
						manItem.setValue(manufacturer.getId());
					}
				}, true);
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);

	}

	@Override
	public void save(final GHAAsyncCallback<EiaType> callback) {
		EiaType eiaType = extract(false);

		if (eiaType == null)
			return;
		EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType result) {
				hasUnCommittedChanges = false;
				notifyEiaType(result);
				clear();
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
	@Override
	public void set(EiaType eiaType) {
		this.originalEntity = eiaType;
		if (eiaType.getBrand() != null) {
			fillBrands(eiaType.getBrand());
			fillMans(eiaType.getBrand().getManufacturer());
		} else {
			manItem.clearValue();
			brandItem.clearValue();
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
		if (originalEntity == null) // this is suposed to happen only on addform
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

	@Override
	public void update(final GHAAsyncCallback<EiaType> callback) {
		EiaType eiaType = extract(true);

		if (eiaType == null)
			return;

		EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType result) {
				hasUnCommittedChanges = false;
				notifyEiaType(result);
				if (callback != null)
					callback.onSuccess(result);
			}
		});
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}
}
