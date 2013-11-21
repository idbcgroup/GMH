package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABpuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATimeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.util.LogicalTime;

public class EIADamageReportForm extends GHAForm<EiaDamageReport> implements
		EIASelectionListener, EiaDamageReportSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			workingAreaLocationCodeTextItem, facilityCodeTextItem,
			realWarrantyTimeTextItem, intWarrantyTimeTextItem;
	private GHAWorkingAreaSelectItem workingAreaSelectItem;
	private GHAFacilitySelectItem facilitySelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHAExternalProviderSelectItem adqisitionProviderSelectItem,
			maintenanceProviderSelectItem;
	private GHASelectItem stateSelectItem, locationTypeSelectItem,
			realWarrantySinceSelectItem, realWarrantyPotSelectItem,
			intWarrantyPotSelectItem, intWarrantySinceSelectItem,
			eiaTypeSelectItem;
	private GHATitleTextItem information_TitleItem, location_TitleItem,
			workingArea_TitleItem, facility_TitleItem, report_TitleItem,
			realWarranty_TitleItem, intermedWarranty_TitleItem,
			providers_TitleItem;
	private GHADateItem realWarrantyBeginDate, intWarrantyBeginDate;
	// datos del reporte
	private GHADateItem damageDateItem;
	private GHATimeItem damageTimeItem;
	private GHATextAreaItem damageMotiveTextAreaItem;
	private GHABpuSelectItem userWhoRegistedSelectItem,
			userWhoReportedSelectItem;
	private GHASelectItem damageStatusSelectItem, damagePrioritySelectItem;

	private GHASectionForm sectionForm;
	private GHADynamicForm infoBasicaForm;
	private GHADynamicForm garantiasForm;
	private GHADynamicForm ubicacionForm;
	private GHADynamicForm reportForm;

	private List<EiaDamageReportSelectionListener> listeners;
	private Eia eia;

	{ // Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EiaDamageReportSelectionListener>();

		// report data Form Items
		report_TitleItem = new GHATitleTextItem(
				"Datos del Reporte de Equipo Dañado:", 3);
		damageDateItem = new GHADateItem("Fecha del daño o falla",
				changedHandler);
		damageTimeItem = new GHATimeItem("Hora del daño o falla",
				changedHandler);
		damageStatusSelectItem = new GHASelectItem("Estatus", true,
				changedHandler);
		damagePrioritySelectItem = new GHASelectItem("Prioridad", true,
				changedHandler);
		userWhoReportedSelectItem = new GHABpuSelectItem(
				"Usuario que lo reportó", true, changedHandler);
		userWhoRegistedSelectItem = new GHABpuSelectItem(
				"Usuario que lo registró", true, changedHandler);
		damageMotiveTextAreaItem = new GHATextAreaItem(
				"Motivo del daño o falla", changedHandler);
		damageMotiveTextAreaItem.setColSpan(2);

		// Information Form Items
		eiaTypeSelectItem = new GHASelectItem("Tipo", false);
		information_TitleItem = new GHATitleTextItem("Información:", 3);
		codeTextItem = new GHATextItem("Código", false);
		codeTextItem.setLength(20);
		serialTextItem = new GHATextItem("Serial", false);
		serialTextItem.setLength(20);
		fixedAssetIdTextItem = new GHATextItem("Id Activo Fijo", false);
		fixedAssetIdTextItem.setLength(19);
		obuSelectItem = new GHAObuSelectItem();
		obuSelectItem.setDisabled(true);
		baseRoleSelectItem = new GHARoleSelectItem();
		baseRoleSelectItem.setDisabled(true);
		stateSelectItem = new GHAEiaStateSelectItem();
		stateSelectItem.setDisabled(true);
		providers_TitleItem = new GHATitleTextItem("Proveedores", 3);
		adqisitionProviderSelectItem = new GHAExternalProviderSelectItem(
				"Proveedor de Adq.", false);
		maintenanceProviderSelectItem = new GHAExternalProviderSelectItem(
				"Proveedor de Mant.", false);

		// Garantias Form Items
		realWarranty_TitleItem = new GHATitleTextItem("Garantía Real:", 4);
		intermedWarranty_TitleItem = new GHATitleTextItem(
				"Garantía del Intermediario:", 4);
		realWarrantySinceSelectItem = new GHASelectItem("Desde", false);
		realWarrantyTimeTextItem = new GHATextItem("Duración", false);
		realWarrantyTimeTextItem.setLength(3);
		realWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				false);
		realWarrantyBeginDate = new GHADateItem("Fecha Inicio", false);
		intWarrantySinceSelectItem = new GHASelectItem("Desde", false);
		intWarrantyTimeTextItem = new GHATextItem("Duración", false);
		intWarrantyTimeTextItem.setLength(3);
		intWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo", false);
		intWarrantyBeginDate = new GHADateItem("Fecha Inicio", false);

		// Ubicacion Form Items
		location_TitleItem = new GHATitleTextItem("Ubicación:", 3);
		locationTypeSelectItem = new GHASelectItem("Tipo de Ubicación", false,
				changedHandler);
		locationTypeSelectItem.setDisabled(true);

		workingArea_TitleItem = new GHATitleTextItem("Área de Trabajo:", 3);
		facility_TitleItem = new GHATitleTextItem("Servicio/Instalación:", 3);

		workingAreaLocationCodeTextItem = new GHATextItem("Código", false);
		workingAreaSelectItem = new GHAWorkingAreaSelectItem("Nombre");
		workingAreaSelectItem.setDisabled(true);

		facilityCodeTextItem = new GHATextItem("Código", false);
		facilitySelectItem = new GHAFacilitySelectItem("Nombre");
		facilitySelectItem.setDisabled(true);
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
		fillLocationTypeSelect();
		fillWarrantySelects();
		fillReportDataSelects();

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

	@Override
	public void clear() {
		super.clear();
		// clean text fields
		codeTextItem.clearValue();
		serialTextItem.clearValue();
		serialTextItem.validate();
		fixedAssetIdTextItem.clearValue();
		facilityCodeTextItem.clearValue();
		realWarrantyTimeTextItem.clearValue();
		intWarrantyTimeTextItem.clearValue();

		// clean select fields
		eiaTypeSelectItem.clearValue();
		stateSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		adqisitionProviderSelectItem.clearValue();
		locationTypeSelectItem.clearValue();
		workingAreaSelectItem.clearValue();
		facilitySelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		realWarrantyPotSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		intWarrantyPotSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();

		realWarrantyBeginDate.clearValue();
		intWarrantyBeginDate.clearValue();

		// report data items
		damageDateItem.clearValue();
		damageTimeItem.clearValue();
		damageStatusSelectItem.clearValue();
		damagePrioritySelectItem.clearValue();
		userWhoReportedSelectItem.clearValue();
		userWhoRegistedSelectItem.clearValue();
		damageMotiveTextAreaItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private EiaDamageReport extract() {
		EiaDamageReport eiaDamageReport = new EiaDamageReport();

		eia.setState(EiaStateEnum.DAMAGED);
		eiaDamageReport.setEia(eia);

		eiaDamageReport.setDamageMotive(damageMotiveTextAreaItem
				.getValueAsString());

		if (damageStatusSelectItem.getValueAsString() != null)
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum
					.valueOf(damageStatusSelectItem.getValueAsString()));

		if (damagePrioritySelectItem.getValueAsString() != null)
			eiaDamageReport.setPriority(EiaDamagePriorityEnum
					.valueOf(damagePrioritySelectItem.getValueAsString()));

		if (userWhoRegistedSelectItem.getValueAsString() != null) {
			Bpu userWhoRegistered = new Bpu();
			userWhoRegistered.setId(Long.valueOf(userWhoRegistedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoRegistered(userWhoRegistered);
		}
		if (userWhoReportedSelectItem.getValueAsString() != null) {
			Bpu userWhoReported = new Bpu();
			userWhoReported.setId(Long.valueOf(userWhoReportedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoReported(userWhoReported);
		}

		LogicalTime time = damageTimeItem.getValueAsLogicalTime();
		LogicalDate date = damageDateItem.getValueAsLogicalDate();
		Date datetime = EiaDamageReportUtil.getDatetime(date, time);
		eiaDamageReport.setDateTimeDamage(datetime);

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaDamageReport>> violations = null;
		violations = validator.validate(eiaDamageReport);
		if (reportForm.validate() && violations.isEmpty())
			return eiaDamageReport;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (ConstraintViolation<EiaDamageReport> violation : violations)
				violationsList.add(violation.getMessage());
			GHANotification.alert(violationsList);
		}
		return null;
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

		stateSelectItem.setValueMap(EiaStateEnum.toValueMap());
	}

	private void fillLocationTypeSelect() {
		LinkedHashMap<String, String> valueMapLocationType = new LinkedHashMap<String, String>();
		valueMapLocationType.put("0", "Área de Trabajo");
		valueMapLocationType.put("1", "Servicio/Instalación");
		locationTypeSelectItem.setValueMap(valueMapLocationType);
	}

	private void fillReportDataSelects() {
		damageStatusSelectItem.setValueMap(EiaDamageStatusEnum.toValueMap());
		damagePrioritySelectItem
				.setValueMap(EiaDamagePriorityEnum.toValueMap());
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
				stateSelectItem, new GHASpacerItem(), providers_TitleItem,
				adqisitionProviderSelectItem, maintenanceProviderSelectItem);

		return equipoForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getReportForm() {
		GHADynamicForm reportForm = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 3);

		reportForm.setItems(report_TitleItem, damageStatusSelectItem,
				damagePrioritySelectItem, new GHASpacerItem(), damageDateItem,
				damageTimeItem, new GHASpacerItem(), userWhoReportedSelectItem,
				userWhoRegistedSelectItem, new GHASpacerItem(),
				damageMotiveTextAreaItem, new GHASpacerItem());

		return reportForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getUbicacionForm() {
		GHADynamicForm areaForm = new GHADynamicForm(
				GHAUiHelper.getSectionFormFormWidth(30), 3);

		areaForm.setItems(location_TitleItem, locationTypeSelectItem,
				new GHASpacerItem(2), workingArea_TitleItem,
				workingAreaSelectItem, workingAreaLocationCodeTextItem,
				facility_TitleItem, facilitySelectItem, facilityCodeTextItem);
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
		reportForm.resize(GHAUiHelper.getSectionFormFormWidth(30), 3);
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
							notifyEiaDamageReport(result);
							clear();
							if (callback != null) {
								callback.onSuccess(result);
							}

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
		workingAreaSelectItem.clearValue();
		facilitySelectItem.clearValue();

		if (eia.getWorkingArea() != null) {
			workingAreaSelectItem.setValue(eia.getWorkingArea().getId());
			workingAreaLocationCodeTextItem.setValue(eia.getWorkingArea()
					.getId());
			locationTypeSelectItem.setValue("0");
		}
		if (eia.getFacility() != null) {
			facilitySelectItem.setValue(eia.getFacility().getId());
			facilityCodeTextItem.setValue(eia.getFacility().getId());
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

		sectionForm.openFirst();
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
		damageDateItem.setDisabled(!activate);
		damageTimeItem.setDisabled(!activate);
		damageStatusSelectItem.setDisabled(!activate);
		damagePrioritySelectItem.setDisabled(!activate);
		userWhoReportedSelectItem.setDisabled(!activate);
		userWhoRegistedSelectItem.setDisabled(!activate);
		damageMotiveTextAreaItem.setDisabled(!activate);
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