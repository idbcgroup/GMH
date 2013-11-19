package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class EIADamageReportForm extends GHAForm<EiaDamageReport> implements
		EIASelectionListener, EiaDamageReportSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			workingAreaLocationCodeTextItem, facilityLocationCodeTextItem,
			realWarrantyTimeTextItem, intWarrantyTimeTextItem;
	private GHASelectItem obuSelectItem, baseRoleSelectItem, stateSelectItem,
			adqisitionProviderSelectItem, locationTypeSelectItem,
			workingAreaLocationSelectItem, facilityLocationSelectItem,
			realWarrantySinceSelectItem, realWarrantyPotSelectItem,
			intWarrantyPotSelectItem, intWarrantySinceSelectItem,
			maintenanceProviderSelectItem, eiaTypeSelectItem;
	private GHATitleTextItem information_TitleItem, location_TitleItem,
			workingArea_TitleItem, facility_TitleItem, report_TitleItem,
			realWarranty_TitleItem, intermedWarranty_TitleItem,
			providers_TitleItem;
	private GHADateItem acceptationDateItem,

	realWarrantyBeginDate, intWarrantyBeginDate;
	// private GHACheckboxItem sameLocationAttendedItem, isInMaintenanceItem;
	private GHASectionForm sectionForm;
	private GHADynamicForm infoBasicaForm;
	private GHADynamicForm garantiasForm;
	private GHADynamicForm ubicacionForm;
	private GHADynamicForm reportForm;
	// private GHADynamicForm garantiasMantForm;

	private List<EiaDamageReportSelectionListener> listeners;
	private Eia eia;

	{ // Global
		sectionForm = new GHASectionForm();

		// Information Form Items
		eiaTypeSelectItem = new GHASelectItem("Tipo", false, changedHandler);
		information_TitleItem = new GHATitleTextItem("Información:", 3);
		codeTextItem = new GHATextItem("Código", false, changedHandler);
		codeTextItem.setLength(20);
		serialTextItem = new GHATextItem("Serial", false, changedHandler);
		serialTextItem.setLength(20);
		fixedAssetIdTextItem = new GHATextItem("Id Activo Fijo", false,
				changedHandler);
		fixedAssetIdTextItem.setLength(19);
		obuSelectItem = new GHASelectItem("Departamento Responsable", false,
				changedHandler);
		baseRoleSelectItem = new GHASelectItem("Rol Responsable", false,
				changedHandler);
		stateSelectItem = new GHASelectItem("Estado Equipo", false,
				changedHandler);
		stateSelectItem.setDefaultValue(EiaStateEnum.CREATED.name());
		stateSelectItem.setAllowEmptyValue(false);
		acceptationDateItem = new GHADateItem("Fecha de Aceptación", false);
		acceptationDateItem.addChangedHandler(changedHandler);
		providers_TitleItem = new GHATitleTextItem("Proveedores", 3);
		adqisitionProviderSelectItem = new GHASelectItem("Proveedor de Adq.",
				false, changedHandler);
		maintenanceProviderSelectItem = new GHASelectItem("Proveedor de Mant.",
				false, changedHandler);

		// Garantias Form Items
		// Garantia
		realWarranty_TitleItem = new GHATitleTextItem("Garantía Real:", 4);
		intermedWarranty_TitleItem = new GHATitleTextItem(
				"Garantía del Intermediario:", 4);
		realWarrantySinceSelectItem = new GHASelectItem("Desde", false,
				changedHandler);
		realWarrantyTimeTextItem = new GHATextItem("Duración", false,
				changedHandler);
		realWarrantyTimeTextItem.setLength(3);
		realWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				false, changedHandler);
		realWarrantyBeginDate = new GHADateItem("Fecha Inicio", false);
		realWarrantyBeginDate.addChangedHandler(changedHandler);

		intWarrantySinceSelectItem = new GHASelectItem("Desde", false,
				changedHandler);
		intWarrantyTimeTextItem = new GHATextItem("Duración", false,
				changedHandler);
		intWarrantyTimeTextItem.setLength(3);
		intWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				false, changedHandler);
		intWarrantyBeginDate = new GHADateItem("Fecha Inicio", true);
		intWarrantyBeginDate.addChangedHandler(changedHandler);

		// Ubicacion Form Items
		location_TitleItem = new GHATitleTextItem("Ubicación:", 3);
		locationTypeSelectItem = new GHASelectItem("Tipo de Ubicación", false,
				changedHandler);

		workingArea_TitleItem = new GHATitleTextItem("Área de Trabajo:", 3);
		facility_TitleItem = new GHATitleTextItem("Servicio/Instalación:", 3);
		workingAreaLocationCodeTextItem = new GHATextItem("Código", false);
		workingAreaLocationSelectItem = new GHASelectItem("Nombre", false,
				changedHandler);
		workingAreaLocationSelectItem.setDisabled(true);

		facilityLocationCodeTextItem = new GHATextItem("Código", false);
		facilityLocationSelectItem = new GHASelectItem("Nombre", false,
				changedHandler);
		facilityLocationSelectItem.setDisabled(true);

		// DamageReport Form Items
		report_TitleItem = new GHATitleTextItem(
				"Datos del Reporte de Equipo Dañado:", 4);

		deactivate();
	}

	/**
	 * @param eiaType
	 * 
	 */
	public EIADamageReportForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		infoBasicaForm = getInfoBasicaForm();
		garantiasForm = getAdquisicionForm();
		ubicacionForm = getUbicacionForm();
		reportForm = getReportForm();

		sectionForm.addSection("Información Básica", infoBasicaForm);
		sectionForm.addSectionSeparator();
		sectionForm.addSection("Garantias", garantiasForm);
		sectionForm.addSection("Ubicación", ubicacionForm);
		sectionForm.addSection("Datos Reporte", reportForm);

		addMember(sectionForm);

		// Fillers
		fillInformationSelects();
		fillAdquisitionSelects();
		fillLocationTypeSelect();
		fillLocationsSelects();
		fillWarrantySelects();

		// Funcionalities
		buildingLocFuncionalities();

		sectionForm.openFirst();
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		listeners.add(eiaDamageReportSelectionListener);
	}

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

	@Override
	public void clear() {
		super.clear();
		// clean text fields
		codeTextItem.clearValue();
		serialTextItem.clearValue();
		serialTextItem.validate();
		fixedAssetIdTextItem.clearValue();
		facilityLocationCodeTextItem.clearValue();
		realWarrantyTimeTextItem.clearValue();
		intWarrantyTimeTextItem.clearValue();

		// clean select fields
		eiaTypeSelectItem.clearValue();
		stateSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		adqisitionProviderSelectItem.clearValue();
		locationTypeSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		realWarrantyPotSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		intWarrantyPotSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();

		acceptationDateItem.clearValue();
		realWarrantyBeginDate.clearValue();
		intWarrantyBeginDate.clearValue();
		acceptationDateItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private EiaDamageReport extract() {
		EiaDamageReport eiaDamageReport = new EiaDamageReport();

		eia.setState(EiaStateEnum.DAMAGED);
		eiaDamageReport.setEia(eia);

		return eiaDamageReport;
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
						adqisitionProviderSelectItem.setValueMap(valueMap);
					}
				});
	}

	// Fillers
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
		// stateSelectItem.setValue(EiaStateEnum.CREATED.name());
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

	private void fillLocationTypeSelect() {
		LinkedHashMap<String, String> valueMapLocationType = new LinkedHashMap<String, String>();
		valueMapLocationType.put("0", "Área de Trabajo");
		valueMapLocationType.put("1", "Servicio/Instalación");
		locationTypeSelectItem.setValueMap(valueMapLocationType);
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
						for (ExternalProvider entity : result) {
							valueMap.put(entity.getId() + "", entity
									.getInstitution().getName() + "");
						}
						maintenanceProviderSelectItem.setValueMap(valueMap);
					}
				});
	}

	/**
	 * @return
	 */
	private GHADynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		GHADynamicForm adquisicionForm = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 4);

		adquisicionForm.setItems(realWarranty_TitleItem,
				realWarrantySinceSelectItem, realWarrantyBeginDate,
				realWarrantyTimeTextItem, realWarrantyPotSelectItem,
				new GHASpacerItem(4), intermedWarranty_TitleItem,
				intWarrantySinceSelectItem, intWarrantyBeginDate,
				intWarrantyTimeTextItem, intWarrantyPotSelectItem);

		return adquisicionForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getInfoBasicaForm() {
		GHADynamicForm equipoForm = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 3);

		equipoForm.setItems(information_TitleItem, eiaTypeSelectItem,
				new GHASpacerItem(2), codeTextItem, serialTextItem,
				fixedAssetIdTextItem, obuSelectItem, baseRoleSelectItem,
				new GHASpacerItem(), acceptationDateItem, stateSelectItem,
				new GHASpacerItem(), providers_TitleItem,
				adqisitionProviderSelectItem, maintenanceProviderSelectItem);

		return equipoForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getReportForm() {
		GHADynamicForm res = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 4);

		res.setItems(report_TitleItem);

		return res;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getUbicacionForm() {
		GHADynamicForm areaForm = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 3);

		areaForm.setItems(location_TitleItem, locationTypeSelectItem,
				new GHASpacerItem(2), workingArea_TitleItem,
				workingAreaLocationSelectItem, workingAreaLocationCodeTextItem,
				facility_TitleItem, facilityLocationSelectItem,
				facilityLocationCodeTextItem);
		return areaForm;
	}

	@Override
	public void hide() {
		super.hide();
		sectionForm.hide();
	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
		for (EiaDamageReportSelectionListener listener : listeners)
			listener.select(eiaDamageReport);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		infoBasicaForm.resize(GHAUiHelper.getSectionFormFormWidth(30), 3);
		garantiasForm.resize(GHAUiHelper.getSectionFormFormWidth(30), 4);
		ubicacionForm.resize(GHAUiHelper.getSectionFormFormWidth(30), 3);
		reportForm.resize(GHAUiHelper.getSectionFormFormWidth(30), 4);
	}

	public void openFirstSection() {
		sectionForm.openFirst();
	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		listeners.remove(eiaDamageReportSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<EiaDamageReport> callback) {
		EiaDamageReport eiaDamageReport = extract();
		if (eiaDamageReport != null) {
			EiaDamageReportModel.save(eiaDamageReport,
					new GHAAsyncCallback<EiaDamageReport>() {
						@Override
						public void onSuccess(EiaDamageReport result) {
							hasUnCommittedChanges = false;
							clear();
							if (callback != null)
								callback.onSuccess(result);
						}
					});
		}
	}

	@Override
	public void select(Eia eia) {
		this.eia = eia;

		// basic information
		codeTextItem.setValue(eia.getCode());
		serialTextItem.setValue(eia.getSerialNumber());
		fixedAssetIdTextItem.setValue(eia.getFixedAssetIdentifier());
		acceptationDateItem.setValue(eia.getAcceptationDate());

		eiaTypeSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		stateSelectItem.clearValue();

		if (eia.getEiaType() != null)
			eiaTypeSelectItem.setValue(eia.getEiaType().getCode());
		if (eia.getObu() != null)
			obuSelectItem.setValue(eia.getObu().getId());
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());

		// acquisition
		adqisitionProviderSelectItem.clearValue();

		if (eia.getProvider() != null)
			adqisitionProviderSelectItem.setValue(eia.getProvider().getId());

		locationTypeSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();

		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem.setDisabled(false);
			workingAreaLocationSelectItem
					.setValue(eia.getWorkingArea().getId());
			workingAreaLocationCodeTextItem.setValue(eia.getWorkingArea()
					.getId());
			locationTypeSelectItem.setValue("0");
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setDisabled(false);
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
			facilityLocationCodeTextItem.setValue(eia.getFacility().getId());
			locationTypeSelectItem.setValue("1");
		}

		// costs
		realWarrantyBeginDate.setValue(eia.getRealWarrantyBegin());
		realWarrantyTimeTextItem.setValue(eia.getRealWarrantyTime());
		intWarrantyTimeTextItem.setValue(eia.getIntWarrantyTime());
		intWarrantyBeginDate.setValue(eia.getIntWarrantyBegin());

		realWarrantySinceSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();

		// guarantees
		if (eia.getRealWarrantySince() != null)
			realWarrantySinceSelectItem.setValue(eia.getRealWarrantySince()
					.name());

		if (eia.getRealWarrantyPoT() != null)
			realWarrantyPotSelectItem.setValue(eia.getRealWarrantyPoT().name());

		if (eia.getIntWarrantySince() != null)
			intWarrantySinceSelectItem.setValue(eia.getIntWarrantySince()
					.name());

		if (eia.getIntWarrantyPoT() != null)
			intWarrantyPotSelectItem.setValue(eia.getIntWarrantyPoT().name());

		if (eia.getMaintenanceProvider() != null
				&& eia.getMaintenanceProvider().getInstitution() != null)
			maintenanceProviderSelectItem.setValue(eia.getMaintenanceProvider()
					.getId());
	}

	@Override
	public void set(EiaDamageReport entity) {
	}

	@Override
	public void show() {
		sectionForm.show();
		super.show();
	}

	private void toggleForm(boolean activate) {
		codeTextItem.setDisabled(!activate);
		serialTextItem.setDisabled(!activate);
		fixedAssetIdTextItem.setDisabled(!activate);
		workingAreaLocationCodeTextItem.setDisabled(!activate);
		facilityLocationCodeTextItem.setDisabled(!activate);
		realWarrantyTimeTextItem.setDisabled(!activate);
		intWarrantyTimeTextItem.setDisabled(!activate);

		// clean select fields
		eiaTypeSelectItem.setDisabled(!activate);
		obuSelectItem.setDisabled(!activate);
		baseRoleSelectItem.setDisabled(!activate);
		stateSelectItem.setDisabled(!activate);
		adqisitionProviderSelectItem.setDisabled(!activate);
		locationTypeSelectItem.setDisabled(!activate);
		workingAreaLocationSelectItem.setDisabled(!activate);
		facilityLocationSelectItem.setDisabled(!activate);
		realWarrantySinceSelectItem.setDisabled(!activate);
		realWarrantyPotSelectItem.setDisabled(!activate);
		intWarrantySinceSelectItem.setDisabled(!activate);
		intWarrantyPotSelectItem.setDisabled(!activate);
		maintenanceProviderSelectItem.setDisabled(!activate);

		acceptationDateItem.setDisabled(!activate);
		realWarrantyBeginDate.setDisabled(!activate);
		intWarrantyBeginDate.setDisabled(!activate);
		acceptationDateItem.setDisabled(!activate);
	}

	@Override
	public void undo() {
		if (originalEntity == null)
			clear();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;
	}

	@Override
	public void update(GHAAsyncCallback<EiaDamageReport> callback) {
	}
}
