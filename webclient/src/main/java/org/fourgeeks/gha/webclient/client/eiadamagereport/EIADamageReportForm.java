package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABspSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.damageandplanification.EIADamageAndPlanificationUtil;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.util.LogicalTime;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

/**
 * @author naramirez
 * 
 */
public class EIADamageReportForm extends GHAForm<EiaDamageReport> implements
		EIASelectionListener, EiaDamageReportSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			workingAreaLocationCodeTextItem, facilityCodeTextItem,
			realWarrantyTimeTextItem, intWarrantyTimeTextItem;
	private GHAWorkingAreaSelectItem workingAreaSelectItem;
	private GHAFacilitySelectItem facilitySelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHAExternalProviderSelectItem adqisitionProviderSelectItem;
	private GHABspSelectItem maintenanceProviderSelectItem;
	private GHASelectItem stateSelectItem, locationTypeSelectItem,
			realWarrantySinceSelectItem, intWarrantySinceSelectItem,
			eiaTypeSelectItem;
	private GHATitletextItem information_TitleItem, location_TitleItem,
			workingArea_TitleItem, facility_TitleItem, report_TitleItem,
			realWarranty_TitleItem, intermedWarranty_TitleItem,
			providers_TitleItem;
	private GHADateItem realWarrantyBeginDate, intWarrantyBeginDate;
	// datos del reporte
	private GHADateItem damageDateItem;
	// private GHATimeItem damageTimeItem;
	private GHATextItem damageTimeItem;
	private GHATextAreaItem damageMotiveTextAreaItem;
	private GHABpuSelectItem userWhoRegistedSelectItem,
			userWhoReportedSelectItem;
	private GHAPeriodOfTimeSelectItem realWarrantyPotSelectItem,
			intWarrantyPotSelectItem;
	private GHASelectItem damageStatusSelectItem, equipmentCondSelectItem,
			damagePrioritySelectItem;

	private GHASectionForm sectionForm;
	private final GHADynamicForm infoBasicaForm;
	private final GHADynamicForm garantiasForm;
	private final GHADynamicForm ubicacionForm;
	private final GHADynamicForm reportForm;

	private List<EiaDamageReportSelectionListener> listeners;
	private Eia eia;

	{ // Global
		sectionForm = new GHASectionForm(GHAStrings.get("reports"));
		listeners = new ArrayList<EiaDamageReportSelectionListener>();

		// report data Form Items
		report_TitleItem = new GHATitletextItem(
				"Datos del Reporte de Equipo Dañado:", 3);
		damageDateItem = new GHADateItem("Fecha del daño o falla",
				changedHandler);
		damageDateItem.setValidators(damageDateItem.getValidator());

		damageTimeItem = new GHATextItem("Hora del daño o falla", false,
				changedHandler); // new GHATimeItem("Hora del daño o falla",
		// changedHandler);
		// damageTimeItem.setKeyPressFilter("[0-2][0-9]:[0-5][0-9]");
		// damageTimeItem.setMask("");
		// ("[0-2][0-9]:[0-5][0-9]");

		RegExpValidator textValidator = new RegExpValidator();
		textValidator.setErrorMessage("La fecha introducida es Erronea");
		// textValidator.setExpression("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		textValidator.setExpression("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		damageTimeItem.setValidators(textValidator);
		// damageTimeItem.setValidateOnChange(true);
		damageTimeItem.setShowErrorIcon(true);

		damageStatusSelectItem = new GHASelectItem("Estatus", true,
				changedHandler);
		equipmentCondSelectItem = new GHASelectItem("Condición del equipo",
				true, changedHandler);
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
		information_TitleItem = new GHATitletextItem("Información:", 3);
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
		providers_TitleItem = new GHATitletextItem("Proveedores", 3);
		adqisitionProviderSelectItem = new GHAExternalProviderSelectItem(
				"Proveedor de Adq.", false);
		maintenanceProviderSelectItem = new GHABspSelectItem(
				GHAStrings.get("maintenance-provider"), false, changedHandler);
		maintenanceProviderSelectItem.setDisabled(true);

		// Garantias Form Items
		realWarranty_TitleItem = new GHATitletextItem("Garantía Real:", 4);
		intermedWarranty_TitleItem = new GHATitletextItem(
				"Garantía del Intermediario:", 4);
		realWarrantySinceSelectItem = new GHASelectItem("Desde", false);
		realWarrantyTimeTextItem = new GHATextItem("Duración", false);
		realWarrantyTimeTextItem.setLength(3);
		realWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false);
		realWarrantyBeginDate = new GHADateItem("Fecha Inicio", false);
		intWarrantySinceSelectItem = new GHASelectItem("Desde", false);
		intWarrantyTimeTextItem = new GHATextItem("Duración", false);
		intWarrantyTimeTextItem.setLength(3);
		intWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false);
		intWarrantyBeginDate = new GHADateItem("Fecha Inicio", false);

		// Ubicacion Form Items
		location_TitleItem = new GHATitletextItem("Ubicación:", 3);
		locationTypeSelectItem = new GHASelectItem("Tipo de Ubicación", false,
				changedHandler);
		locationTypeSelectItem.setDisabled(true);

		workingArea_TitleItem = new GHATitletextItem("Área de Trabajo:", 3);
		facility_TitleItem = new GHATitletextItem("Servicio/Instalación:", 3);

		workingAreaLocationCodeTextItem = new GHATextItem("Código", false);
		workingAreaSelectItem = new GHAWorkingAreaSelectItem("Nombre");
		workingAreaSelectItem.setDisabled(true);

		facilityCodeTextItem = new GHATextItem("Código", false);
		facilitySelectItem = new GHAFacilitySelectItem("Nombre");
		facilitySelectItem.setDisabled(true);
	}

	/**
	 * Constructor
	 */
	public EIADamageReportForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		infoBasicaForm = getInfoBasicaForm();
		garantiasForm = getAdquisicionForm();
		ubicacionForm = getUbicacionForm();
		reportForm = getReportForm();

		sectionForm.addSection("Información Básica", infoBasicaForm);
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
		equipmentCondSelectItem.clearValue();
		damagePrioritySelectItem.clearValue();
		userWhoReportedSelectItem.clearValue();
		userWhoRegistedSelectItem.clearValue();
		damageMotiveTextAreaItem.clearValue();

		reportForm.clearErrors(true);
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private EiaDamageReport extract() {
		// if (!hasUnCommittedChanges)
		// return null;

		final EiaDamageReport eiaDamageReport = new EiaDamageReport();

		eia.setState(EiaStateEnum.DAMAGED);
		eiaDamageReport.setEia(eia);

		eiaDamageReport.setDamageMotive(damageMotiveTextAreaItem
				.getValueAsString());

		if (damageStatusSelectItem.getValueAsString() != null)
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum
					.valueOf(damageStatusSelectItem.getValueAsString()));

		if (equipmentCondSelectItem.getValueAsString() != null)
			eiaDamageReport.setEiaCondition(EiaStateEnum
					.valueOf(equipmentCondSelectItem.getValueAsString()));

		if (damagePrioritySelectItem.getValueAsString() != null)
			eiaDamageReport.setPriority(EiaDamagePriorityEnum
					.valueOf(damagePrioritySelectItem.getValueAsString()));

		if (userWhoRegistedSelectItem.getValueAsString() != null) {
			final Bpu userWhoRegistered = new Bpu();
			userWhoRegistered.setId(Long.valueOf(userWhoRegistedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoRegistered(userWhoRegistered);
		}

		if (userWhoReportedSelectItem.getValueAsString() != null) {
			final Bpu userWhoReported = new Bpu();
			userWhoReported.setId(Long.valueOf(userWhoReportedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoReported(userWhoReported);
		}

		LogicalTime time = null;

		if (damageTimeItem.validate()) {
			// time = damageTimeItem.getValueAsLogicalTime();
		}

		final LogicalDate date = damageDateItem.getValueAsLogicalDate();

		final Timestamp timestamp = EIADamageAndPlanificationUtil.getTimestamp(
				date, time);

		eiaDamageReport.setDateTimestamp(timestamp);

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaDamageReport>> violations = null;
		violations = validator.validate(eiaDamageReport);

		if (reportForm.validate() && violations.isEmpty())
			return eiaDamageReport;
		else {
			final List<String> violationsList = new ArrayList<String>();
			for (final ConstraintViolation<EiaDamageReport> violation : violations)
				violationsList.add(violation.getMessage());
			// GHAAlertManager.alert(violationsList);

			String validation_message[] = { "damageStatus-not-null",
					"state-not-null", "userWhoReported-not-null",
					"userWhoRegistered-not-null", "damagePriority-not-null" };

			for (int i = 0; i < validation_message.length; i++) {
				if (violationsList.contains(validation_message[i])) {
					GHAErrorMessageProcessor.alert(validation_message[i]);
					return null;
				}
			}
			// GHAErrorMessageProcessor.alert(violationsList.get(0));
		}
		return null;
	}

	// Fillers
	private void fillInformationSelects() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final EiaType entity : result) {
					valueMap.put(entity.getCode() + "", entity.getName() + "");
				}
				eiaTypeSelectItem.setValueMap(valueMap);
			}
		}, false);

		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final Obu entity : result) {
					valueMap.put(entity.getId() + "", entity.getName() + "");
				}
				obuSelectItem.setValueMap(valueMap);
			}
		});

		stateSelectItem.setValueMap(EiaStateEnum.toValueMap());
	}

	private void fillLocationTypeSelect() {
		final LinkedHashMap<String, String> valueMapLocationType = new LinkedHashMap<String, String>();
		valueMapLocationType.put("0", "Área de Trabajo");
		valueMapLocationType.put("1", "Servicio/Instalación");
		locationTypeSelectItem.setValueMap(valueMapLocationType);
	}

	private void fillReportDataSelects() {
		damageStatusSelectItem.setValueMap(EiaDamageStatusEnum.toValueMap());
		equipmentCondSelectItem.setValueMap(EiaStateEnum.toValueMapCondEia());
		damagePrioritySelectItem
				.setValueMap(EiaDamagePriorityEnum.toValueMap());
	}

	private void fillWarrantySelects() {
		realWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		realWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		intWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		intWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
	}

	/**
	 * @return
	 */
	private GHADynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		final GHADynamicForm adquisicionForm = new GHADynamicForm(4,
				FormType.SECTIONFORM_FORM);

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
		final GHADynamicForm equipoForm = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

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
		final GHADynamicForm reportForm = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

		reportForm.setItems(report_TitleItem, damageStatusSelectItem,
				equipmentCondSelectItem, new GHASpacerItem(), damageDateItem,
				damageTimeItem, new GHASpacerItem(), userWhoReportedSelectItem,
				userWhoRegistedSelectItem, new GHASpacerItem(),
				damagePrioritySelectItem, new GHASpacerItem(),
				new GHASpacerItem(), damageMotiveTextAreaItem,
				new GHASpacerItem());

		return reportForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getUbicacionForm() {
		final GHADynamicForm areaForm = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

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
		for (final EiaDamageReportSelectionListener listener : listeners)
			listener.select(eiaDamageReport);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		infoBasicaForm.resize();
		garantiasForm.resize();
		ubicacionForm.resize();
		reportForm.resize();
	}

	/**
	 * Open the first section (view) of the sectionForm
	 */
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
		final EiaDamageReport eiaDamageReport = extract();
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
				&& eia.getMaintenanceProvider().getObu() != null)
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
		equipmentCondSelectItem.setDisabled(!activate);
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
