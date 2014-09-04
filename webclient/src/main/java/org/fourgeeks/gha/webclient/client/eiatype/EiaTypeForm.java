package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAComboboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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
	private GHAEiaTypeCategoryPickTreeItem categoryItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private GHAComboboxItem<Brand> brandItem;
	private GHAComboboxItem<Manufacturer> manItem;
	private boolean cleanCodeItem = true;

	private final String violationsOrder[] = { "name-not-null",
			"eiatype-category-not-null", "mobility-not-null" };

	private List<EIATypeSelectionListener> listeners;

	{
		form = new GHADynamicForm(4, FormType.NORMAL_FORM);

		codeItem = new GHACodeTextItem(false, changedHandler);
		codeItem.disable();

		nameItem = new GHATextItem(GHAStrings.get("name"), true, changedHandler);
		categoryItem = new GHAEiaTypeCategoryPickTreeItem(
				GHAStrings.get("category"));
		categoryItem.setRequired(true);
		categoryItem.addChangedHandler(changedHandler);

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

		// Regex!

		nameItem.validateWords();
		nameItem.setTooltip(GHAStrings.get("eiatype-name-tooltip"));

		modelItem.validateSerial();
		modelItem.setTooltip(GHAStrings.get("eiatype-model-tooltip"));

		categoryItem.setTooltip(GHAStrings.get("eiatype-type-tooltip"));

		subTypeItem.setTooltip(GHAStrings.get("eiatype-subtype-tooltip"));
		mobilityItem.setTooltip(GHAStrings.get("eiatype-mobility-tooltip"));
		manItem.setTooltip(GHAStrings.get("eiatype-manufacturer-tooltip"));
		brandItem.setTooltip(GHAStrings.get("eiatype-brand-tooltip"));
		descriptionItem.setTooltip(GHAStrings
				.get("eiatype-description-tooltip"));

		useDescriptionItem.setTooltip(GHAStrings
				.get("eiatype-use-description-tooltip"));
		eiaUmdnsItem.validateAlphanumeric();
	}

	/**
	 * 
	 */
	public EiaTypeForm() {
		super();
		final HLayout gridPanel = new HLayout();
		// disable the brand select if no manufacturer is selected
		brandItem.disable();
		brandItem.setDefaultToFirstOption(true);

		// set the handler for selected manufacturer
		manItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				final String manItemValue = manItem.getValueAsString();
				brandItem.clearValue();

				if (manItemValue == null || manItemValue.isEmpty()) {
					brandItem.disable();
				} else {
					if (manItemValue.matches("[1-9]+\\d*")) {
						fillBrands(new Manufacturer(Integer
								.valueOf(manItemValue), null));
					} else {
						final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						brandItem.setValueMap(valueMap);
						brandItem.redraw();
					}
					brandItem.enable();
				}
			}
		});

		form.setItems(codeItem, nameItem, categoryItem, new GHASpacerItem(),
				subTypeItem, eiaUmdnsItem, modelItem, new GHASpacerItem(),
				mobilityItem, manItem, brandItem, new GHASpacerItem(),
				descriptionItem, new GHASpacerItem(), useDescriptionItem);

		gridPanel.addMembers(form, new LayoutSpacer());
		addMember(gridPanel);
		fillMans(true);
		fillExtras();

	}

	/**
	 * This constructor force the value of the codeItem to be the parameter
	 * 
	 * @param value
	 */
	public EiaTypeForm(String value) {
		this();
		codeItem.setValue(value);
		this.cleanCodeItem = false;
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

		if (this.cleanCodeItem)
			codeItem.clearValue();

		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
		useDescriptionItem.clearValue();
		eiaUmdnsItem.clearValue();
		mobilityItem.clearValue();
		categoryItem.clearValue();
		subTypeItem.clearValue();
		this.form.clearErrors(true);
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private EiaType extract(boolean update) {
		final List<String> violationsList = new ArrayList<String>();
		final EiaType eiaType = new EiaType();

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
		else
			eiaType.setCode(this.originalEntity.getCode());

		eiaType.setName(nameItem.getValueAsString());
		eiaType.setDescription(descriptionItem.getValueAsString());
		eiaType.setModel(modelItem.getValueAsString());
		eiaType.setUseDescription(useDescriptionItem.getValueAsString());
		eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
					.getValueAsString()));
		if (categoryItem.getValue() != null) {
			eiaType.setEiaTypeCategory(new ServiceResourceCategory(categoryItem
					.getValue().toString()));
		}
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));
		Set<ConstraintViolation<EiaType>> violations = validator
				.validate(eiaType);
		if (violations.isEmpty() && form.validate()) {
			return eiaType;
		} else {
			for (final Iterator<ConstraintViolation<EiaType>> it = violations
					.iterator(); it.hasNext();) {
				String next = it.next().getMessage();
				violationsList.add(next);
			}
			// GHAAlertManager.alert(violationsList);
			// GHAAlertManager.oldAlert(violationsList.get(0));

			for (String errorCode : violationsOrder) {
				if (violationsList.contains(errorCode)) {
					GHAErrorMessageProcessor.alert(errorCode);
					break;
				}
			}
		}
		return null;
	}

	private void fillBrands(final Brand brand) {
		final Manufacturer manufacturer = brand.getManufacturer();
		BrandModel.findByManufacturer(manufacturer,
				new GHAAsyncCallback<List<Brand>>() {

					@Override
					public void onSuccess(List<Brand> result) {
						final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (final Brand brand : result)
							valueMap.put(brand.getId() + "", brand.getName());
						brandItem.setValueMap(valueMap);
						brandItem.setValue(brand.getId());
					}

				});

	}

	private void fillBrands(Manufacturer manufacturer) {
		final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		BrandModel.findByManufacturer(manufacturer,
				new GHAAsyncCallback<List<Brand>>() {

					@Override
					public void onSuccess(List<Brand> result) {
						for (final Brand brand : result)
							valueMap.put(brand.getId() + "", brand.getName());
						brandItem.setValueMap(valueMap);
						brandItem.redraw();
					}

				});
	}

	private void fillExtras() {
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
						final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (final Manufacturer manufacturer : result)
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
						final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (final Manufacturer manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getName());
						manItem.setValueMap(valueMap);
						manItem.setValue(manufacturer.getId());
					}
				}, true);
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (final EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);

	}

	@Override
	public void save(final GHAAsyncCallback<EiaType> callback) {
		final EiaType eiaType = extract(false);

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
			brandItem.enable();
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
		categoryItem.setValue(eiaType.getEiaTypeCategory().getCode());
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
		// if (originalEntity == null) // this is suposed to happen only on
		// addform
		// codeItem.setDisabled(!activate);
		// else
		// codeItem.disable();
		codeItem.disable();

		nameItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		modelItem.setDisabled(!activate);
		useDescriptionItem.setDisabled(!activate);
		eiaUmdnsItem.setDisabled(!activate);
		mobilityItem.setDisabled(!activate);
		categoryItem.setDisabled(!activate);
		subTypeItem.setDisabled(!activate);
	}

	@Override
	public void update(final GHAAsyncCallback<EiaType> callback) {
		if (!hasUnCommittedChanges)
			return;
		final EiaType eiaType = extract(true);

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
}
