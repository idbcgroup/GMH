package org.fourgeeks.gha.webclient.client.eia;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;
//import org.fourgeeks.gha.domain.gar.BuildingLocation;
//import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
//import com.google.gwt.user.client.Window;

//import org.fourgeeks.gha.domain.enu.ItSystemEnum;

/**
 * @author alacret, vivi.torresg
 * 
 */
public class EIAForm extends VLayout implements EIATypeSelectionListener,
		EiaSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			purchaseOrderNumTextItem, purchaseInvoiceNumTextItem,
			workingAreaLocationCodeTextItem, facilityLocationCodeTextItem,
			adquisitionCostTextItem, adquisitionCostLocalTextItem,
			depreciationTimeTextItem, lifeTimeTextItem, actualCostTextItem,
			realWarrantyTimeTextItem, intWarrantyTimeTextItem;
	// codeMant_WarrMant_TextItem;
	private GHASelectItem obuSelectItem, baseRoleSelectItem, stateSelectItem,
			providerSelectItem, locationTypeSelectItem,
			workingAreaLocationSelectItem, facilityLocationSelectItem,
			adquisitionCostCurrencySelectItem,
			adquisitionCostCurrencyLocalSelectItem,
			depreciationMethodSelectItem, depreciationTimePotSelectItem,
			lifeTimePotSelectItem, actualCostCurrencySelectItem,
			realWarrantySinceSelectItem, realWarrantyPotSelectItem,
			intWarrantyPotSelectItem, intWarrantySinceSelectItem,
			// maintenanceLocationSelectItem,
			maintenanceProviderSelectItem, eiaTypeSelectItem,
			installationProviderSelectItem;
	private GHATitleTextItem information_TitleItem, adquisition_TitleItem,
			location_TitleItem, workingArea_TitleItem, facility_TitleItem,
			adqCost_TitleItem, actualCost_TitleItem, depTime_TitleItem,
			lifeTime_TitleItem, realWarranty_TitleItem,
			intermedWarranty_TitleItem, maintenance_TitleItem;
	private GHADateItem acceptationDateItem, purchaseDateItem,
			purchaseInvoiceDateItem, purchaseOrderDateItem, receptionDateItem,
			installationDateItem, contabilizationDateItem,
			lastDepreciationDate, realWarrantyBeginDate, intWarrantyBeginDate;
	// private GHACheckboxItem sameLocationAttendedItem, isInMaintenanceItem;
	private GHASectionForm sectionForm;
	private Validator validator;
	private List<EIASelectionListener> listeners;
	private Eia entity;

	{ // Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		// Information Form Items
		eiaTypeSelectItem = new GHASelectItem("Tipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypeSelectItem.setRequired(true);
		information_TitleItem = new GHATitleTextItem("Información:");
		codeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		serialTextItem = new GHATextItem("Serial",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		serialTextItem.setRequired(true);
		fixedAssetIdTextItem = new GHATextItem("Id. Activo Fijo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		fixedAssetIdTextItem.setRequired(true);
		obuSelectItem = new GHASelectItem("Departamento Responsable",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		obuSelectItem.setRequired(true);
		baseRoleSelectItem = new GHASelectItem("Rol Responsable",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		baseRoleSelectItem.setRequired(true);
		stateSelectItem = new GHASelectItem("Estado Equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		stateSelectItem.setRequired(true);
		acceptationDateItem = new GHADateItem("Fecha de Aceptación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Adquisicion Form Itemsxt
		adquisition_TitleItem = new GHATitleTextItem("Adquisición:");
		purchaseDateItem = new GHADateItem("Fecha de Compra",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		providerSelectItem = new GHASelectItem("Proveedor",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		providerSelectItem.setRequired(true);
		purchaseOrderNumTextItem = new GHATextItem("No. Orden Compra",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		purchaseInvoiceNumTextItem = new GHATextItem("No. Factura",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		receptionDateItem = new GHADateItem("Recepción",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		installationDateItem = new GHADateItem("Instalación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		purchaseInvoiceDateItem = new GHADateItem("Fecha de Factura",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		purchaseOrderDateItem = new GHADateItem("Fecha de Orden de Compra",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		installationProviderSelectItem = new GHASelectItem(
				"Proveedor de Inst.", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Ubicacion Form Items
		location_TitleItem = new GHATitleTextItem("Ubicación:");
		locationTypeSelectItem = new GHASelectItem("Tipo de Ubicación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		locationTypeSelectItem.setRequired(true);
		workingArea_TitleItem = new GHATitleTextItem("Área de Trabajo:");
		facility_TitleItem = new GHATitleTextItem("Servicio/Instalación:");
		workingAreaLocationCodeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		workingAreaLocationSelectItem = new GHASelectItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilityLocationCodeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		facilityLocationSelectItem = new GHASelectItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Costos Form Items
		adqCost_TitleItem = new GHATitleTextItem("Costo Adquisición:");
		actualCost_TitleItem = new GHATitleTextItem("Costo Actual:");
		depTime_TitleItem = new GHATitleTextItem("Tiempo de Depreciación:");
		lifeTime_TitleItem = new GHATitleTextItem("Tiempo de Vida:");
		adquisitionCostTextItem = new GHATextItem("Costo de Adq. del equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostCurrencySelectItem = new GHASelectItem("Moneda",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		contabilizationDateItem = new GHADateItem("Fecha de Contabilización",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostLocalTextItem = new GHATextItem("Costo de Adq. Local",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostCurrencyLocalSelectItem = new GHASelectItem(
				"Moneda Local", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationMethodSelectItem = new GHASelectItem("Metodo Depreciación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationTimePotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lastDepreciationDate = new GHADateItem("Fecha Ult. Depreciación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTimePotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		actualCostTextItem = new GHATextItem("Costo Actual en libros",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		actualCostCurrencySelectItem = new GHASelectItem("Moneda",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Garantias Form Items
		realWarranty_TitleItem = new GHATitleTextItem("Garantía Real:");
		intermedWarranty_TitleItem = new GHATitleTextItem(
				"Garantía del Intermediario:");
		maintenance_TitleItem = new GHATitleTextItem("Mantenimiento:");
		realWarrantySinceSelectItem = new GHASelectItem("Desde",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyBeginDate = new GHADateItem("Fecha Inicio",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantySinceSelectItem = new GHASelectItem("Desde",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyBeginDate = new GHADateItem("Fecha Inicio",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		// isInMaintenanceItem = new GHACheckboxItem("Equipo en Mantenimiento");
		// codeMant_WarrMant_TextItem = new GHATextItem("Cod. Ubicación Mant.",
		// GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		// maintenanceLocationSelectItem = new
		// GHASelectItem("Nombre Ubicación Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE,
		// false);
		maintenanceProviderSelectItem = new GHASelectItem("Proveedor de Mant.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		maintenanceProviderSelectItem.setRequired(true);
	}

	/**
	 * Creates a EiaForm with a eiatype select
	 */
	public EIAForm() {
		this(null);
	}

	/**
	 * @param eiaType
	 * @param canChooseEiaType
	 * 
	 */
	public EIAForm(EiaType eiaType) {
		select(eiaType);

		sectionForm.addSection("Información Básica", getInfoBasicaForm(), true);
		sectionForm.addSectionSeparator();
		sectionForm.addSection("Adquisicion", getAdquisicionForm(), false);
		sectionForm.addSection("Ubicación", getUbicacionForm(), false);
		sectionForm.addSection("Costos", getCostosTab(), false);
		sectionForm.addSection("Garantias", getGarantiasMantForm(), false);
		// sectionForm.addSection("EquiposIT", getEquiposIT(), false);

		addMember(sectionForm);

		// Fillers
		fillInformationSelects();
		fillAdquisitionSelects();
		fillLocationTypeSelect();
		fillLocationsSelects();
		fillCostsSelects();
		fillWarrantySelects();
		// fillITEquipmentsSelects();

		// Funcionalities
		buildingLocFuncionalities();
		// warrantyFunctionalities();
	}

	// //Form Creating Functions
	/**
	 * @return
	 */
	private DynamicForm getInfoBasicaForm() {
		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(3);

		equipoForm.setItems(eiaTypeSelectItem, new GHASpacerItem(2),
				information_TitleItem, new GHASpacerItem(2), codeTextItem,
				serialTextItem, fixedAssetIdTextItem, obuSelectItem,
				baseRoleSelectItem, stateSelectItem, acceptationDateItem);
		return equipoForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(3);

		adquisicionForm.setItems(adquisition_TitleItem, new GHASpacerItem(2),
				purchaseDateItem, receptionDateItem, installationDateItem,
				providerSelectItem, purchaseOrderNumTextItem,
				purchaseInvoiceNumTextItem, purchaseInvoiceDateItem,
				purchaseOrderDateItem, installationProviderSelectItem);
		return adquisicionForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getUbicacionForm() {
		DynamicForm areaForm = new DynamicForm();
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);

		areaForm.setItems(location_TitleItem, new GHASpacerItem(),
				locationTypeSelectItem, new GHASpacerItem(),
				workingArea_TitleItem, new GHASpacerItem(),
				workingAreaLocationSelectItem, workingAreaLocationCodeTextItem,
				facility_TitleItem, new GHASpacerItem(),
				facilityLocationSelectItem, facilityLocationCodeTextItem);
		return areaForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getCostosTab() {
		DynamicForm costosForm = new DynamicForm();
		costosForm.setTitleOrientation(TitleOrientation.TOP);
		costosForm.setNumCols(3);

		costosForm.setItems(adqCost_TitleItem, new GHASpacerItem(2),
				adquisitionCostTextItem, adquisitionCostCurrencySelectItem,
				contabilizationDateItem, adquisitionCostLocalTextItem,
				adquisitionCostCurrencyLocalSelectItem, new GHASpacerItem(),
				actualCost_TitleItem, new GHASpacerItem(2),
				depreciationMethodSelectItem, lastDepreciationDate,
				new GHASpacerItem(), actualCostTextItem,
				actualCostCurrencySelectItem, new GHASpacerItem(),
				depTime_TitleItem, depreciationTimeTextItem,
				depreciationTimePotSelectItem, lifeTime_TitleItem,
				lifeTimeTextItem, lifeTimePotSelectItem);

		return costosForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm() {
		DynamicForm garantiasMantenimientoForm = new DynamicForm();
		garantiasMantenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMantenimientoForm.setNumCols(3);

		garantiasMantenimientoForm.setItems(realWarranty_TitleItem,
				new GHASpacerItem(2), realWarrantySinceSelectItem,
				realWarrantyBeginDate, new GHASpacerItem(),
				realWarrantyTimeTextItem, realWarrantyPotSelectItem,
				new GHASpacerItem(), intermedWarranty_TitleItem,
				new GHASpacerItem(2), intWarrantySinceSelectItem,
				intWarrantyBeginDate, new GHASpacerItem(),
				intWarrantyTimeTextItem, intWarrantyPotSelectItem,
				new GHASpacerItem(), maintenance_TitleItem, // isInMaintenanceItem,
				new GHASpacerItem(2), maintenanceProviderSelectItem);
		// maintenanceLocationSelectItem,
		// codeMant_WarrMant_TextItem,

		return garantiasMantenimientoForm;
	}

	// private DynamicForm getEquiposIT() {
	// DynamicForm equiposITForm = new DynamicForm();
	// equiposITForm.setTitleOrientation(TitleOrientation.TOP);
	// equiposITForm.setNumCols(2);
	//
	// equiposITForm.setItems(it_TitleItem, new GHASpacerItem(),
	// itTypeSelectItem, machineNameTextItem, ipAddresTextItem,
	// macAddressTextItem);
	//
	// return equiposITForm;
	// }

	// //Fillers
	private void fillInformationSelects() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (EiaType entity : result) {
					valueMap.put(entity.getCode() + "", entity.getName() + "");
				}
				eiaTypeSelectItem.setValueMap(valueMap);
			}
		}, false);

		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu entity : result) {
					valueMap.put(entity.getId() + "", entity.getName() + "");
				}
				obuSelectItem.setValueMap(valueMap);
			}
		});

		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<Role>>() {
			@Override
			public void onSuccess(List<Role> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Role entity : result)
					valueMap.put(entity.getId() + "", entity.getName() + "");
				baseRoleSelectItem.setValueMap(valueMap);
			}
		});

		stateSelectItem.setValueMap(EiaStateEnum.toValueMap());
		stateSelectItem.setValue(EiaStateEnum.CREATED.name());
	}

	private void fillAdquisitionSelects() {
		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider entity : result)
							valueMap.put(entity.getId() + "", entity
									.getInstitution().getName() + "");
						providerSelectItem.setValueMap(valueMap);
					}
				});
	}

	private void fillLocationTypeSelect() {
		LinkedHashMap<String, String> valueMapLocationType = new LinkedHashMap<String, String>();
		valueMapLocationType.put("0", "Área de Trabajo");
		valueMapLocationType.put("1", "Servicio/Instalación");
		locationTypeSelectItem.setValueMap(valueMapLocationType);
	}

	private void fillLocationsSelects() {
		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
					@Override
					public void onSuccess(List<WorkingArea> result) {
						LinkedHashMap<String, String> valueMapWorkingArea = new LinkedHashMap<String, String>();

						for (WorkingArea entity : result) {
							valueMapWorkingArea.put(entity.getId() + "",
									entity.getName());
						}

						workingAreaLocationSelectItem
								.setValueMap(valueMapWorkingArea);
					}
				});
		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> valueMapFacility = new LinkedHashMap<String, String>();

				for (Facility entity : result) {
					valueMapFacility.put(entity.getId() + "", entity.getName());
				}

				facilityLocationSelectItem.setValueMap(valueMapFacility);
			}
		});

	}

	private void fillCostsSelects() {
		adquisitionCostCurrencySelectItem.setValueMap(CurrencyTypeEnum
				.toValueMap());
		adquisitionCostCurrencySelectItem.setValue(CurrencyTypeEnum.BS.name());
		adquisitionCostCurrencyLocalSelectItem.setValueMap(CurrencyTypeEnum
				.toValueMap());
		adquisitionCostCurrencyLocalSelectItem.setValue(CurrencyTypeEnum.BS
				.name());

		depreciationMethodSelectItem.setValueMap(DepreciationMethodEnum
				.toValueMap());
		depreciationMethodSelectItem.setValue(DepreciationMethodEnum.METHOD_1
				.name());

		depreciationTimePotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		depreciationTimePotSelectItem.setValue(TimePeriodEnum.HOURS.name());
		lifeTimePotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		lifeTimePotSelectItem.setValue(TimePeriodEnum.HOURS.name());

		actualCostCurrencySelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		actualCostCurrencySelectItem.setValue(CurrencyTypeEnum.BS.name());
	}

	private void fillWarrantySelects() {
		realWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		realWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		intWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		intWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());

		realWarrantyPotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		realWarrantyPotSelectItem.setValue(TimePeriodEnum.HOURS.name());
		intWarrantyPotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		intWarrantyPotSelectItem.setValue(TimePeriodEnum.HOURS.name());

		// GHACache.INSTANCE
		// .getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>()
		// {
		// @Override
		// public void onSuccess(List<BuildingLocation> result) {
		// LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
		// String>();
		// for (BuildingLocation entity : result)
		// valueMap.put(entity.getCode() + "",
		// entity.getName());
		// maintenanceLocationSelectItem.setValueMap(valueMap);
		// }
		// });

		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider entity : result)
							valueMap.put(entity.getId() + "", entity
									.getInstitution().getName() + "");
						maintenanceProviderSelectItem.setValueMap(valueMap);
						installationProviderSelectItem.setValueMap(valueMap);
					}
				});
	}

	// private void fillITEquipmentsSelects() {
	// itTypeSelectItem.setValueMap(ItSystemEnum.toValueMap());
	// itTypeSelectItem.setValue(ItSystemEnum.COMPUTER.name());
	// }

	// ///Funcionalities
	private void buildingLocFuncionalities() {
		workingAreaLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				workingAreaLocationCodeTextItem.setValue(event.getValue());
			}
		});

		facilityLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				facilityLocationCodeTextItem.setValue(event.getValue());
			}
		});

		locationTypeSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// value == 0 WorkingArea type selected, value == 1 Facility
				// type selected
				if (event.getValue().equals("0")) {
					workingAreaLocationSelectItem.setDisabled(false);
					facilityLocationSelectItem.setDisabled(true);
					facilityLocationSelectItem.clearValue();
					facilityLocationCodeTextItem.clearValue();
				} else if (event.getValue().equals("1")) {
					facilityLocationSelectItem.setDisabled(false);
					workingAreaLocationSelectItem.setDisabled(true);
					workingAreaLocationSelectItem.clearValue();
					workingAreaLocationCodeTextItem.clearValue();
				}
			}
		});
	}

	/*
	 * private void warrantyFunctionalities() {
	 * isInMaintenanceItem.addChangeHandler(new ChangeHandler() {
	 * 
	 * @Override public void onChange(ChangeEvent event) { if
	 * (event.getValue().equals(true)) {
	 * maintenanceLocationSelectItem.setDisabled(false);
	 * maintenanceProviderSelectItem.setDisabled(false); } else {
	 * maintenanceLocationSelectItem.setDisabled(true);
	 * maintenanceProviderSelectItem.setDisabled(true);
	 * maintenanceLocationSelectItem.clearValue();
	 * codeMant_WarrMant_TextItem.clearValue();
	 * maintenanceProviderSelectItem.clearValue(); } } });
	 * 
	 * maintenanceLocationSelectItem.addChangeHandler(new ChangeHandler() {
	 * 
	 * @Override public void onChange(ChangeEvent event) {
	 * codeMant_WarrMant_TextItem.setValue(event.getValue()); } }); }
	 */

	/**
	 * Save the new element to database
	 */
	public void save() {
		Eia eia = extract();
		if (eia != null)
			EIAModel.save(eia, new GHAAsyncCallback<Eia>() {
				@Override
				public void onSuccess(Eia result) {
					notifyEia(result);
				}
			});
	}

	/**
	 * Update the eia element
	 */
	public void update() {
		Eia eia = extract();
		if (eia != null)
			EIAModel.update(eia, new GHAAsyncCallback<Eia>() {
				@Override
				public void onSuccess(Eia result) {
					notifyEia(result);
				}
			});
	}

	/**
	 * @return
	 */
	private Eia extract() {
		Eia eia;
		if (this.entity == null)
			eia = new Eia();
		else
			eia = this.entity;

		// basic information
		if (eiaTypeSelectItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeSelectItem.getValueAsString()));
		eia.setCode(codeTextItem.getValueAsString());
		eia.setSerialNumber(serialTextItem.getValueAsString());
		eia.setFixedAssetIdentifier(fixedAssetIdTextItem.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			Obu obu = new Obu();
			obu.setId(Integer.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			Role baseRole = new Role();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}

		if (stateSelectItem.getValue() != null) {
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		}

		if (acceptationDateItem.getValue() != null)
			eia.setAcceptationDate(new Date(acceptationDateItem
					.getValueAsDate().getTime()));

		// adquisition
		if (purchaseDateItem.getValue() != null)
			eia.setPurchaseDate(new Date(purchaseDateItem.getValueAsDate()
					.getTime()));

		if (receptionDateItem.getValue() != null)
			eia.setReceptionDate(new Date(receptionDateItem.getValueAsDate()
					.getTime()));

		if (installationDateItem.getValue() != null)
			eia.setInstallationDate(new Date(installationDateItem
					.getValueAsDate().getTime()));

		if (providerSelectItem.getValue() != null) {
			eia.setProvider(new ExternalProvider(Integer
					.valueOf(providerSelectItem.getValueAsString())));
		}
		eia.setPurchaseOrderNumber(purchaseOrderNumTextItem.getValueAsString());
		eia.setPurchaseInvoiceNumber(purchaseInvoiceNumTextItem
				.getValueAsString());

		if (purchaseInvoiceDateItem.getValue() != null)
			eia.setPurchaseInvoiceDate(new Date(purchaseInvoiceDateItem
					.getValueAsDate().getTime()));

		if (purchaseOrderDateItem.getValue() != null)
			eia.setPurchaseOrderDate(new Date(purchaseOrderDateItem
					.getValueAsDate().getTime()));
		if (installationProviderSelectItem.getValue() != null) {
			eia.setInstallationProvider(new ExternalProvider(Integer
					.valueOf(installationProviderSelectItem.getValueAsString())));
		}

		// ubication
		if (locationTypeSelectItem.getValue() != null) {
			if (locationTypeSelectItem.getValue().equals("0")) {
				if (workingAreaLocationSelectItem.getValue() != null) {
					eia.setFacility(null);
					eia.setWorkingArea(new WorkingArea(Integer
							.valueOf(workingAreaLocationSelectItem
									.getValueAsString())));
				}
			} else if (locationTypeSelectItem.getValue().equals("1")) {
				if (facilityLocationSelectItem.getValue() != null) {
					eia.setWorkingArea(null);
					eia.setFacility(new Facility(Integer
							.valueOf(facilityLocationSelectItem
									.getValueAsString())));
				}
			}
		}
		// if (buildingLocationSelectItem.getValue() != null) {
		// eia.setBuildingLocation(new BuildingLocation(
		// buildingLocationSelectItem.getValueAsString()));
		// if (sameLocationAttendedItem.getValueAsBoolean()) {
		// eia.setAttendedLocation(new BuildingLocation(
		// buildingLocationSelectItem.getValueAsString()));
		// } else {
		// if (attendedLocationSelectItem.getValue() != null) {
		// eia.setAttendedLocation(new BuildingLocation(
		// attendedLocationSelectItem.getValueAsString()));
		// }
		// }
		// }

		// costs
		if (adquisitionCostTextItem.getValue() != null)
			eia.setAdquisitionCost(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostTextItem.getValueAsString())));

		eia.setAdquisitionCostCurrency(CurrencyTypeEnum
				.valueOf(adquisitionCostCurrencySelectItem.getValueAsString()));

		if (contabilizationDateItem.getValue() != null)
			eia.setContabilizationDate(new Date(contabilizationDateItem
					.getValueAsDate().getTime()));

		if (adquisitionCostLocalTextItem.getValue() != null)
			eia.setAdquisitionCostLocal(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostLocalTextItem.getValueAsString())));

		eia.setAdquisitionCostCurrencyLocal(CurrencyTypeEnum
				.valueOf(adquisitionCostCurrencyLocalSelectItem
						.getValueAsString()));
		eia.setDepreciationMethod(DepreciationMethodEnum
				.valueOf(depreciationMethodSelectItem.getValueAsString()));

		if (lastDepreciationDate.getValue() != null)
			eia.setDateLastDepreciation(new Date(lastDepreciationDate
					.getValueAsDate().getTime()));

		if (actualCostTextItem.getValue() != null)
			eia.setActualCost(BigDecimal.valueOf(Double
					.valueOf(actualCostTextItem.getValueAsString())));
		eia.setActualCostCurrency(CurrencyTypeEnum
				.valueOf(actualCostCurrencySelectItem.getValueAsString()));

		if (depreciationTimeTextItem.getValue() != null)
			eia.setDepreciationTime(Integer.valueOf(depreciationTimeTextItem
					.getValueAsString()));
		eia.setDepreciationTimePoT(TimePeriodEnum
				.valueOf(depreciationTimePotSelectItem.getValueAsString()));

		if (lifeTimeTextItem.getValue() != null)
			eia.setLifeTime(Integer.valueOf(lifeTimeTextItem.getValueAsString()));
		eia.setLifeTimePoT(TimePeriodEnum.valueOf(lifeTimePotSelectItem
				.getValueAsString()));

		// guarantees
		if (realWarrantyBeginDate.getValue() != null)
			eia.setRealWarrantyBegin(new Date(realWarrantyBeginDate
					.getValueAsDate().getTime()));

		eia.setRealWarrantyPoT(TimePeriodEnum.valueOf(realWarrantyPotSelectItem
				.getValueAsString()));
		eia.setRealWarrantySince(WarrantySinceEnum
				.valueOf(realWarrantySinceSelectItem.getValueAsString()));

		if (realWarrantyTimeTextItem.getValue() != null)
			eia.setRealWarrantyTime(Integer.valueOf(realWarrantyTimeTextItem
					.getValueAsString()));

		if (intWarrantyBeginDate.getValue() != null)
			eia.setIntWarrantyBegin(new Date(intWarrantyBeginDate
					.getValueAsDate().getTime()));

		eia.setIntWarrantyPoT(TimePeriodEnum.valueOf(intWarrantyPotSelectItem
				.getValueAsString()));
		eia.setIntWarrantySince(WarrantySinceEnum
				.valueOf(intWarrantySinceSelectItem.getValueAsString()));

		if (intWarrantyTimeTextItem.getValue() != null)
			eia.setIntWarrantyTime(Integer.valueOf(intWarrantyTimeTextItem
					.getValueAsString()));
		// if (isInMaintenanceItem.getValueAsBoolean()) {
		// eia.setMaintenanceLocation(new BuildingLocation(
		// maintenanceLocationSelectItem.getValueAsString()));
		if (maintenanceProviderSelectItem.getValue() != null)
			eia.setMaintenanceProvider(new ExternalProvider(Integer
					.valueOf(maintenanceProviderSelectItem.getValueAsString())));
		// }

		// itEquipments
		// eia.setItType(ItSystemEnum.valueOf(itTypeSelectItem.getValueAsString()));
		// eia.setMachineName(machineNameTextItem.getValueAsString());
		// eia.setIpAddress(ipAddresTextItem.getValueAsString());
		// eia.setMacAddress(macAddressTextItem.getValueAsString());
		// Window.alert("1 " + eia);

		Set<ConstraintViolation<Eia>> violations = validator.validate(eia);
		// Window.alert("2 " + violations);
		// Window.alert(violations.isEmpty() == true ? "vacio" : "novacio");
		if (violations.isEmpty())
			return eia;
		else
			GHANotification.alert(GHAStrings.get(violations.iterator().next()
					.getMessage()));
		// Window.alert("3");
		// Window.alert(violations.iterator().next().getMessage());
		// Window.alert("4");
		return null;
	}

	/**
	 * 
	 */
	public void clearValue() {
		this.entity = null;
		// clean text fields
		codeTextItem.clearValue();
		serialTextItem.clearValue();
		fixedAssetIdTextItem.clearValue();
		purchaseOrderNumTextItem.clearValue();
		purchaseInvoiceNumTextItem.clearValue();
		workingAreaLocationCodeTextItem.clearValue();
		facilityLocationCodeTextItem.clearValue();
		adquisitionCostTextItem.clearValue();
		adquisitionCostLocalTextItem.clearValue();
		actualCostTextItem.clearValue();
		depreciationTimeTextItem.clearValue();
		lifeTimeTextItem.clearValue();
		realWarrantyTimeTextItem.clearValue();
		intWarrantyTimeTextItem.clearValue();
		// codeMant_WarrMant_TextItem.clearValue();
		// ipAddresTextItem.clearValue();
		// macAddressTextItem.clearValue();
		// machineNameTextItem.clearValue();

		// clean select fields
		eiaTypeSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		stateSelectItem.clearValue();
		providerSelectItem.clearValue();
		installationProviderSelectItem.clearValue();
		locationTypeSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		adquisitionCostCurrencySelectItem.clearValue();
		adquisitionCostCurrencyLocalSelectItem.clearValue();
		depreciationMethodSelectItem.clearValue();
		actualCostCurrencySelectItem.clearValue();
		depreciationTimePotSelectItem.clearValue();
		lifeTimePotSelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		realWarrantyPotSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		intWarrantyPotSelectItem.clearValue();
		// maintenanceLocationSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();
		// itTypeSelectItem.clearValue();

		acceptationDateItem.clearValue();
		purchaseDateItem.clearValue();
		receptionDateItem.clearValue();
		installationDateItem.clearValue();
		purchaseInvoiceDateItem.clearValue();
		purchaseOrderDateItem.clearValue();
		contabilizationDateItem.clearValue();
		lastDepreciationDate.clearValue();
		realWarrantyBeginDate.clearValue();
		intWarrantyBeginDate.clearValue();
		purchaseInvoiceDateItem.clearValue();
		purchaseOrderDateItem.clearValue();
		acceptationDateItem.clearValue();

		// sameLocationAttendedItem.setValue(false);
		// isInMaintenanceItem.setValue(false);

	}

	// //Implementations

	private void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	@Override
	public void select(EiaType eiaType) {
		if (eiaType != null) {
			eiaTypeSelectItem.setValue(eiaType.getCode());
			eiaTypeSelectItem.disable();
		}
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	@Override
	public void hide() {
		sectionForm.deactivate();
	}

	/**
	 * @param eia
	 */
	public void setEia(Eia eia) {
		this.entity = eia;
		// basic information
		if (eia.getEiaType() != null)
			eiaTypeSelectItem.setValue(eia.getEiaType().getCode());
		if (eia.getCode() != null)
			codeTextItem.setValue(eia.getCode());
		if (eia.getSerialNumber() != null)
			serialTextItem.setValue(eia.getSerialNumber());
		if (eia.getFixedAssetIdentifier() != null)
			fixedAssetIdTextItem.setValue(eia.getFixedAssetIdentifier());
		if (eia.getObu() != null)
			obuSelectItem.setValue(eia.getObu().getId());
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());
		if (eia.getAcceptationDate() != null)
			acceptationDateItem.setValue(eia.getAcceptationDate());

		// adquisition
		if (eia.getPurchaseDate() != null)
			purchaseDateItem.setValue(eia.getPurchaseDate());
		if (eia.getReceptionDate() != null)
			receptionDateItem.setValue(eia.getReceptionDate());
		if (eia.getInstallationDate() != null)
			installationDateItem.setValue(eia.getInstallationDate());
		if (eia.getProvider() != null)
			providerSelectItem.setValue(eia.getProvider().getId());
		if (eia.getPurchaseOrderNumber() != null)
			purchaseOrderNumTextItem.setValue(eia.getPurchaseOrderNumber());
		if (eia.getPurchaseInvoiceNumber() != null)
			purchaseInvoiceNumTextItem.setValue(eia.getPurchaseInvoiceNumber());
		if (eia.getPurchaseInvoiceDate() != null)
			purchaseInvoiceDateItem.setValue(eia.getPurchaseInvoiceDate());
		if (eia.getPurchaseOrderDate() != null)
			purchaseOrderDateItem.setValue(eia.getPurchaseOrderDate());
		if (eia.getInstallationProvider() != null)
			installationProviderSelectItem.setValue(eia
					.getInstallationProvider().getId());

		// // ubication
		// boolean flag = true;
		// if (eia.getBuildingLocation() != null)
		// buildingLocationSelectItem.setValue(eia.getBuildingLocation()
		// .getCode());
		// else
		// flag = false;
		// if (eia.getAttendedLocation() != null)
		// attendedLocationSelectItem.setValue(eia.getAttendedLocation()
		// .getCode());
		// else
		// flag = false;
		// if (flag
		// && eia.getBuildingLocation().getCode() == eia
		// .getAttendedLocation().getCode()) {
		// sameLocationAttendedItem.setValue(true);
		// } else {
		// sameLocationAttendedItem.setValue(false);
		// }
		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem
					.setValue(eia.getWorkingArea().getId());
			workingAreaLocationCodeTextItem.setValue(eia.getWorkingArea()
					.getId());
			locationTypeSelectItem.setValue("0");
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
			facilityLocationCodeTextItem.setValue(eia.getFacility().getId());
			locationTypeSelectItem.setValue("1");
		}

		// costs
		if (eia.getAdquisitionCost() != null)
			adquisitionCostTextItem.setValue(eia.getAdquisitionCost());
		if (eia.getAdquisitionCostCurrency() != null)
			adquisitionCostCurrencySelectItem.setValue(eia
					.getAdquisitionCostCurrency().name());
		if (eia.getContabilizationDate() != null)
			contabilizationDateItem.setValue(eia.getContabilizationDate());
		if (eia.getAdquisitionCostLocal() != null)
			adquisitionCostLocalTextItem
					.setValue(eia.getAdquisitionCostLocal());
		if (eia.getAdquisitionCostCurrencyLocal() != null)
			adquisitionCostCurrencyLocalSelectItem.setValue(eia
					.getAdquisitionCostCurrencyLocal().name());
		if (eia.getDepreciationMethod() != null)
			depreciationMethodSelectItem.setValue(eia.getDepreciationMethod()
					.name());
		if (eia.getDateLastDepreciation() != null)
			lastDepreciationDate.setValue(eia.getDateLastDepreciation());
		if (eia.getActualCost() != null)
			actualCostTextItem.setValue(eia.getActualCost());
		if (eia.getActualCostCurrency() != null)
			actualCostCurrencySelectItem.setValue(eia.getActualCostCurrency()
					.name());
		depreciationTimeTextItem.setValue(eia.getDepreciationTime());
		if (eia.getDepreciationTimePoT() != null)
			depreciationTimePotSelectItem.setValue(eia.getDepreciationTimePoT()
					.name());
		lifeTimeTextItem.setValue(eia.getLifeTime());
		if (eia.getLifeTimePoT() != null)
			lifeTimePotSelectItem.setValue(eia.getLifeTimePoT().name());

		// guarantees if (eia.getRealWarrantyBegin() != null)
		if (eia.getRealWarrantySince() != null)
			realWarrantySinceSelectItem.setValue(eia.getRealWarrantySince()
					.name());
		realWarrantyBeginDate.setValue(eia.getRealWarrantyBegin());
		realWarrantyTimeTextItem.setValue(eia.getRealWarrantyTime());
		if (eia.getRealWarrantyPoT() != null)
			realWarrantyPotSelectItem.setValue(eia.getRealWarrantyPoT().name());
		if (eia.getIntWarrantySince() != null)
			intWarrantySinceSelectItem.setValue(eia.getIntWarrantySince()
					.name());
		if (eia.getIntWarrantyBegin() != null)
			intWarrantyBeginDate.setValue(eia.getIntWarrantyBegin());
		intWarrantyTimeTextItem.setValue(eia.getIntWarrantyTime());
		if (eia.getIntWarrantyPoT() != null)
			intWarrantyPotSelectItem.setValue(eia.getIntWarrantyPoT().name());

		// if (eia.getMaintenanceLocation() != null) {
		// isInMaintenanceItem.setValue(true);
		// if (eia.getMaintenanceLocation() != null)
		// maintenanceLocationSelectItem.setValue(eia
		// .getMaintenanceLocation().getCode());
		if (eia.getMaintenanceProvider() != null
				&& eia.getMaintenanceProvider().getInstitution() != null)
			maintenanceProviderSelectItem.setValue(eia.getMaintenanceProvider()
					.getInstitution().getId());
		// }

		// itEquipments
		// if (eia.getItType() != null)
		// itTypeSelectItem.setValue(eia.getItType().name());
		// if (eia.getMachineName() != null)
		// machineNameTextItem.setValue(eia.getMachineName());
		// if (eia.getIpAddress() != null)
		// ipAddresTextItem.setValue(eia.getIpAddress());
		// if (eia.getMacAddress() != null)
		// macAddressTextItem.setValue(eia.getMacAddress());
	}
}
